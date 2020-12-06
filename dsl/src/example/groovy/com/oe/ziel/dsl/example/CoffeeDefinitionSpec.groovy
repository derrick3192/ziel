package com.oe.ziel.dsl.example

import com.oe.ziel.dsl.ServiceOfferingDefinitionSpec
import com.oe.ziel.domain.booking.options.BoolOption
import com.oe.ziel.domain.booking.options.IntOption
import com.oe.ziel.domain.booking.options.OptionList
import groovy.transform.CompileStatic
import org.joda.time.Hours
import org.joda.time.Minutes


@CompileStatic
class CoffeeDefinitionSpec extends ServiceOfferingDefinitionSpec {

    /** Constants **/

    def milkOptions = ["Full Cream", "Almond", "Soy"]
    def coffeeSizes = [
            new CoffeeConfig(6, "SML", 1),
            new CoffeeConfig(8, "MED", 2),
            new CoffeeConfig(10, "LRG", 3)
    ]

    CoffeeDefinitionSpec() {

        // constants needed for the service offering
        name = "Coffee"
        description = "That delicious caffeinated beverage everybody loves."
        authorities = ["EMPLOYEES"]                                                 // who can order this service
        multiple = true                                                             // whether you can order multiple of these in the same booking


        def gantt = gantt {

            OptionList coffeeSize = optionList {
                label = "Coffee Size"
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


            String customerDisplayName = booking.customer.getDisplayName()

            // NOTE: (booking option).value is now known here
            def coffee = coffeeSizes.find{it.label == coffeeSize.selected}

            resource {
                license("hr", 1, 2, 3)
                license("ohs", 1, 2, 3)
                same = true
            }

            def boilWater = work {

                name = "Boil Water"
                description = "Boiling the water is the process of heating the water to 100 degrees"
                amount = 10

                maxStartTime = booking.createdAt + Minutes.minutes(20).toStandardDuration()
                maxFinishTime = booking.createdAt + Hours.hours(1).toStandardDuration()

                resource {
                    license("barista")
                    constraint(3) { age > 20 && age < 30}
                    constraint { age == 22}
                }
            }

            def frothMilk = work {
                name = "Froth Milk"
                amount = coffee.milk
            }

            def grindBeans = work {
                name = "Grind Coffee Beans"
                amount = extraShots.selected + coffee.shots
            }

            def brewCoffee = work {
                name = "Brewing the coffee"
                dependsOn boilWater, grindBeans
            }

            def combine = work {
                name = "Combine the milk and the brew"
                dependsOn brewCoffee, brewCoffee
            }

            def addSugar = work {
                name = "Add Sugar"
                amount = 1
                dependsOn combine
                _if = {sugar.selected}
            }

            def giveCoffee = work {
                name = "Give Coffee to Customer"
                dependsOnAllOtherTasks = true // will be the last task completed
                after addSugar
            }

            constraints {
                // constraints which involve more than one task
                tasksMustStartAtSameTime(boilWater, frothMilk, grindBeans)
                tasksMustBeDoneByTheSameResource(boilWater, giveCoffee)
            }

        }
    }

}
