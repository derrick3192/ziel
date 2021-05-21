package com.oe.ziel.dsl

import com.oe.ziel.domain.booking.Booking
import com.oe.ziel.domain.booking.options.BookingOption
import com.oe.ziel.domain.booking.options.BoolOption
import com.oe.ziel.domain.booking.options.IntOption
import com.oe.ziel.domain.booking.options.OptionList
import com.oe.ziel.domain.user.User
import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.model.Gantt
import com.oe.ziel.dsl.model.dsl.spec.WorkSpec
import groovy.transform.CompileStatic
import org.joda.time.Hours
import org.joda.time.Instant
import org.joda.time.Minutes
import org.junit.Test

class CoffeeDSLTest {

    private User derrops = new User(username: "derrops", displayName: "DeRR0ps", city: "Brunswick West")


    List<String> milkOptions = ["Full Cream", "Almond", "Soy"]
    List<CoffeeConfig> coffeeSizes = [
        new CoffeeConfig(6, "SML", 1),
        new CoffeeConfig(8, "MED", 2),
        new CoffeeConfig(10, "LRG", 3)
    ]


    @CompileStatic
    @Test
    void test() {

        Gantt gantt = Gantt.build {

            OptionList coffeeSize = optionList {
                id = "coffeeSize"
                label = "Coffee Size"
                description = "What size of coffee you would like"
                options = coffeeSizes.collect{it.label}
                labels =  coffeeSizes.collect{it.label}
                selected = "MED"
                required = true
            }

            OptionList milk = optionList {
                id = "milk"
                label = "Milk"
                description = "The kind of Milk you would like"
                options = milkOptions
                selected = "Full Cream"
                required = false
            }

            BoolOption sugar = boolOption {
                id = "sugar"
                label = "Sugar?"
                description = "If you want the white tasty sugar in your drink or not"
                selected = false
                required = false
            }

            IntOption extraShots = intOption {
                id = "extraShots"
                label = "Extra Shots?"
                description = "If you would like any extra shots with your coffee?"
                selected = 0
                min = 0
                max = 2
                required = false
            }

            WorkSpec boilWater = work {

                name = "Boil Water"
                description = "Boiling the water is the process of heating the water to 100 degrees"
                amount = 10

                maxStartTime = booking.createdAt + Minutes.minutes(20).toStandardDuration()
                maxFinishTime = booking.createdAt + Hours.hours(1).toStandardDuration()

//                resource {
//                    license("barista")
//                    constraint(3) { age > 20 && age < 30}
//                    constraint { age == 22}
//                }

            }

//            def frothMilk = work {
//                name = "Froth Milk"
//                amount = booking.customerInput.
//            }
//
//            def grindBeans = work {
//                name = "Grind Coffee Beans"
//                amount = extraShots.selected + coffee.shots
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


        Booking booking = new Booking(
            createdAt: Instant.now(),
            customerInput: [
                coffeeSize : "MED",
                milk : "Almond",
                sugar : true,
                extraShots : false
            ],
            customer: derrops
        )
        gantt.accept(booking)

        List<? extends Work> works = gantt.getWorks()


//        List<BookingOption<?>> bookingOptions = booking
//        println bookingOptions
        //List<Validation> gantt.validate()



    }





}
