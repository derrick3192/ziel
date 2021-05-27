package com.oe.ziel.swagger

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.user.User
import com.oe.ziel.dsl.model.Gantt
import org.joda.time.Instant
import spock.lang.Specification

class SwaggerBuilderTest extends Specification {

    Gantt buildingGantt = Gantt.build{

        name = "BuildHouse"
        description = "This is the service offering definition to build a house."


        /**
         * BOOKING OPTIONS
         */

        def demolishNeeded = boolOption {
            label = "Demolition needed?"
            id = "demolish"
            description = "A demolition is needed if a house exists"
            selected = false
            required = true
        }


        def sqrMeters = intOption {
            label = "Square Meters"
            id = "m2"
            description = "The size of the house"
            min = 50
            max = 500
            selected = 10
        }


        def noFloors = intOption {
            label = "Number of floors"
            id = "noFloors"
            min = 1
            max = 3
            selected = 1
        }


        def noBathrooms = intOption {
            label = "Number of floors"
            id = "noBathrooms"
            min = 1
            max = 3
            selected = 1
        }




        /**
         * WORK WHICH NEEDS DOING
         */
        def demolish = work {
            name = "Demolish"
            skill = "demolisher"
            duration =  (demolishNeeded) ? sqrMeters / 100.0 : 0.0
        }


        def concreting = work {
            name = "Concreting"
            skill = "labourer"
            duration = sqrMeters / 50.0
            dependsOn demolish
        }


        def fencing = work {
            name = "Fencing"
            skill = "labourer"
            duration = sqrMeters / 100.0
        }


        def framing = work {
            name = "Framing"
            skill = "framing"
            duration = 1
            dependsOn concreting
        }


        def roofing = work {
            name = "Roofing"
            skill = "roofing"
            duration = sqrMeters / 30.0
            dependsOn framing
        }


        def plumbing = work {
            name = "plumbing"
            skill = "plumbing"
            duration = noBathrooms * noFloors
            dependsOn framing, roofing
        }

    }

    private User client = new User(
            username: "john_doe",
            displayName: "John Doe"
    )


    Booking mansionBooking = new Booking(
            createdAt: Instant.now(),
            customerInput: [
                    demolish : false,
                    m2 : 600,
                    noBathrooms : 3,
                    noFloors : 2
            ],
            customer: client
    )


    def "Swagger Builder test" () {
        given:
            SwaggerBuilder sb = new SwaggerBuilder()

        when:
            String result = sb.build(buildingGantt)
            println(result)
        then:
            true
    }


}
