package com.oe.ziel.dsl

class CoffeeConfig {

    Integer milk
    String label
    Integer shots

    CoffeeConfig(Integer milk, String label, Integer shots) {
        this.milk = milk
        this.label = label
        this.shots = shots
    }

}
