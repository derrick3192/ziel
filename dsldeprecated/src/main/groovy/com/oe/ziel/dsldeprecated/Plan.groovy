package com.oe.ziel.dsldeprecated

import com.oe.ziel.domain.work.Work

class Plan {

    private Work work


    Work work(@DelegatesTo(value = Plan, strategy = Closure.DELEGATE_FIRST) closure) {
        work = new Work();
    }

}
