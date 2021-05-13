package com.oe.ziel.domain.system;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.resource.Resource;

import java.util.List;


/**
 * Responsible for accepting or declining bookings
 */
public class BookingSystem {

    List<Resource> resources;

    List<Booking> currentBookings;

    /**
     * Based on the current workload, is it possible to do this booking. This will be based on the planing solution of this problem.
     * @param booking
     * @return
     */
    public boolean book(List<Booking> booking) {
        return false;
    }

}
