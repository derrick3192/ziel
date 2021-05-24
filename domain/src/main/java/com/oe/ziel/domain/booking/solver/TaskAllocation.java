package com.oe.ziel.domain.booking.solver;

import com.oe.ziel.domain.resource.Resource;
import com.oe.ziel.domain.work.Work;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Period;

public class TaskAllocation {

    protected Instant startTime;
    protected Instant endTime;
    protected Resource resource;
    protected String work;
    protected Duration duration;

    public TaskAllocation(){}

    public TaskAllocation(Instant startTime, Instant endTime, Resource resource, String work) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.resource = resource;
        this.work = work;
    }

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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Duration getDuration() {
        if (duration == null) {
            duration = new Duration(startTime, endTime);
        }
        return duration;
    }

    public Duration overlappingDuration(TaskAllocation other) {
        if (this == other) {
            return getDuration();
        }
        Instant startMaximum = (startTime.compareTo(other.startTime) < 0) ? other.startTime : startTime;
        Instant endMinimum = (endTime.compareTo(other.endTime) < 0) ? endTime : other.endTime;
        return new Period(startMaximum, endMinimum).toStandardDuration();
    }

    @Override
    public String toString() {
        return "TaskAllocation{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", resource=" + resource +
                ", work='" + work + '\'' +
                ", duration=" + getDuration().toStandardHours() +
                "} \n";
    }
}
