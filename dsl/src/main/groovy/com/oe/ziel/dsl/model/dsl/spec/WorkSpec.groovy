package com.oe.ziel.dsl.model.dsl.spec

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.constraints.WorkDependencyConstraint
import com.oe.ziel.domain.work.Work


import java.util.function.Consumer


class WorkSpec extends Work{


    Booking booking

    List<Constraint> constraints = new ArrayList<>()


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

    Booking getBooking() {
        return booking
    }

    void setBooking(Booking booking) {
        this.booking = booking
    }


    private Consumer<WorkSpec> consumer;

    void addConsumer(Consumer<WorkSpec> consumer) {
        this.consumer = consumer
    }

    void build() {
        consumer.accept(this)
    }


    @Override
    public String toString() {
        return "WorkSpec{" +
            "name='" + name + '\'' +
            ", amount=" + amount.getStandardHours() +
            ", priority=" + priority +
            ", description='" + description + '\'' +
            ", maxStartTime=" + maxStartTime +
            ", maxFinishTime=" + maxFinishTime +
            '}';
    }
}
