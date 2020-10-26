package com.oe.ziel.dsl.example

import com.oe.ziel.dsl.ServiceOfferingDefinition
import com.oe.ziel.dsl.booking.BoolOption
import com.oe.ziel.dsl.booking.IntOption
import com.oe.ziel.dsl.booking.OptionList

class CoffeeDefinition extends ServiceOfferingDefinition {

    CoffeeDefinition() {
        name = "Coffee"
        description = "That delicious caffeinated beverage everybody loves."
        authorities = ["EMPLOYEES"]                                                 // who can order this service
        multiple = true                                                             // whether you can order multiple of these in the same booking
    }

    def coffeeSizes = [new CoffeeConfig(6, "SML", 1), new CoffeeConfig(8, "MED", 2), new CoffeeConfig(10, "LRG", 3)]
    def milkOptions = ["Full Cream", "Almond", "Soy"]


    /**
     * Booking Options
     */
    OptionList coffeeSize = booking.optionList {
        label = "Coffe Size"
        description = "What size of coffee you would like"
        options = coffeeSizes.collect{it.label}
        selected = "MEDIUM"
        required = true
    }

    OptionList milk = booking.optionList {
        label = "Milk"
        description = "The kind of Milk you would like"
        options = milkOptions
        selected = "Full Cream"
        required = false
    }

    BoolOption sugar = booking.boolOption {
        label = "Sugar?"
        description = "If you want the white tasty sugar in your drink or not"
        selected = false
        required = false
    }

    IntOption extraShots = booking.intOption {
        label = "Extra Shots?"
        description = "If you would like any extra shots with your coffee?"
        selected = 0
        min = 0
        max = 2
    }

    def validation = booking.validation {
        check {
            label = "Check the shot craziness"
            return sugar.selected && extraShots.selected >= 2 ? invalid("That coffee is too unhealthy. You cannot have Sugar & extra shots") : valid()
        }
    }

    gantt {

        // NOTE: (booking option).value is now known here
        def coffee = coffeeSizes.find{it.label == coffeeSize.value}

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
                minStartTime = booking.createdAt
            }
        }

        def frothMilk = work {
            name = "Froth Milk"
            amount = coffee.milk
        }

        def grindBeans = work {
            name = "Grind Coffee Beans"
            amount = extraShots.of(booking) + coffee.shots
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
