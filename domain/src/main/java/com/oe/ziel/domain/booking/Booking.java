package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.ServiceOfferingDefinition;
import com.oe.ziel.domain.user.User;
import org.joda.time.Instant;

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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public ServiceOfferingDefinition getServiceOfferingDefinition() {
        return serviceOfferingDefinition;
    }

    public void setServiceOfferingDefinition(ServiceOfferingDefinition serviceOfferingDefinition) {
        this.serviceOfferingDefinition = serviceOfferingDefinition;
    }

    public Map<String, Object> getCustomerInput() {
        return customerInput;
    }

    public void setCustomerInput(Map<String, Object> customerInput) {
        this.customerInput = customerInput;
    }
}
