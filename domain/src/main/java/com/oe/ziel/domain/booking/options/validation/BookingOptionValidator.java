package com.oe.ziel.domain.booking.options.validation;

import com.oe.ziel.domain.booking.options.BookingOption;

public interface BookingOptionValidator<VALUE> {

    /**
     * Validate a BookingOption if it is valid or not
     * @param bi
     * @return
     */
    ValidationResult validate(BookingOption<VALUE> bi);

}
