package com.oe.ziel.dsl.booking

import com.oe.ziel.dsl.booking.gantt.Gantt
import com.oe.ziel.dsl.booking.options.BookingOption
import com.oe.ziel.dsl.booking.options.BoolOption
import com.oe.ziel.dsl.booking.options.IntOption
import com.oe.ziel.dsl.booking.options.OptionList
import com.oe.ziel.dsl.booking.validation.Validation
import org.joda.time.Instant


class BookingDefinitionBuilder {



    // booking options definitely needs to be a member in the builder
    List<BookingOption> bookingOptions = new ArrayList<>()

    //Validation validation = new Validation()

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

    def gantt(@DelegatesTo(value = Gantt) Closure<Gantt> cl) {

    }

//    def ServiceOfferig make() {
//
//        // initiate booking options
//
//        // cl.call(booking)
//
//
//    }


}
