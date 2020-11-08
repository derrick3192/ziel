package com.oe.ziel.domain.booking.options;

class BoolOption extends BookingOption<Boolean> {

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
