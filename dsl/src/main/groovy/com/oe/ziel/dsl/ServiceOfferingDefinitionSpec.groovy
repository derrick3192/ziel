package com.oe.ziel.dsl

import com.oe.ziel.domain.ServiceOfferingDefinition
import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.user.User
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.booking.gantt.GanttSpec
import com.oe.ziel.domain.booking.options.BookingOption
import com.oe.ziel.domain.booking.options.BoolOption
import com.oe.ziel.domain.booking.options.IntOption
import com.oe.ziel.domain.booking.options.OptionList
import com.oe.ziel.dsl.booking.validation.Validation

class ServiceOfferingDefinitionSpec extends ServiceOfferingDefinition {


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




    //Validation validation = new Validation()

    List<Constraint> constraints = new ArrayList<>()


    def gantt(@DelegatesTo(value = GanttSpec) Closure cl) {

        return new GanttSpec()
    }

    @Override
    protected List<BookingOption> bookingOptions(User client) {
        this.client = client
    }

    @Override
    List<Work> work(Booking booking) {
        return gantt.work(booking)
    }
}
