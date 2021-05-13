package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.booking.options.validation.BookingOptionValidationResult;
import com.oe.ziel.domain.work.Gantt;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A Service Offering Definition is a recipe to convert customer input into work which needs to be done by the organization.
 */
public abstract class ServiceOfferingDefinition {

    /**
     * The booking options available to the customer
     * @return the booking options the customer can use
     */
    public abstract List<BookingOption<?>> bookingOptions();

    /**
     * Perform validation on the booking options a customer has specified. It is enough to return only invalid results,
     * as missing results on an option will be assumed to be valid.
     * @param bookingOptions the option to validate
     * @return the validation results for the given option
     */
    public List<BookingOptionValidationResult<?>> validate(List<BookingOption<?>> bookingOptions) {
        return bookingOptions().stream().map(bi -> new BookingOptionValidationResult<>(true, "valid", bi)).collect(Collectors.toList());
    }

    /**
     * This is the crux of a service offering. The gantt represents the work involved in to deliver the service, and the
     * applicable constraints. Particular work might only be able to be performed by a resources with particular
     * attributes, or the work might need to be performed in a given order.
     * @param bookingOptions
     * @return
     */
    public abstract Gantt gantt(List<BookingOption<?>> bookingOptions);

}
