package com.oe.ziel.domain.booking.options.validation;

public class ValidationResult<VALUE> {

    private boolean valid;
    private String message;
    private VALUE value;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public VALUE getValue() {
        return value;
    }

    public void setValue(VALUE value) {
        this.value = value;
    }
}
