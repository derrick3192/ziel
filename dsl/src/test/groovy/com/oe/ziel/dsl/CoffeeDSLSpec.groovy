package com.oe.ziel.dsl

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.user.User
import com.oe.ziel.dsl.model.Gantt
import com.oe.ziel.dsl.model.dsl.spec.WorkSpec
import org.joda.time.Duration
import org.joda.time.Hours
import org.joda.time.Instant
import org.joda.time.Minutes
import spock.lang.Specification

class CoffeeDSLSpec extends Specification {

    private User derrops = new User(
        username: "derrops",
        displayName: "DeRR0ps",
        city: "Brunswick West"
    )

    List<String> milkOptions = ["Full Cream", "Almond", "Soy"]
    List<CoffeeConfig> coffeeSizes = [
        new CoffeeConfig(6, "SML", 1),
        new CoffeeConfig(8, "MED", 2),
        new CoffeeConfig(10, "LRG", 3)
    ]


    Gantt coffeeGantt = Gantt.build {

        def coffeeSize = optionList {
            id = "coffeeSize"
            label = "Coffee Size"
            description = "What size of coffee you would like"
            options = coffeeSizes.collect{it.label}
            labels =  coffeeSizes.collect{it.label}
            selected = "MED"
            required = true
        }

        def milk = optionList {
            id = "milk"
            label = "Milk"
            description = "The kind of Milk you would like"
            options = milkOptions
            selected = "Full Cream"
            required = false
        }


        def sugar = intOption {
            id = "sugar"
            label = "Sugars"
            description = "If you want the white tasty sugar in your drink or not"
            selected = 1
            required = false
            max = 10
            min = 0
        }

        def extraShots = intOption {
            id = "extraShots"
            label = "Extra Shots?"
            description = "If you would like any extra shots with your coffee?"
            selected = 0
            min = 0
            max = 2
            required = false
        }

        // TODO - wrap in job {} so that we can access booking before defining work

        WorkSpec boilWater = work {

            name = "Boil Water"
            description = "Boiling the water is the process of heating the water to 100 degrees"
            duration = coffeeSizes.find {it.label == coffeeSize.selected}.shots

            maxStartTime = booking.createdAt + Minutes.minutes(20).toStandardDuration()
            maxFinishTime = booking.createdAt + Hours.hours(1).toStandardDuration()

//                resource {
//                    license("barista")
//                    constraint(3) { age > 20 && age < 30}
//                    constraint { age == 22}
//                }

        }

        def frothMilk = work {
            name = "Froth Milk"
            duration = coffeeSizes.find {it.label == coffeeSize.selected}.milk
        }
//
//            def grindBeans = work {
//                name = "Grind Coffee Beans"
//                duration = extraShots.selected + coffee.shots
//            }
//
//            def brewCoffee = work {
//                name = "Brewing the coffee"
//                dependsOn boilWater, grindBeans
//            }
//
//            def combine = work {
//                name = "Combine the milk and the brew"
//                dependsOn brewCoffee, brewCoffee
//            }
//
//            def addSugar = work {
//                name = "Add Sugar"
//                amount = 1
//                dependsOn combine
//                _if = {sugar.selected}
//            }
//
//            def giveCoffee = work {
//                name = "Give Coffee to Customer"
//                dependsOnAllOtherTasks = true // will be the last task completed
//                after addSugar
//            }

    }


    Booking unknownParamBooking = new Booking(
        createdAt: Instant.now(),
        customerInput: [
            coffeeSize : "MED",
            milk : "Almond",
            sugar : 5,
            extraShots : 1,
            someUnkownParam : "something"
        ],
        customer: derrops
    )


    Booking coffeeSizeMissing = new Booking(
        createdAt: Instant.now(),
        customerInput: [
            milk : "Almond",
            sugar : 5,
            extraShots : 1
        ],
        customer: derrops
    )


    Booking coffeeTooMuchSugar = new Booking(
        createdAt: Instant.now(),
        customerInput: [
            coffeeSize : "MED",
            milk : "Almond",
            sugar : 20,
            extraShots : 1
        ],
        customer: derrops
    )


    Booking coffeeNegativeSugar = new Booking(
        createdAt: Instant.now(),
        customerInput: [
            coffeeSize : "MED",
            milk : "Almond",
            sugar : -1,
            extraShots : 1
        ],
        customer: derrops
    )


    Booking coffeeThreeSugar = new Booking(
        createdAt: Instant.now(),
        customerInput: [
            coffeeSize : "MED",
            milk : "Almond",
            sugar : 3,
            extraShots : 1
        ],
        customer: derrops
    )


    def "If an unknown parameter is present throw a RuntimeException" () {
        given:
            Booking booking = unknownParamBooking
        when:
            coffeeGantt.accept(booking)
        then:
            thrown(RuntimeException)
    }


    def "If a required parameter is missing, that option should be invalid" () {
        given:
            Booking booking = coffeeSizeMissing
        when:
            coffeeGantt.accept(booking)
        then:
            coffeeGantt.validations().find{ (!it.valid) }.message == "Booking option of Coffee Size not found"
    }


    def "If a parameter is too large have there should be an invalid result" () {
        given:
            Booking booking = coffeeTooMuchSugar
        when:
            coffeeGantt.accept(booking)
        then:
            coffeeGantt.validations().find{!it.valid}.message == "Booking option of Sugars too large. Maximum value is 10"
    }


    def "If a parameter is too small have there should be an invalid result" () {
        given:
            Booking booking = coffeeNegativeSugar
        when:
            coffeeGantt.accept(booking)
        then:
            coffeeGantt.validations().find{!it.valid}.message == "Booking option of Sugars too small. Minimum value is 0"
    }


    def "Calculation of work.duration is correct" () {
        given:
            Booking booking = coffeeThreeSugar
        when:
            coffeeGantt.accept(booking)
        then:
            coffeeGantt.works.find { it.name == "Froth Milk" }.duration == Duration.standardHours(8)
    }

}
