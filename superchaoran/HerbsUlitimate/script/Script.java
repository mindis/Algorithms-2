package superchaoran.HerbsUlitimate.script;

import org.powerbot.script.ClientContext;
import org.powerbot.script.PollingScript;

/*
 * Author: Coma
 */

public abstract class Script<C extends ClientContext> extends PollingScript<C> {

    protected JobContainer container = new JobContainer();

    protected void work() {
        Job j = container.get();
        if (j != null) {
            j.execute();
        }
    }

    public void submit(Job... jobs) {
        container.submit(jobs);
    }

    public C context() {
        return ctx;
    }
}
