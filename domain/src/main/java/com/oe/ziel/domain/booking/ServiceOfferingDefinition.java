package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.booking.options.validation.ValidationResult;
import com.oe.ziel.domain.user.User;
import com.oe.ziel.domain.work.Gantt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// consider using the word schema
public interface ServiceOfferingDefinition {

    /**
     * Show the booking options for the user to make a selection from.
     * @param customer who is ordering the service. For some customers you may want to desplay different booking options
     * @return the booking options the customer can use
     */
    List<BookingOption<?>> bookingOptions(User customer);

    /**
     * Optional method which can be implemented to verify if a user has input valid input data for the booking
     * @param input what the customer has input
     * @return validation results
     */
    default List<ValidationResult> validate(Map<String, Object> input) {
        return new ArrayList<>();
    }

    /**
     * Given a booking return the resulting gantt
     * @param booking the input for the service
     * @return gantt which encapsulates the
     */
    Gantt gantt(Booking booking);

}
