package com.oe.ziel.dsl.booking

abstract class BookingOption<VALUE> {

    VALUE selected
    String id
    String label
    String description
    boolean required

}
