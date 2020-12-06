package com.oe.ziel.domain;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.user.User;
import com.oe.ziel.domain.work.Work;

import java.util.ArrayList;
import java.util.List;

// consider using the word schema
public abstract class ServiceOfferingDefinition {

//    /**
//     * Can the given user book this service
//     * @param user who is booking the service
//     * @return true if the user can book the service otherwise false
//     */
//    boolean canBook(User user);

    /**
     * Roles which are needed to book this service (if empty any user can book this service).
     */
    private List<String> roles = new ArrayList<>();

    /**
     * Show the booking options for the user to make a selection from.
     * @param client
     * @return
     */
    protected abstract List<BookingOption> bookingOptions(User client);


    /**
     * The Work given a booking.
     * @param booking
     * @return
     */
    public abstract List<Work> work(Booking booking);



}
