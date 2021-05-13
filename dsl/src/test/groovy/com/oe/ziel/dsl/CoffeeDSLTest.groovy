package com.oe.ziel.dsl

import com.oe.ziel.domain.booking.options.BoolOption
import com.oe.ziel.domain.booking.options.IntOption
import com.oe.ziel.domain.booking.options.OptionList
import com.oe.ziel.dsl.model.Gantt
import groovy.transform.CompileStatic
import org.junit.Test

class CoffeeDSLTest {

    List<String> milkOptions = ["Full Cream", "Almond", "Soy"]
    List<CoffeeConfig> coffeeSizes = [
        new CoffeeConfig(6, "SML", 1),
        new CoffeeConfig(8, "MED", 2),
        new CoffeeConfig(10, "LRG", 3)
    ]


    @CompileStatic
    @Test
    void test() {

        Gantt.build {

            OptionList coffeeSize = optionList {
                id = "coffeeSize"
                label = "Coffee Size"
                description = "What size of coffee you would like"
                options = coffeeSizes.collect{it.label}
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

            println getBookingOptions()

        }

    }





}
