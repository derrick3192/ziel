package com.oe.ziel.dsl.booking

class Check {
    String message
    boolean valid

    Check(String message, boolean valid) {
        this.message = message
        this.valid = valid
    }
}
