package superchaoran.Mining;

import com.oracle.tools.packager.Log;
import montezuma.script.ScriptState;
import org.powerbot.script.*;
import org.powerbot.script.Condition;
import org.powerbot.script.rt6.*;
import org.powerbot.script.rt6.ClientContext;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.*;

/**
 * Created by chaoran on 5/18/16.
 */

@Script.Manifest(
        name = "MiningAndBanking", properties = "author=superchaoran; topic=-5; client=6;",
        description = "MiningAndBanking"
)
public class MiningAndBanking extends PollingScript<ClientContext>{
    private String rockName;
    private Path pathToMining1;
    private Path pathToBank;
    private Path pathToMining0;
    private Locatable miningLocation;
    private Locatable bankLocation = ctx.bank.nearest();

    @Override
    public void poll(){//get rid of polling delay

        while(0<1) {

            long startXp = ctx.skills.experience(Constants.SKILLS_MINING);

//            if(startXp<40000){
//                rockName= "Clay rocks";
//
//                miningLocation = new Locatable() {
//                    @Override
//                    public Tile tile() {
//                        return new Tile(3180,3371);
//                    }
//                };
//                pathToMining0 = ctx.movement.newTilePath(
//                        new Tile(3185, 3430),
//                        new Tile(3167, 3432),
//                        new Tile(3163, 3420)
//                );
//                pathToMining1 = ctx.movement.newTilePath(
//                        new Tile(3166, 3403),
//                        new Tile(3169, 3393),
//                        new Tile(3172, 3386),
//                        new Tile(3184, 3373),
//                        miningLocation.tile()
//                );
//                pathToBank = ctx.movement.newTilePath(
//                        new Tile(3176, 3389),
//                        new Tile(3170, 3404),
//                        new Tile(3170, 3421),
//                        new Tile(3185, 3430)
//                );
//
//            } else {


            if(startXp<25000){
                rockName= "Tin ore rocks";

                miningLocation = new Locatable() {
                    @Override
                    public Tile tile() {
                        return new Tile(3172,3365);
                    }
                };
                pathToMining0 = ctx.movement.newTilePath(
                        new Tile(3185, 3430),
                        new Tile(3167, 3432),
                        new Tile(3163, 3420)
                );
                pathToMining1 = ctx.movement.newTilePath(
                        new Tile(3166, 3403),
                        new Tile(3169, 3393),
                        new Tile(3172, 3386),
                        new Tile(3184, 3373),
                        miningLocation.tile()
                );
                pathToBank = ctx.movement.newTilePath(
                        new Tile(3184, 3373),
                        new Tile(3176, 3389),
                        new Tile(3170, 3404),
                        new Tile(3170, 3421),
                        new Tile(3185, 3430)
                );

            } else {

                rockName= "Iron ore rocks";

                miningLocation = new Locatable() {
                    @Override
                    public Tile tile() {
                        return new Tile(3175,3367);
                    }
                };
                pathToMining0 = ctx.movement.newTilePath(
                        new Tile(3185, 3430),
                        new Tile(3167, 3432),
                        new Tile(3163, 3420)
                );
                pathToMining1 = ctx.movement.newTilePath(
                        new Tile(3166, 3403),
                        new Tile(3169, 3393),
                        new Tile(3172, 3386),
                        new Tile(3184, 3373),
                        new Tile(3176, 3380),
                        miningLocation.tile()
                );
                pathToBank = ctx.movement.newTilePath(
                        new Tile(3184, 3373),
                        new Tile(3176, 3389),
                        new Tile(3170, 3404),
                        new Tile(3170, 3421),
                        new Tile(3185, 3430)
                );
            }

            switch (state()) {
                case Mining:
                    log.info("Mining");
                    ctx.input.send("{VK_UP}");
                    Condition.sleep(200);
                    ctx.input.send("{VK_UP}");
                    Condition.sleep(200);
                    ctx.input.send("{VK_UP}");
                    Condition.sleep(200);
                    ctx.input.send("{VK_UP}");
                    Condition.sleep(200);
                    while (ctx.backpack.select().count() < 28) {
                        log.info("Finding " + rockName);
                        /*public MobileIdNameQuery<GameObject> select(final Locatable l, final int radius) {
                            return select(get(l, radius));
                        }*/
                        //GameObject rock0 = ctx.objects.select().id(rockIds).nearest().poll();
                        GameObject rock0 = ctx.objects.select(miningLocation, 2).name(rockName).nearest().poll();
                        if (ctx.backpack.select().count() == 28) {
                            break;
                        }
                        final int[] bounds = {-3, 3, -3, 0, -3, 3};
                        rock0.doSetBounds(bounds);
                        rock0.click();
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                if (ctx.objects.select(rock0.tile(), 0).poll().name().equals(rockName)) {
                                    log.info(rockName + "Still exists, mining on it");
                                    return false;
                                } else {
                                    log.info(rockName + "vanished");
                                    return true;
                                }
                            }
                        }, 100, 10 * 3);
                    }
                    break;

                case Banking:
                    log.info("Banking");
                    if (ctx.bank.open()) {
                        if (ctx.bank.inViewport() || ctx.bank.opened()) {
                            if (!ctx.backpack.select().isEmpty())
                                ctx.bank.depositInventory();
                        }
                    }
                    ctx.bank.close();
                    break;

                case GotoMining:

                    log.info("GotoMiningSpot");
                    double d = ctx.movement.distance(miningLocation);
                    /*split to prevent obvious bot glitch*/

                    log.info("Traversing path0....");
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            double distance = new Tile(3163, 3420).tile().distanceTo(ctx.players.local());
                            if (distance < 0 || distance > 2) {
                                log.info("Traversing path0:traverse count");
                                ctx.camera.turnTo(pathToMining0.next());
                                pathToMining0.traverse();
                                return false;
                            } else {
                                log.info("Traversing path0:finished");
                                return true;
                            }
                        }
                    }, 500, 2 * 15);


                    log.info("Traversing path1....");
                    pathToMining1.traverse();
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            double distance = miningLocation.tile().distanceTo(ctx.players.local());
                            if (distance < 0 || distance > 2) {
                                log.info("Traversing path1:traverse count");
                                ctx.camera.turnTo(pathToMining1.next());
                                pathToMining1.traverse();
                                return false;
                            } else {
                                log.info("Traversing path1:finished");
                                return true;
                            }
                        }
                    }, 500, 2 * 20);


                    /*  that should work if you are close to the bank,
                        if the bank is further than ~ 50 tiles you will need to use a tilepath*/
                    //ctx.movement.step(miningLocation);
                    //Condition.sleep(500);
                    ctx.camera.turnTo(miningLocation);
                    Condition.sleep(500);
                    break;

                case GotoBank:
                    log.info("Going to Bank");

                    bankLocation = ctx.bank.nearest();

                    if (bankLocation.tile().distanceTo(ctx.players.local()) <= 10) {
                        log.info("Bank is near, entering near bank search....");
                        ctx.movement.step(bankLocation.tile());
                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                double distance = bankLocation.tile().distanceTo(ctx.players.local().tile());
                                if (distance < 0 || distance > 2) {
                                    log.info("near bank search:traversing count");
                                    ctx.camera.turnTo(bankLocation.tile());
                                    ctx.movement.step(bankLocation.tile());
                                    return false;
                                } else {
                                    log.info("near bank search:finished");
                                    return true;
                                }
                            }
                        }, 500, 2 * 10);
                    } else {
                        log.info("Bank too far away, Entering PathTile mode....");

                        Condition.wait(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                double distance = new Tile(3185, 3430).tile().distanceTo(ctx.players.local());
                                if (distance < 0 || distance > 3) {
                                    log.info("Pathfinding:traversing count");
                                    ctx.camera.turnTo(pathToBank.next());
                                    pathToBank.traverse();
                                    return false;
                                } else {
                                    log.info("Pathfinding:finished");
                                    return true;
                                }
                            }
                        }, 500, 2 * 20);
                    }
                    break;
            }
        }

    }

    private State state() {
        log.info("Checking state");
        if(ctx.backpack.select().count() >= 27) {
            log.info("Checking gotoBank && banking");
            double distance = bankLocation.tile().distanceTo(ctx.players.local());
            if(distance<0 || distance > 4) {
                log.info("Checking gotoBank;");
                return State.GotoBank;
            } else {
                log.info("Checking banking;");
                return State.Banking;
            }
        } else {
            log.info("Checking gotoMining && Mining");
            double distance = miningLocation.tile().distanceTo(ctx.players.local());
            if(distance<0 || distance > 3) {
                log.info("Checking gotoMining");
                return State.GotoMining;
            } else {
                log.info("Checking mining");
                return State.Mining;
            }
        }
    }

    private enum State {
        Mining, GotoBank, GotoMining, Banking
    }

}
