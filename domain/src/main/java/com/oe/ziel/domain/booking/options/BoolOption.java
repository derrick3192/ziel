package com.oe.ziel.domain.booking.options;

public class BoolOption extends BookingOption<Boolean> {

    protected String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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
}
