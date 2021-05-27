package com.oe.ziel.domain.booking.options;

public class IntOption extends BookingOption<Integer> {

    @Override
    public Double doubleValue() {
        return selected.doubleValue();
    }

    @Override
    public String asSwaggerResource() {
        return  "  " + id + ":"                  + "\n" +
                "    type: \"integer\""          + "\n" +
                "    format: \"int32\""          + "\n" +
                (this.description != null ? "    description: \"" + description + "\"" : "");
    }

}
