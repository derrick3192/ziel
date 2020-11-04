package com.oe.ziel.dsl.example

import com.oe.ziel.dsl.ServiceOfferingDefinition
import com.oe.ziel.dsl.booking.Booking
import com.oe.ziel.dsl.booking.options.BoolOption
import com.oe.ziel.dsl.booking.options.IntOption
import com.oe.ziel.dsl.booking.options.OptionList

class CoffeeDefinition extends ServiceOfferingDefinition {

    /** Constants **/

    def milkOptions = ["Full Cream", "Almond", "Soy"]
    def coffeeSizes = [
            new CoffeeConfig(6, "SML", 1),
            new CoffeeConfig(8, "MED", 2),
            new CoffeeConfig(10, "LRG", 3)
    ]

    CoffeeDefinition() {

        // constants needed for the service offering
        name = "Coffee"
        description = "That delicious caffeinated beverage everybody loves."
        authorities = ["EMPLOYEES"]                                                 // who can order this service
        multiple = true                                                             // whether you can order multiple of these in the same booking


        OptionList coffeeSize = optionList {
            label = "Coffe Size"
            description = "What size of coffee you would like"
            options = coffeeSizes.collect{it.label}
            selected = "MEDIUM"
            required = true
        }

        OptionList milk = optionList {
            label = "Milk"
            description = "The kind of Milk you would like"
            options = milkOptions
            selected = "Full Cream"
            required = false
        }

        BoolOption sugar = boolOption {
            label = "Sugar?"
            description = "If you want the white tasty sugar in your drink or not"
            selected = false
            required = false
        }

        IntOption extraShots = intOption {
            label = "Extra Shots?"
            description = "If you would like any extra shots with your coffee?"
            selected = 0
            min = 0
            max = 2
        }

        /** Any input validation which may need to occur between the components**/
        def validation = validation {

            check("Check the shot to sugar craziness") {
                return sugar.selected && extraShots.selected >= 2
            }

            check("You can't have a large Almond") {
                return ! (milk.selected == "Almond" && coffeeSize.selected == "LRG" )
            }

        }

        def gantt = gantt {
            Booking booking ->



            // NOTE: (booking option).value is now known here
            def coffee = coffeeSizes.find{it.label == coffeeSize.selected}

            // any constraints here will be added to all task allocation decisions (including resource decisions & starting times)
            constraints {
                // all plans have an implicit
                minStartTime(booking.createdAt)

                // constraint for all tasks
                license("BARISTER", 1)
            }

            def boilWater = work {
                name = "Boil Water"
                description = "Boiling the water is the process of heating the water to 100 degrees"
                amount = 10
                constraints {
                    minStartTime = builder.createdAt
                }
            }

            def frothMilk = work {
                name = "Froth Milk"
                amount = coffee.milk
            }

            def grindBeans = work {
                name = "Grind Coffee Beans"
                amount = extraShots.of(builder) + coffee.shots
            }

            def brewCoffee = work {
                name = "Brewing the coffee"
                dependsOn boilWater, grindBeans
            }

            def combine = work {
                name = "Combine the milk and the brew"
                dependsOn brewCoffee, brewCoffee
            }

            // example of a task which may or may not need to be done based on the booking
            // _if must also return a clours
            def addSugar = work {
                name = "Add Sugar"
                amount = 1
                dependsOn combine
                iff = {booking -> extraShots > 0}
            }

            def giveCoffee = work {
                dependsOnAllOtherTasks = true // will be the last task completed
                name = "Give Coffee to Customer"
                dependsOnAllOtherTasks = true // will be the last task completed
                after addSugar
            }

            constraints {
                // constraints which involve more than one task
                tasksMustStartAtSameTime(boilWater, frothMilk, grindBeans)
                //tasksMustBeDoneByTheSameResource(boilWater, give)
                allTasksMustBeDoneByTheSameResource = true
            }

        }
    }

}
