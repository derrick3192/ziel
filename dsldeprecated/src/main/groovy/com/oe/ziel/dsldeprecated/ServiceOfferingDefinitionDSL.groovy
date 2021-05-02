package com.oe.ziel.dsldeprecated

import com.oe.ziel.domain.booking.ServiceOfferingDefinition
import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.user.User
import com.oe.ziel.domain.work.Gantt
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsldeprecated.booking.gantt.GanttSpec
import com.oe.ziel.domain.booking.options.BookingOption

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


    List<BookingOption> bookingOptions() {
        return gantt.bookingOptions
    }


    List<Work> work(Booking booking) {
        return gantt.work(booking)
    }

    @Override
    List<BookingOption<?>> bookingOptions(User customer) {
        return null
    }

    @Override
    Gantt gantt(Booking booking) {
        return null
    }
}
