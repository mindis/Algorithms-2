package superchaoran.HerbsUlitimate.script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Author: Coma
 */

public class JobContainer implements Comparator<Job> {

    private List<Job> jobList = new ArrayList<Job>();

    public JobContainer(Job... jobs) {
        submit(jobs);
    }

    public JobContainer() {
        this(new Job[0]);
    }

    public void submit(final Job... jobs) {
        for (Job j : jobs) {
            if (!jobList.contains(j)) {
                jobList.add(j);
            }
        }
        Collections.sort(jobList, this);
    }

    public void clear() {
        jobList.clear();
    }

    public Job get() {
        for (Job j : jobList) {
            if (j.activate()) {
                return j;
            }
        }
        return null;
    }

    @Override
    public int compare(Job o1, Job o2) {
        return o2.priority() - o1.priority();
    }
}
