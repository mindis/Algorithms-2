package superchaoran.HerbsUlitimate.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;
import superchaoran.GraniteSplitter.GraniteSpliterMain;
import superchaoran.HerbsUlitimate.HerbsUltimateMain;
import superchaoran.HerbsUlitimate.constants.Herb;
import superchaoran.HerbsUlitimate.constants.MethodChosen;
import superchaoran.HerbsUlitimate.script.Job;
import superchaoran.HerbsUlitimate.utils.ImprovedBank;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class Bank extends Job<HerbsUltimateMain, ClientContext> {

    private ImprovedBank improvedBank;
    private MethodChosen methodChosen;
    private ArrayList<int[]> withdrawSize;
    private ArrayList<Item> itemArrayList;

    public Bank(HerbsUltimateMain script, MethodChosen methodChosen) {

        super(script);
        this.methodChosen = methodChosen;
        this.improvedBank = new ImprovedBank(ctx);
        this.withdrawSize = methodChosen.getWithdrawSize();
    }

    /*activate bank if each component size not match, then check total composite*/
    @Override
    public boolean activate() {
        int total = 0;
        for(int i = 0; i < withdrawSize.size(); i++){
           if(ctx.backpack.select().id(withdrawSize.get(i)[0]).count() != withdrawSize.get(i)[1]){
               return true;
           }
            total += withdrawSize.get(i)[1];
        }
        return ctx.backpack.select().count() != total;
    }

    @Override
    public void execute() {
        if (ctx.bank.inViewport() || ctx.bank.opened()) {
            if (ctx.bank.open()) {

                /* caching items*/
                if (itemArrayList == null) {
                    itemArrayList = new ArrayList<Item>();
                    for(int i = 0; i< withdrawSize.size(); i++) {
                        itemArrayList.add(ctx.bank.select().id(withdrawSize.get(i)[0]).poll());
                    }
                }

                /* withdrawing based on specified size */
                for(int i = 0; i< withdrawSize.size(); i++) {

                    int id = withdrawSize.get(i)[0];

                    if (ctx.bank.select().id(id).isEmpty()) {
                        script.log.info("[Herbs Ultimate] Required item not found in the bank, stopping.");
                        script.stop();
                        ctx.controller.stop();
                        return;
                    }

                    if (!ctx.backpack.select().isEmpty())
                        ctx.bank.depositInventory();

                    this.improvedBank.withdrawCustomized(itemArrayList.get(i), withdrawSize.get(i)[1], false);

                }

                this.improvedBank.closeBank();

                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return !activate();
                    }
                });

            }
        } else {
            Locatable bank = ctx.bank.nearest();
            if (bank.tile().distanceTo(ctx.players.local()) >= 7) {
                ctx.movement.step(bank);
            } else {
                ctx.camera.turnTo(bank);
            }
        }

    }
}
