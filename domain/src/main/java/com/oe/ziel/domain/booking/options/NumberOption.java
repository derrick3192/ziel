package com.oe.ziel.domain.booking.options;

public class NumberOption extends BookingOption<Double> {

    @Override
    public String asSwaggerResource() {
        return  "  " + id + ":"                  + "\n" +
                "    type: \"number\""          + "\n" +
                "    format: \"double\""          + "\n" +
                (this.description != null ? "    description: \"" + description + "\"" : "");
    }

}
