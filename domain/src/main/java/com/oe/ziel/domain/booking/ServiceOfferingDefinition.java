package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.booking.options.validation.ValidationResult;
import com.oe.ziel.domain.work.Work;

import java.util.List;
/**
 * A Service Offering Definition is a recipe to convert customer input into work which needs to be done by the organization.
 */
public interface ServiceOfferingDefinition {

    /**
     * Populate the context of the booking
     * @param booking sets the booking context so we can construct the actual Gantt
     */
    void accept(Booking booking);

    public Booking getBooking();

    List<? extends Work> getWorks();

    List<ValidationResult<?>> validations();

    List<BookingOption> getBookingOptions();

    String getName();

    String getDescription();

}
