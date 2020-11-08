package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.ServiceOfferingDefinition;
import com.oe.ziel.domain.user.User;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * A booking made by a user
 */
public class Booking {

    /**
     * When the booking was created
     */
    private Instant createdAt;

    /**
     * User who made the booking
     */
    private User customer;

    /**
     * Status of the booking
     */
    private BookingStatus status;

    /**
     * Which service has been booked
     */
    private ServiceOfferingDefinition serviceOfferingDefinition;

    /**
     *  The options the customer has specified
     */
    Map<String, Object> customerInput = new HashMap<>();

}
