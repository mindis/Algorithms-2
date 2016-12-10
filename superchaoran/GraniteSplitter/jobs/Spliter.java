package superchaoran.GraniteSplitter.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.*;
import superchaoran.GraniteSplitter.GraniteSpliterMain;
import superchaoran.GraniteSplitter.constants.GraniteRaw;
import superchaoran.GraniteSplitter.script.Job;
import java.util.concurrent.Callable;

public class Spliter extends Job<GraniteSpliterMain, ClientContext> {

    private final GraniteRaw graniteRaw;
    String status;
    GraniteSpliterMain script;

    public Spliter(GraniteSpliterMain script, GraniteRaw graniteRaw) {
        super(script);
        this.graniteRaw = graniteRaw;
        this.script = script;
    }


    @Override
    public boolean activate() {
        return ctx.backpack.select().count() == graniteRaw.getWithdrawSize() && ctx.backpack.select().id(graniteRaw.id()).count() == graniteRaw.getWithdrawSize();
    }

    @Override
    public void execute() {
        status = "Open Backpack";
        if (!ctx.hud.opened(Hud.Window.BACKPACK)) {
            ctx.hud.open(Hud.Window.BACKPACK);
        }

        if (graniteRaw.equals(GraniteRaw.Granite5kg)) {

            script.log.info("Craft 5kg");
            status = "Craft 5kg";
            ctx.backpack.select().id(graniteRaw.id()).poll().interact("Craft");
            //Confirm
            status = "Confirm crafting";
            script.log.info("Confirm crafting");
            if (Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    return ctx.widgets.component(1370, 20).visible();
                }
            }, 20, 50 * 3)) {
                ctx.input.send(" ");
            }

            //wait for completion
            status = "Wait for crafting 5kg";
            script.log.info("Wait for crafting 5kg");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    int count = ctx.backpack.select().count();
                    return count == 12;
                }
            }, 20, 50 * 15);
        }


        //craft 2kg granite
        status = "Craft 2kg";
        script.log.info("Craft 2kg");
        ctx.backpack.select().id(GraniteRaw.Granite2kg.id()).poll().interact("Craft");

        //Confirm
        status = "Confirm crafting";
        script.log.info("Confirm crafting");
        if (Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return ctx.widgets.component(1370, 20).visible();
            }
        }, 20, 50*3)) {
            ctx.input.send(" ");
//                    Condition.sleep(1000);
//                    Condition.wait(new Callable<Boolean>() {
//                        @Override
//                        public Boolean call() {
//                            return !ctx.widgets.component(1251, 0).visible();
//                        }
//                    }, 1, 16000);
        }

        //wait for completion
        status = "Wait for crafting 2kg";
        script.log.info("Wait for crafting 2kg");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                int count = ctx.backpack.select().count();
                return count == (graniteRaw.equals(GraniteRaw.Granite5kg)?27:28);
            }
        }, 20, 50 * 15);

        script.numberSplitted +=  graniteRaw.equals(GraniteRaw.Granite5kg)? 3:7;
        status = "Completed a backpack cycle";
        script.log.info("Completed a backpack cycle");
    }
}
