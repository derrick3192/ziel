package com.oe.ziel.domain.booking.solver;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Period;

public class Grid {

    private final Integer steps;
    private final Instant start;
    private final Instant end;
    private final Period period;
    private final Duration duration;

    public Grid(Integer steps, Instant start, Instant end) {
        this.steps = steps;
        this.start = start;
        this.end = end;
        this.period = new Period(start, end);
        this.duration = new Duration(start, end);
    }

    /** Returns [0, 1] where the allocation sits on the entire spectrum*/
    public Double t(Integer i) {
        return i.doubleValue() / steps;
    }

    /** Returns the duration from the start time */
    public Duration h(Integer i) {
        return duration.multipliedBy(i).dividedBy(steps);
    }

    /** Returns an instant at Step i */
    public Instant instant(Integer i) {
        return start.plus(h(i));
    }

    public Integer getSteps() {
        return steps;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public Period getPeriod() {
        return period;
    }

    public Duration getDuration() {
        return duration;
    }
}
