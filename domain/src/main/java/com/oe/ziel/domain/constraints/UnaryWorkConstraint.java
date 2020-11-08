package com.oe.ziel.domain.constraints;

import com.oe.ziel.domain.work.Work;

public abstract class UnaryWorkConstraint extends Constraint {

    private final Work work;

    public UnaryWorkConstraint(Work work) {
        this.work = work;
    }
}
