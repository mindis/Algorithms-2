package superchaoran.HerbsUlitimate.utils;

import org.powerbot.script.Condition;
import org.powerbot.script.StringUtils;
import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Component;
import org.powerbot.script.rt6.Constants;
import org.powerbot.script.rt6.Item;

import java.awt.*;
import java.util.concurrent.Callable;

/**
 * Created by chaoran on 5/13/16.
 */
public class ImprovedBank {
    public static ClientContext ctx;
    public ImprovedBank(ClientContext ctx){
        this.ctx = ctx;
    }
    public boolean withdrawCustomized(final Item item, final int amount, final boolean bob) {//TODO: anti pattern
        final Component component = ctx.widgets.component(Constants.BANK_WIDGET, Constants.BANK_ITEMS);
        if (!component.valid() || !item.valid()) {
            return false;
        }
        final Component c = item.component();
        if (c.relativePoint().y == 0) {
            if (!currentTab(0) && Condition.wait(new Condition.Check() {
                @Override
                public boolean poll() {
                    return c.relativePoint().y != 0;
                }
            }, 100, 10)) {
                return false;
            }
        }
        final Rectangle vr = component.viewportRect();
        if (!vr.contains(c.viewportRect()) && !ctx.widgets.scroll(c, ctx.widgets.component(Constants.BANK_WIDGET, Constants.BANK_SCROLLBAR),
                vr.contains(ctx.input.getLocation()))) {
            return false;
        }

        String action = "Withdraw-" + amount;
        //noinspection StatementWithEmptyBody
        if (amount == 1) {
        } else if (bob) {
            action = "fall";
        } else if (amount == 0 ||
                (item.stackSize() <= amount && amount != 5 && amount != 10)) {
            action = "Withdraw-All";
        } else if (amount == -1 || amount == (item.stackSize() - 1)) {
            action = "Withdraw-All but one";
        }
        final int inv = ctx.backpack.moneyPouchCount() + ctx.backpack.select().count(true);
        if (amount != 0 && !containsAction(c, action)) {
            if (c.interact(bob ? "Withdraw-X to Bob" : "Withdraw-X") && Condition.wait(new Condition.Check() {
                @Override
                public boolean poll() {
                    return isInputWidgetOpen();
                }
            })) {
                Condition.sleep();
                ctx.input.sendln(amount + "");
            }
        } else {
            if (!c.interact(action)) {
                return false;
            }
        }
//        Condition.wait(new Callable<Boolean>() {
//            @Override
//            public Boolean call() throws Exception {
//                return !ctx.backpack.isEmpty();
//            }
//        }, 20, 50*20);

        return true;
    }

    public boolean currentTab(final int index) {
        final Component c = ctx.widgets.component(Constants.BANK_WIDGET, 150 + (index * 8));
        return c.click() && Condition.wait(new Condition.Check() {
            @Override
            public boolean poll() {
                return currentTab() == index;
            }
        }, 100, 8);
    }

    public int currentTab() {
        return ((ctx.varpbits.varpbit(Constants.BANK_STATE) >>> 24) - 136) / 8;
    }


    private boolean containsAction(final Component c, final String action) {
        final String[] actions = c.actions();
        for (final String a : actions) {
            if (a != null && StringUtils.stripHtml(a).trim().equalsIgnoreCase(action)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInputWidgetOpen() {
        return ctx.widgets.component(1469, 2).visible();
    }

    public void closeBank(){
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                if(ctx.bank.opened()){
                    ctx.input.send("{ESCAPE}");
                    return false;
                } else {
                    return true;
                }
            }
        }, 20, 50*20);
    }
}
