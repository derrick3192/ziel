package com.oe.ziel.dsl.booking

class BookingDefinitionBuilder {

    List<BookingOption> bookingOptions = new ArrayList<>()

    List<Check> checks = new ArrayList<>()

    protected <B extends BookingOption> B buildOption(Closure cl, B bookingOption) {
        cl.rehydrate(bookingOption, this, this)
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

    def validation(@DelegatesTo(value = ValidationSpec) Closure cl) {

    }

}
