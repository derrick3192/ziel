package com.oe.ziel.domain.grid

import com.oe.ziel.DEFAULTS
import com.oe.ziel.domain.Work
import org.joda.time.{Duration, Instant, Period}



sealed trait Grid {
  def steps : Int
  def start: Instant
  def end: Instant

  // concrete methods
  def period = new Period(start, end)
  def duration = new Duration(start, end)
  def iterable : Iterable[Int] = (0 to steps).toStream // TODO - can do allot more here

  /** Returns [0, 1] where the allocation sits on the entire spectrum*/
  def t(i : Int) = i.toDouble / steps
  /** Returns the duration from the start time */
  def h(i : Int) = duration.multipliedBy(i).dividedBy(steps)
  /** Returns an instant at Step i */
  def instant(i : Int) = start.plus(h(i))
}


case class GridStepEachHour(
                         start : Instant,
                         end : Instant) extends Grid {

  override def steps: Int = duration.getStandardHours.toInt

}

object GridBuilder {

  def grid(start:Instant, end:Instant) = GridStepEachHour(start, end)

  def gridForWork(work : Work):Grid = {
    val start = work.earliestStartTime.getOrElse(DEFAULTS.DEFAULT_EARLIEST_START)
    val end = work.latestFinishTime.getOrElse(DEFAULTS.DEFAULT_LATEST_END)
    GridStepEachHour(start, end)
  }

}