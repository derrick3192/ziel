package com.oe.ziel.dsl.booking.gantt.constraint

import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsl.booking.gantt.Spec
import org.joda.time.Instant

abstract class ConstraintsSpec implements Spec {

    def addWork(Work work) {

    }

    void minStartTime(Instant min) {

    }

}
