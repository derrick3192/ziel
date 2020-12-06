package com.oe.ziel.optaplanner;

import org.joda.time.Instant;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class TaskAllocation extends com.oe.ziel.domain.work.TaskAllocation {

    @PlanningVariable
    @Override
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

}
