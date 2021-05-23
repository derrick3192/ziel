package com.oe.ziel.domain.booking.solver;

import com.oe.ziel.domain.resource.Resource;
import com.oe.ziel.domain.work.Work;
import org.joda.time.Duration;
import org.joda.time.Instant;

public class TaskAllocation {

    private Instant startTime;
    private Instant endTime;
    private Resource resource;
    private Work work;
    private Duration duration;

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Duration getDuration() {
        if (duration == null) {
            duration = new Duration(startTime, endTime);
        }
        return duration;
    }

}
