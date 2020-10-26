package com.oe.ziel.dsl

import com.oe.ziel.dsl.booking.BookingDefinitionBuilder

abstract class ServiceOfferingDefinition {

    protected BookingDefinitionBuilder booking = new BookingDefinitionBuilder()

    /**
     * Name of the service offering
     */
    String name

    /**
     * Description of the service offering
     */
    String description

    /**
     * Who is allowed to book this service offering
     */
    List<String> authorities

    /**
     * Allow multiple orders of this in the same service offering
     */
    boolean multiple


}
