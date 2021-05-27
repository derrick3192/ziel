package com.oe.ziel.domain.booking.options;

import java.math.BigDecimal;

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
     * Whether or not the parameter must be specified
     */
    protected boolean required = false;

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

    public Double doubleValue() {
        return (Double) selected;
    }

    /**
     * Operator overloading
     */

    public Double addition(Integer val) {
        return doubleValue() + val;
    }

    public Double addition(Double val) {
        return doubleValue() + val;
    }

    public Double addition(BigDecimal val) {
        return doubleValue() + val.doubleValue();
    }

    public Double addition(BookingOption<?> val) {
        return doubleValue() + val.doubleValue();
    }

    public Double subtraction(Integer val) {
        return doubleValue() - val;
    }

    public Double subtraction(Double val) {
        return doubleValue() - val;
    }

    public Double subtraction(BigDecimal val) {
        return doubleValue() - val.doubleValue();
    }

    public Double subtraction(BookingOption<?> val) {
        return doubleValue() - val.doubleValue();
    }

    public Double multiplication(Integer val) {
        return doubleValue() * val;
    }

    public Double multiplication(Double val) {
        return doubleValue() * val;
    }

    public Double multiplication(BigDecimal val) {
        return doubleValue() * val.doubleValue();
    }

    public Double multiplication(BookingOption<?> val) {
        return doubleValue() * val.doubleValue();
    }

    public Double multiply(Integer val) {
        return doubleValue() * val;
    }

    public Double multiply(Double val) {
        return doubleValue() * val;
    }

    public Double multiply(BigDecimal val) {
        return doubleValue() * val.doubleValue();
    }

    public Double multiply(BookingOption<?> val) {
        return doubleValue() * val.doubleValue();
    }

    public Double division(Integer val) {
        return doubleValue() / val.doubleValue();
    }

    public Double division(BigDecimal val) {
        return doubleValue() / val.doubleValue();
    }

    public Double division(Double val)  {
        return doubleValue() / val.doubleValue();
    }

    public Double division(BookingOption<?> val)  {
        return doubleValue() / val.doubleValue();
    }

    public Double div(Integer val) {
        return doubleValue() / val.doubleValue();
    }

    public Double div(BigDecimal val) {
        return doubleValue() / val.doubleValue();
    }

    public Double div(Double val)  {
        return doubleValue() / val.doubleValue();
    }

    public Double div(BookingOption<?> val)  {
        return doubleValue() / val.doubleValue();
    }

    public Double power(Integer val) {
        return Math.pow(doubleValue(), val.doubleValue());
    }

    public Double power(Double val) {
        return Math.pow(doubleValue(), val.doubleValue());
    }

    public Double power(BigDecimal val) {
        return Math.pow(doubleValue(), val.doubleValue());
    }

    public Double power(BookingOption<?> val) {
        return Math.pow(doubleValue(), val.doubleValue());
    }

    public String asSwaggerResource() {
        return id;
    }


}
