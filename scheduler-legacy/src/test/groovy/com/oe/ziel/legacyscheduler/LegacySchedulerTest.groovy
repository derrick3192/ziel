package com.oe.ziel.legacyscheduler

import com.oe.ziel.LegacyScheduler
import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.resource.Resource
import com.oe.ziel.domain.user.User
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.model.Gantt
import org.joda.time.Instant
import spock.lang.Specification


class LegacySchedulerTest extends Specification {

    private User derrops = new User(
            username: "derrops",
            displayName: "DeRR0ps",
            city: "Brunswick West"
    )


    private User client = new User(
            username: "john_doe",
            displayName: "John Doe"
    )

    Gantt buildingGantt = Gantt.build{

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
            skill = "labourer"
            duration = 1
            dependsOn concreting
        }


        def roofing = work {
            name = "Roofing"
            skill = "labourer"
            duration = sqrMeters / 30.0
            dependsOn framing
        }


        def plumbing = work {
            name = "plumbing"
            skill = "plumber"
            duration = noBathrooms * noFloors
            dependsOn framing, roofing
        }

    }
    
    /**
     * RESOURCES
     */
    def demolisher = new Resource("demolisher")
    def labourer1 = new Resource("labourer")
    def labourer2 = new Resource("labourer")
    def plumbing = new Resource("plumber")

    List<Resource> resources = Arrays.asList(demolisher, labourer1, labourer2, plumbing)


    def "testing" () {
        given:
            def start = Instant.now()
            def end = start + 1000000000
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
        when:
            buildingGantt.accept(mansionBooking)
            List<? extends Work> works = buildingGantt.getWorks()
            LegacyScheduler legacyScheduler = new LegacyScheduler(start, end);
            println legacyScheduler.schedule(resources, works)
        then:
            true
    }

}
