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
}
