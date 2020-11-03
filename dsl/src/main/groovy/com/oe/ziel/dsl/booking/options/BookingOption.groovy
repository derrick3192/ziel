package com.oe.ziel.dsl.booking.options

abstract class BookingOption<VALUE> {

    VALUE selected
    String id
    String label
    String description
    boolean required

}
