package superchaoran.GraniteSplitter.script;

import org.powerbot.script.Client;
import org.powerbot.script.ClientContext;

/*
 * Author: Coma
 */

public abstract class JobSet<T extends Script<C>, C extends ClientContext<? extends Client>> extends Job<T, C> {

    private JobContainer container;
    private String status = "";


    public JobSet(T script, Job... jobs) {
        super(script);
        container = new JobContainer(jobs);
    }

    public abstract boolean initiate();

    @Override
    public final void execute() {
        final Job job = container.get();
        if (job != null) {
            status = job.status();
            job.execute();
        }
    }

    @Override
    public String status() {
        return status;
    }

    @Override
    public boolean activate() {
        return initiate();
    }
}
