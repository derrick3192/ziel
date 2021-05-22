package com.oe.ziel.domain.booking.options;

public abstract class BookingOption<VALUE extends Comparable<VALUE>> {

    /**
     * The value currently selected by the customer
     */
    protected VALUE selected;

    /**
     * The id of the booking option. This is the identifier to use in scripting
     */
    protected String id;

    /**
     * The label which will be described to the user
     */
    protected String label;

    /**
     * The description of the booking option
     */
    protected String description;

    /**
     * Whether or not the paramter must be specified
     */
    protected boolean required;

    /**
     * The maximum value
     */
    protected VALUE max;

    /**
     * The minimum value
     */
    protected VALUE min;

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

    public VALUE getMax() {
        return max;
    }

    public void setMax(VALUE max) {
        this.max = max;
    }

    public VALUE getMin() {
        return min;
    }

    public void setMin(VALUE min) {
        this.min = min;
    }

    public boolean isTooLarge() {
        if (max == null) return false;
        return selected.compareTo(max) > 0;
    }

    public boolean isTooSmall() {
        if (min == null) return false;
        return selected.compareTo(min) < 0;
    }

}
