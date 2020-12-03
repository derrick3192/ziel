package com.oe.ziel.dsl.booking.gantt

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.booking.gantt.constraint.ConstraintsSpec
import groovy.transform.CompileStatic

@CompileStatic
class GanttSpec extends WorkSpec {

    Booking booking

    List<Work> works = new ArrayList<>()

    List<Constraint> constraints = new ArrayList<>()

    def constraints(@DelegatesTo(value = ConstraintsSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {

    }

    void tasksMustStartAtSameTime(WorkSpec... works) {

    }

    void tasksMustBeDoneByTheSameResource(WorkSpec... works) {

    }

    @Override
    WorkSpec work(@DelegatesTo(value = WorkSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        WorkSpec work = new WorkSpec()
        cl.rehydrate(work, this, this)
        cl.call()
    }

    ResourceSpec resource(@DelegatesTo(value = ResourceSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        ResourceSpec resource = new ResourceSpec()
        cl.rehydrate(resource, this, this)
        cl.call()
    }

}
