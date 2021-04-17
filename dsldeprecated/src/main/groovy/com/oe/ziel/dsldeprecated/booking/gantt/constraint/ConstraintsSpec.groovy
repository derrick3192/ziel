package com.oe.ziel.dsldeprecated.booking.gantt.constraint

import com.oe.ziel.domain.work.Work
import com.oe.ziel.dsldeprecated.booking.gantt.Spec
import org.joda.time.Instant

abstract class ConstraintsSpec implements Spec {

    def addWork(Work work) {

    }

    void minStartTime(Instant min) {

    }

}
