package com.oe.ziel.domain.booking.options.validation;

import com.oe.ziel.domain.booking.Booking;

import java.util.List;

public interface BookingValidator {

    /**
     * Validate if a booking option is valid or not at the booking level. This could be checks across the booking involving more than one booking option.
     * @param booking the booking to be validated
     * @return validation results for a booking
     *
     */
    List<BookingOptionValidationResult> validate(Booking booking);

}
