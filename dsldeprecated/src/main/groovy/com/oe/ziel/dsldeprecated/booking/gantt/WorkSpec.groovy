package com.oe.ziel.dsldeprecated.booking.gantt

import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.constraints.WorkDependencyConstraint
import com.oe.ziel.dsldeprecated.booking.gantt.constraint.ConstraintsSpec
import groovy.transform.CompileStatic
import org.joda.time.Instant

@CompileStatic
class WorkSpec implements Spec {

    List<Constraint> constraints = new ArrayList<>()

    String name

    double amount

    int priority = 0

    String description

    int age;

    Instant maxStartTime

    Instant maxFinishTime

    boolean dependsOnAllOtherTasks

    /**
     * Condition for task being completed
     */
    Closure<Boolean> _if

    void dependsOn(WorkSpec work) {
        WorkDependencyConstraint c = new WorkDependencyConstraint(null, null)
    }

    void after(WorkSpec work) {
        println "TODO"
    }

    void dependsOn(WorkSpec... works){
        for (WorkSpec w : works) {
            dependsOn(w)
        }
    }

    def constraints(@DelegatesTo(value = ConstraintsSpec) Closure cl) {

    }

    ResourceSpec resource(@DelegatesTo(value = ResourceSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {

        ResourceSpec resource = new ResourceSpec()
        cl.rehydrate(resource, this, this)
        cl.call()

        return resource
    }

}
