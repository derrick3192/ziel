package com.oe.ziel.swagger

import com.oe.ziel.domain.booking.ServiceOfferingDefinition
import com.oe.ziel.domain.booking.options.BookingOption


class SwaggerBuilder {

    static void main(String[] args) {

        SwaggerBuilder sb = new SwaggerBuilder()

        sb.build(null)

    }


    String findResourceName(String name) {
        // TODO this needs to be more robust and make use of a library
        return name.capitalize()
                .replace(" ", "")
                .replace("?", "")
        + "BookingInput"
    }

    String swagger(ServiceOfferingDefinition so) {
        return """
  ${findResourceName(so.getName())}:
    type: "object"
    description: "${so.getDescription()}"
    properties:
      ${so.getBookingOptions().collect{swagger(it)}}
      demolish:
        type: "boolean"
        description: "The size of the house"
"""
    }

    String build(ServiceOfferingDefinition so) {

        StringWriter sw = new StringWriter()
        sw.append(
                """
swagger: "2.0"
info:
  description: "This api consists of the service offerings for the organization."
  version: "1.0.0"
  title: "Ziel Platform"
  termsOfService: "http://swagger.io/terms/"
  contact:
    email: "derrops@derrops.com"
  license:
    name: "Apache 2.0"
    url: "http://www.apache.org/licenses/LICENSE-2.0.html"
host: "derrops.com"
basePath: "/api"
tags:

- name: "service offerings"
  description: "Manage applications that you will be developing"
  
schemes:
- "https"
paths:

  /book:
    post:
      tags:
      - "service offerings"
      summary: "Make a booking for ${so.getName()}"
      description: "${so.getDescription()}"
      operationId: "createBooking"
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - in: "body"
        name: "body"
        description: "order specificiation for the booking"
        required: true
        schema:
          \$ref: "#/definitions/${findResourceName(so.getName())}"
      responses:
        "200":
          description: "Booking accepted"
        "201":
          description: "Booking created"
        "422":
          description: "Error in booking"          

  /book/{id}:
    put:
      tags:
      - "service offerings"
      summary: "Make a booking for ${so.getName()}"
      description: "${so.getDescription()}"
      operationId: "updateBooking"
      consumes:
      - "application/json"
      produces:
      - "application/json"
      parameters:
      - in: "path"
        name: "id"
        description: "the identity of the booking"
        required : true
        type: "string"
      - in: "body"
        name: "body"
        description: "order specificiation for the booking"
        required: true
        schema:
          \$ref: "#/definitions/${findResourceName(so.getName())}"
      responses:
        "200":
          description: "Booking accepted"
        "201":
          description: "Booking created"
        "422":
          description: "Error in booking"          




\n"""
        )




    sw.append(
"""
definitions:\n\n
"""
    )

        String resourcesHead = (
"""
  ${findResourceName(so.getName())}:
    type: "object"
    properties:
"""
)
        sw.append(resourcesHead)
        so.getBookingOptions().forEach{it.asSwaggerResource().lines().forEach{l -> sw.append("\n    " + l) } && sw.append("\n")}

        return sw.toString()
    }

}
