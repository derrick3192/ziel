package com.oe.ziel.dsl

import com.oe.ziel.domain.ServiceOfferingDefinition
import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.user.User
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.booking.gantt.GanttSpec
import com.oe.ziel.domain.booking.options.BookingOption
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType


abstract class ServiceOfferingDefinitionDSL implements ServiceOfferingDefinition {

    protected ServiceOfferingDefinitionDSL() {
        ganttSpec = ganttSpec()
    }

    protected GanttSpec ganttSpec


    /**
     * Name of the service offering
     */
    String name

    /**
     * Description of the service offering
     */
    String description

    /**
     * Who is allowed to book this service offering
     */
    List<String> authorities

    /**
     * Allow multiple orders of this in the same service offering
     */
    boolean multiple


    /**
     * The client ordering the service
     */
    User client


    GanttSpec gantt = new GanttSpec()

    
    List<Constraint> constraints = new ArrayList<>()


    def gantt(@DelegatesTo(value = GanttSpec) Closure cl) {
        cl.rehydrate(gantt, this, this)
        cl.call()
    }

    @Override
    List<BookingOption> bookingOptions() {
        return gantt.bookingOptions
    }

    @Override
    List<Work> work(Booking booking) {
        return gantt.work(booking)
    }
}
