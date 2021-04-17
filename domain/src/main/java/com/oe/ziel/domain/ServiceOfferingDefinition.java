package com.oe.ziel.domain;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.booking.options.BookingOption;
import com.oe.ziel.domain.work.Work;

import java.util.List;

// consider using the word schema
public interface ServiceOfferingDefinition {


    /**
     * Show the booking options for the user to make a selection from.
     * @return
     */
    List<BookingOption> bookingOptions();


    /**
     * The Work given a booking.
     * @param booking
     * @return
     */
    List<Work> work(Booking booking);



}
