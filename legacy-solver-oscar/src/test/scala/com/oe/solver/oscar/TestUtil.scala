package com.oe.solver.oscar

import com.oe.ziel.domain.TaskAllocation
import org.joda.time.{DateTime, DateTimeZone}

object TestUtil {

  private val AEST = DateTimeZone.forOffsetHours(10)
  DateTimeZone.setDefault(AEST);

  val DEFAULT_YEAR = 2019
  val DEFAULT_MONTH = 4
  val DEFAULT_DAY = 3
  val DEFAULT_MINUTE_OF_HOUR = 0

  val WORKING_DAY_START = 9
  val WORKING_DAY_END = 17

  def day(hourOfDay : Int) = new DateTime(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY, hourOfDay, DEFAULT_MINUTE_OF_HOUR)

  def startOfDefaultWorkDay = day(WORKING_DAY_START).toInstant
  def endOfDefaultWorkDay = day(WORKING_DAY_END).toInstant

  def assertAllocationsContains(expected : Set[TaskAllocation])(implicit actualAllocations : Set[TaskAllocation]) = Set()



}
