package com.oe.ziel.domain.booking.options;

public class BoolOption extends BookingOption<Boolean> {

    @Override
    public boolean isTooLarge() {
        if (max == null) return false;
        return max && !selected;
    }

    @Override
    public boolean isTooSmall() {
        if (min == null) return false;
        return !min && selected;
    }

    @Override
    public Double doubleValue() {
        return selected ? 1.0 : 0.0;
    }

    @Override
    public String asSwaggerResource() {
        return  "  " + id + ":"                  + "\n" +
                "    type: \"boolean\""          + "\n" +
                (this.description != null ? "    description: \"" + description + "\"" : "");
    }

}
