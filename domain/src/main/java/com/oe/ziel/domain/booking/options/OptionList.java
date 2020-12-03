package com.oe.ziel.domain.booking.options;

import java.util.List;

public class OptionList extends BookingOption<String> {

    private List<String> options;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
