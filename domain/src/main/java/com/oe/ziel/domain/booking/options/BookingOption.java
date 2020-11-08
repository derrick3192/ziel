package com.oe.ziel.domain.booking.options;

public abstract class BookingOption<VALUE> {

    private VALUE selected;
    private String id;
    private String label;
    private String description;
    private boolean required;

    public VALUE getSelected() {
        return selected;
    }

    public void setSelected(VALUE selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
