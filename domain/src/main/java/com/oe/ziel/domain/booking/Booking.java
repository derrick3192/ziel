package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.booking.options.validation.BookingOptionValidationResult;
import com.oe.ziel.domain.user.User;
import org.joda.time.Instant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     *  The options the customer has specified
     */
    Map<String, Comparable<?>> customerInput = new HashMap<>();

    /**
     * User who made the booking
     */
    private User customer;
    
    /**
     * Which service has been booked
     */
    private ServiceOfferingDefinition serviceOfferingDefinition;


    /**
     * The validation results when the booking was made
     */
    private List<BookingOptionValidationResult> bookingOptionValidationResults = new ArrayList<>();

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public ServiceOfferingDefinition getServiceOfferingDefinition() {
        return serviceOfferingDefinition;
    }

    public void setServiceOfferingDefinition(ServiceOfferingDefinition serviceOfferingDefinition) {
        this.serviceOfferingDefinition = serviceOfferingDefinition;
    }

    public Map<String, Comparable<?>> getCustomerInput() {
        return customerInput;
    }

    public void setCustomerInput(Map<String, Comparable<?>> customerInput) {
        this.customerInput = customerInput;
    }
}
