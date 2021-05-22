package com.oe.ziel.domain.booking.options.validation;

import com.oe.ziel.domain.booking.options.BookingOption;

public class BookingOptionValidationResult<VALUE extends Comparable<VALUE>> extends ValidationResult<VALUE> {

    protected BookingOption<VALUE> bookingOption;

    public BookingOptionValidationResult(BookingOption bookingOption, boolean valid, String message) {
        this.bookingOption = bookingOption;
        this.valid = valid;
        this.message = message;
    }


    public BookingOption<VALUE> getBookingOption() {
        return bookingOption;
    }

    public void setBookingOption(BookingOption<VALUE> bookingOption) {
        this.bookingOption = bookingOption;
    }

    @Override
    public VALUE getValue() {
        return bookingOption.getSelected();
    }

}
