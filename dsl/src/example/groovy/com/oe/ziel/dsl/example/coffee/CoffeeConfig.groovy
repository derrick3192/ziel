package com.oe.ziel.dsl.example.coffee

class CoffeeConfig {

    int milk
    String label
    int shots

    CoffeeConfig(int milk, String label, int shots) {
        this.milk = milk
        this.label = label
        this.shots = shots
    }
}