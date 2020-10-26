package com.oe.ziel.domain.constraints;

import com.oe.ziel.domain.Work;

public interface UnaryWorkConstraint extends Constraint {

    Work getWork();

    void setWork(Work work);

}
