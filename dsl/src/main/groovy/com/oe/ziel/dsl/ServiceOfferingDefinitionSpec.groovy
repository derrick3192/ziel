package com.oe.ziel.dsl

import com.oe.ziel.domain.ServiceOfferingDefinition
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.dsl.booking.gantt.GanttSpec
import com.oe.ziel.domain.booking.options.BookingOption
import com.oe.ziel.domain.booking.options.BoolOption
import com.oe.ziel.domain.booking.options.IntOption
import com.oe.ziel.domain.booking.options.OptionList
import com.oe.ziel.dsl.booking.validation.Validation

abstract class ServiceOfferingDefinitionSpec extends ServiceOfferingDefinition {


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


    GanttSpec gantt = new GanttSpec()


    // booking options definitely needs to be a member in the builder
    List<BookingOption> bookingOptions = new ArrayList<>()

    //Validation validation = new Validation()

    List<Constraint> constraints = new ArrayList<>()

    protected <B extends BookingOption> B buildOption(Closure cl, B bookingOption) {
        cl.rehydrate(bookingOption, this, this)
        cl.call()
        bookingOptions.add(bookingOption)
        return bookingOption
    }

    OptionList optionList(@DelegatesTo(value = OptionList) Closure cl) {
        return buildOption(cl, new OptionList())
    }

    BoolOption boolOption(@DelegatesTo(value = BoolOption) Closure cl) {
        return buildOption(cl, new BoolOption())
    }

    IntOption intOption(@DelegatesTo(value = IntOption) Closure cl) {
        return buildOption(cl, new IntOption())
    }

    Validation validation(@DelegatesTo(value = Validation) Closure cl) {
        Validation validation = new Validation()
        cl.rehydrate(validation, this, this)
        cl.call()
        return validation
    }

    def gantt(@DelegatesTo(value = GanttSpec) Closure cl) {
        return new GanttSpec()
    }


}
