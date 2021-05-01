package com.oe.ziel.domain.booking;

import com.oe.ziel.domain.user.User;

public class BookingBuilder {

    public Booking booking = new Booking();

    public static BookingBuilder open() {
        BookingBuilder bookingBuilder = new BookingBuilder();
        return bookingBuilder;
    }

    public BookingBuilder withAnonymousCustomer() {
        booking.setCustomer(new User());
        return this;
    }

    public BookingBuilder withCustomer(User user) {
        booking.setCustomer(user);
        return this;
    }

    public BookingBuilder withServiceOfferingDefinition(ServiceOfferingDefinition serviceOfferingDefinition) {
        booking.setServiceOfferingDefinition(serviceOfferingDefinition);
        return this;
    }

    public Booking makeBooking() {
        return booking;
    }

    public OptionInputBuilder withOptions() {
        return new OptionInputBuilder(this);
    }
}
