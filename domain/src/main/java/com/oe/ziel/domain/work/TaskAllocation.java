package com.oe.ziel.domain.work;


import com.oe.ziel.domain.resource.Resource;
import org.joda.time.Instant;

public class TaskAllocation {

    private Instant startTime;
    private Instant endTime;
    private Resource resource;
    private Work work;

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
}
