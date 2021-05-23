package com.oe.ziel.domain.booking.options;

public class IntOption extends BookingOption<Integer> {

    @Override
    public Double toDouble() {
        return selected.doubleValue();
    }

}
