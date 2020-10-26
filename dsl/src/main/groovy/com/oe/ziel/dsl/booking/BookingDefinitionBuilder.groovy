package com.oe.ziel.dsl.booking

class BookingDefinitionBuilder {

    List<BookingOption> bookingOptions = new ArrayList<>()

    Validation validation = new Validation()

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
        cl.rehydrate(validation, this, this)
        cl.call()
        return validation
    }

    def gantt(Closure cl) {

    }


}
