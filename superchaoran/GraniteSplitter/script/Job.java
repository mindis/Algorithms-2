package superchaoran.GraniteSplitter.script;

import org.powerbot.script.Client;
import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

/*
 * Author: Coma
 */

public abstract class Job<T extends Script<C>, C extends ClientContext<? extends Client>> extends ClientAccessor<C> {

    protected final T script;

    public Job(T script) {
        super(script.context());
        this.script = script;
    }

    public abstract boolean activate();

    public abstract void execute();

    public int priority() {
        return 0;
    }

    public String status() {
        return "";
    }
}
