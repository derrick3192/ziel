package com.oe.ziel

import com.oe.ziel.domain.TaskAllocation
import org.joda.time.{DateTime, DateTimeZone, Duration}

object DEFAULTS {

  val PERFORMANCE = 1.00
  val COST = 1



  val AEST = DateTimeZone.forOffsetHours(10)
  DateTimeZone.setDefault(AEST);

  val DEFAULT_YEAR = 2019
  val DEFAULT_MONTH = 4
  val DEFAULT_DAY = 3
  val DEFAULT_MINUTE_OF_HOUR = 0

  val WORKING_DAY_START = 9
  val WORKING_DAY_END = 17

  def day(hourOfDay : Int) = new DateTime(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY, hourOfDay, DEFAULT_MINUTE_OF_HOUR).toInstant

  def startOfDefaultWorkDay = day(WORKING_DAY_START)
  def endOfDefaultWorkDay = day(WORKING_DAY_END)

  def assertAllocationsContains(expected : Set[TaskAllocation])(implicit actualAllocations : Set[TaskAllocation]) = Set()


  val DEFAULT_EARLIEST_START = new DateTime(2019, 1, 1, 0, 0).toInstant
  val DEFAULT_LATEST_END = new DateTime(2019, 12, 1, 0, 0).toInstant


  object ALLOCATION {
    val EARLIEST_START = new DateTime(2019, 1, 1, 0, 0).toInstant
  }

  object SOLVER {
    val MIN_ALLOCATION = Duration.standardHours(3)
    val MAX_ALLOCATION = Duration.standardHours(8)
    val START_HOUR = 9
    val END_HOUR = 17
  }


}
