package com.oe.ziel.domain.work;


import com.oe.ziel.domain.resource.Resource;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Period;

public class TaskAllocation {

    protected Instant startTime;
    protected Instant endTime;
    protected Resource resource;
    protected Work work;
    protected Instant minStartTime;
    protected Instant maxStarTime;

    protected Instant minEndTime;
    protected Instant maxEndTime;

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

    public Duration overlappingDuration(TaskAllocation other) {
        if (this == other) {
            return duration();
        }
        Instant startMaximum = (startTime.compareTo(other.startTime) < 0) ? other.startTime : startTime;
        Instant endMinimum = (endTime.compareTo(other.endTime) < 0) ? endTime : other.endTime;
        return new Period(startMaximum, endMinimum).toStandardDuration();
    }

    private Duration duration() {
        return new Period(startTime, endTime).toStandardDuration();
    }

    public Instant getMaxStarTime() {
        return maxStarTime;
    }

    public void setMaxStarTime(Instant maxStarTime) {
        this.maxStarTime = maxStarTime;
    }

    public Instant getMaxEndTime() {
        return maxEndTime;
    }

    public void setMaxEndTime(Instant maxEndTime) {
        this.maxEndTime = maxEndTime;
    }
}
