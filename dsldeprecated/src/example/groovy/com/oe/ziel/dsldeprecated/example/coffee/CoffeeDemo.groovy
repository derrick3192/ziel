package com.oe.ziel.dsldeprecated.example.coffee


import com.oe.ziel.domain.booking.BookingBuilder

class CoffeeDemo {

    static void main(String[] args) {

        CoffeeDefinition coffee = new CoffeeDefinition()

        def booking = BookingBuilder
            .open()
            .withAnonymousCustomer()
            .withServiceOfferingDefinition(coffee)
            .withOptions()
                .withOption("coffeeSize", "MED")
                .withOption(" milk", "Soy")
                .withBool("sugar", true)
                .withInt("extraShots", 1)
                .buildOptions()
            .makeBooking()

        println booking

        println coffee.work(booking)

    }

}
