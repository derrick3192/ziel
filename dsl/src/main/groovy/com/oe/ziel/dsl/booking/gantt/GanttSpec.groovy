package com.oe.ziel.dsl.booking.gantt

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.booking.options.BookingOption
import com.oe.ziel.domain.booking.options.BoolOption
import com.oe.ziel.domain.booking.options.IntOption
import com.oe.ziel.domain.booking.options.OptionList
import com.oe.ziel.domain.constraints.Constraint
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.booking.gantt.constraint.ConstraintsSpec
import com.oe.ziel.dsl.booking.validation.Validation
import groovy.transform.CompileStatic
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam
import groovy.transform.stc.SimpleType

@CompileStatic
class GanttSpec extends WorkSpec {

    Booking booking

    // booking options definitely needs to be a member in the builder
    List<BookingOption> bookingOptions = new ArrayList<>()

    List<Work> works = new ArrayList<>()

    List<Constraint> constraints = new ArrayList<>()

    def constraints(@DelegatesTo(value = ConstraintsSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {

    }

    void tasksMustStartAtSameTime(WorkSpec... works) {

    }

    void tasksMustBeDoneByTheSameResource(WorkSpec... works) {

    }


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

    WorkSpec work(@DelegatesTo(value = WorkSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        WorkSpec work = new WorkSpec()
        cl.rehydrate(work, this, this)
        cl.call()
        return null
    }

    ResourceSpec resource(@DelegatesTo(value = ResourceSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        ResourceSpec resource = new ResourceSpec()
        cl.rehydrate(resource, this, this)
        cl.call()
        return null
    }

    List<Work> work(Booking booking) {
        return works
    }
}
