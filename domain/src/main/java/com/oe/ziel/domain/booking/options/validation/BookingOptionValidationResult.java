package com.oe.ziel.domain.booking.options.validation;

import com.oe.ziel.domain.booking.options.BookingOption;

public class BookingOptionValidationResult<VALUE> {

    private boolean valid;
    private String message;
    BookingOption<VALUE> bookingOption;

    public BookingOptionValidationResult(boolean valid, String message, BookingOption<VALUE> bookingOption) {
        this.valid = valid;
        this.message = message;
        this.bookingOption = bookingOption;
    }

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

    public BookingOption<VALUE> getBookingOption() {
        return bookingOption;
    }

    public void setBookingOption(BookingOption<VALUE> bookingOption) {
        this.bookingOption = bookingOption;
    }
}
