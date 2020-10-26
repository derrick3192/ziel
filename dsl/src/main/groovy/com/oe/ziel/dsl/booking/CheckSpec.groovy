package com.oe.ziel.dsl.booking

class CheckSpec {

    String label

    def invalid(String message) {
        return new Check(message, false)
    }

    def valid() {
        return new Check("Valid.", false)
    }

    void check(@DelegatesTo(strategy=Closure.DELEGATE_ONLY, value=CheckSpec) Closure cl) {
        // ...
    }

}
