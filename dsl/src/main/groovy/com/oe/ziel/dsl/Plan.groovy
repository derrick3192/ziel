package com.oe.ziel.dsl

import com.oe.ziel.domain.Work

class Plan {

    private Work work


    Work work(@DelegatesTo(value = Plan, strategy = Closure.DELEGATE_FIRST) closure) {
        work = new Work();
    }

}
