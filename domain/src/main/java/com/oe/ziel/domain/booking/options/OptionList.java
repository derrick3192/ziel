package com.oe.ziel.domain.booking.options;

import java.util.List;
import java.util.stream.Collectors;


public class OptionList extends BookingOption<String> {

    private List<String> options;

    private List<String> labels;

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public Double doubleValue() {
        throw new RuntimeException("Cannot covert OptionList.selected to a Number.");
    }

    @Override
    public String toString() {
        return "OptionList{" +
                "options=" + options +
                ", labels=" + labels + '\'' +
                ", selected=" + selected + '\'' +
                ", id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                '}';
    }

    @Override
    public String asSwaggerResource() {
        return  "  " + id + ":"                  + "\n" +
                "    type: \"string\""          + "\n" +
                "    enum: [" + String.join(",", options) + "]\n" +
                (this.description != null ? "    description: \"" + description + "\"" : "");
    }

}
