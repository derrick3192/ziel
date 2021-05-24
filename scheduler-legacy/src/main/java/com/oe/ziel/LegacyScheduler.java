package com.oe.ziel;

import com.oe.solver.oscar.OscarSolverJava;
import com.oe.ziel.domain.booking.solver.Scheduler;
import org.joda.time.Instant;

import java.util.List;

public class LegacyScheduler implements Scheduler {

    private final Instant minStartTime;
    private final Instant maxFinishTime;

    public LegacyScheduler(Instant minStartTime, Instant maxFinishTime) {
        this.minStartTime = minStartTime;
        this.maxFinishTime = maxFinishTime;
    }

    @Override
    public List<com.oe.ziel.domain.booking.solver.TaskAllocation> schedule(List<com.oe.ziel.domain.resource.Resource> resources, List<com.oe.ziel.domain.work.Work> works) {

        OscarSolverJava oeSolver = new OscarSolverJava(minStartTime, maxFinishTime);

        return oeSolver.schedule(
                resources,
                works
        );
    }

    public static void main(String[] args) {
        
    }

}
