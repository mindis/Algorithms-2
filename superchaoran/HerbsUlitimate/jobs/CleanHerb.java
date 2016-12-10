package superchaoran.HerbsUlitimate.jobs;

import org.powerbot.script.Condition;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;
import org.powerbot.script.rt6.Hud;
import org.powerbot.script.rt6.Item;
import superchaoran.HerbsUlitimate.HerbsUltimateMain;
import superchaoran.HerbsUlitimate.constants.Herb;
import superchaoran.HerbsUlitimate.constants.MethodChosen;
import superchaoran.HerbsUlitimate.script.Job;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CleanHerb extends Job<HerbsUltimateMain, ClientContext> {

    HerbsUltimateMain script;
    MethodChosen methodChosen;
    private ArrayList<int[]> withdrawSize;
    private ArrayList<Item> itemArrayList;
    String status;

    public CleanHerb(HerbsUltimateMain script, MethodChosen methodChosen) {
        super(script);
        this.script = script;
        this.methodChosen = methodChosen;
        this.withdrawSize = methodChosen.getWithdrawSize();
        methodChosen.getHerb().setNumberCleaned(0);
    }


    @Override
    public boolean activate() {
        int total = 0;
        for(int i = 0; i < withdrawSize.size(); i++){
            if(ctx.backpack.select().id(withdrawSize.get(i)[0]).count() != withdrawSize.get(i)[1]){
                return false;
            }
            total += withdrawSize.get(i)[1];
        }
        return ctx.backpack.select().count() == total;
    }

    @Override
    public void execute() {

        script.log.info("Open Backpack");
        status = "Open Backpack";
        if(!ctx.hud.opened(Hud.Window.BACKPACK)){
            ctx.hud.open(Hud.Window.BACKPACK);
        }

        //Take anagogic ort
        GameObject rock0 = ctx.objects.select(ctx.players.local().tile(), 0).name("Anagogic ort").poll();
        if(rock0.valid()) {
            rock0.click();
            Condition.sleep(300);
        }

        //cleaning herb
        script.log.info("Clearning herb");
        status = "Clearning herb";
        ctx.backpack.select().id(methodChosen.getHerb().getGrimyId()).poll().interact("Clean");

        //Confirm
        status = "Confirm cleaning";
        if (Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                return ctx.widgets.component(1370, 20).visible();
            }
        }, 20, 50*3)) {
            ctx.input.send(" ");
        }

        //wait for completion
        status = "Wait for cleaning...";
        Condition.wait(new Condition.Check() {
            @Override
            public boolean poll() {
                return ctx.backpack.select().id(methodChosen.getHerb().getCleanId()).count() == 28;
            }
        }, 20, 50 * 15);

        methodChosen.getHerb().setNumberCleaned(methodChosen.getHerb().getNumberCleaned()+28);
    }
}
