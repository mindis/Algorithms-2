package superchaoran.Mining;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Item;

import java.util.concurrent.Callable;

/**
 * Created by chaoran on 5/24/16.
 */


@Script.Manifest(
        name = "PowerMining", properties = "author=superchaoran; topic=-5; client=6;",
        description = "PowerMining"
)
public class PowerMining extends PollingScript<ClientContext> {
    private String rockName = "Tin ore rocks";
    private int rockID;
    @Override
    public void poll(){
        while (1 < 2) {
            switch (state()) {
                case Mining:
                    GameObject rock0 = ctx.objects.select(ctx.players.local().tile(), 2).name(rockName).nearest().poll();
                   if(rock0.valid()) {
                       rockID = rock0.id();
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
                       break;
                   }

                case Dropping:
                    log.info("Dropping");
                    ctx.backpack.select().id(rockID).each(new Filter<Item>() {
                        @Override
                        public boolean accept(Item item) {
                            return item.interact("Drop");
                        }
                    });
                    break;
            }
        }
    }
    private State state() {
        log.info("Checking state");
        if(ctx.backpack.select().id(rockID).count() > 0 ) {
            log.info("Dropping State");
            return State.Dropping;
        } else {
            log.info("Mining State");
            return State.Mining;
        }
    }

    private enum State {
        Mining, Dropping
    }
}
