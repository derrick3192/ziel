package com.oe.ziel.dsldeprecated.booking.validation

class Validation {

    List<Validator> validators = new ArrayList<>();

    def check(String description, Closure<Boolean> cl) {
        Validator validator = new Validator(description:description, check:cl)
        validators.add(validator)
        return validator
    }

}
