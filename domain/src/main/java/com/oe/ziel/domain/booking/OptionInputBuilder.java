package com.oe.ziel.domain.booking;

import java.util.HashMap;
import java.util.Map;

public class OptionInputBuilder {

    Map<String, Comparable<?>> options = new HashMap<>();

    private BookingBuilder bookingBuilder;

    public OptionInputBuilder(BookingBuilder bookingBuilder) {
        this.bookingBuilder = bookingBuilder;
    }

    public OptionInputBuilder withOption(String option, String value) {
        options.put(option, value);
        bookingBuilder.booking.setCustomerInput(options);
        return this;
    }

    public OptionInputBuilder withBool(String option, boolean value) {
        options.put(option, value);
        bookingBuilder.booking.setCustomerInput(options);
        return this;
    }

    public OptionInputBuilder withInt(String option, int value) {
        options.put(option, value);
        bookingBuilder.booking.setCustomerInput(options);
        return this;
    }

    public BookingBuilder buildOptions() {
        bookingBuilder.booking.setCustomerInput(options);
        return bookingBuilder;
    }
}
