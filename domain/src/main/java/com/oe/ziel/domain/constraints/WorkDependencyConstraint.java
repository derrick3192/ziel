package com.oe.ziel.domain.constraints;

import com.oe.ziel.domain.work.TaskAllocation;
import org.joda.time.Minutes;

public class WorkDependencyConstraint extends Constraint {

    private final TaskAllocation primary;

    private final TaskAllocation secondary;

    public WorkDependencyConstraint(TaskAllocation primary, TaskAllocation secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public TaskAllocation getPrimary() {
        return primary;
    }

    public TaskAllocation getSecondary() {
        return secondary;
    }

    @Override
    public boolean isValid() {
        return primary.getStartTime().isAfter(secondary.getEndTime()) || primary.getStartTime() == secondary.getEndTime();
    }

    @Override
    int calculateScore() {
        Minutes.minutesBetween(secondary.getEndTime(), primary.getStartTime()).getMinutes();
    }

}
