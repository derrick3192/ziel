package com.oe.ziel.domain.solver

import com.oe.ziel.DEFAULTS
import com.oe.ziel.domain.grid.GridBuilder
import org.joda.time.{Duration, Instant}

case class SolverConfiguration(
                                earliestStartTime: Option[Instant] = None,
                                latestFinishTime: Option[Instant] = None,
                                minAllocation: Option[Duration] = None,
                                maxAllocation: Option[Duration] = None,
                                workDayStart: Option[Int] = None,
                                workDayEnd: Option[Int] = None
                              ) {

  val grid = GridBuilder.grid(earliestStartTime.get, latestFinishTime.get)


}

object SolverConfiguration {

  implicit def implicitSome[T] = (i:T) => Some(i)

  val DEFAULT_CONFIG = SolverConfiguration(
    earliestStartTime = Some(DEFAULTS.DEFAULT_EARLIEST_START),
    latestFinishTime = Some(DEFAULTS.DEFAULT_LATEST_END),
    minAllocation = DEFAULTS.SOLVER.MIN_ALLOCATION,
    maxAllocation = DEFAULTS.SOLVER.MAX_ALLOCATION,
    workDayStart = DEFAULTS.SOLVER.START_HOUR,
    workDayEnd = DEFAULTS.SOLVER.END_HOUR,
  )

}
