package com.oe.ziel.domain

import org.joda.time.{Duration, Instant, Period}


trait Allocation {
  val startTime:Instant
  val endTime:Instant
}


/**
 *  Represents a
 * @param startTime the time the allocation begins
 * @param endTime the time the allocation finishes
 * @param resource the resource allocated to do this task
 */
case class TaskAllocation(
                           startTime:Instant,
                           endTime:Instant,
                           resource: Resource,
                           work: Work) {

  lazy val duration = new Duration(startTime, endTime)

  // TODO - unsure what this should be referenced i.e. task etc.
  
}
