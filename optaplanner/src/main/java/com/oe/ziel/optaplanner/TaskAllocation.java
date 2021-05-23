package com.oe.ziel.optaplanner;

import com.oe.ziel.domain.resource.Resource;
import org.joda.time.Instant;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class TaskAllocation extends com.oe.ziel.domain.booking.solver.TaskAllocation {

    @PlanningVariable
    @Override
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
