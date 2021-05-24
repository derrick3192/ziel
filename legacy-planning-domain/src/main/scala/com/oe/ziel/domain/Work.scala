package com.oe.ziel.domain

import org.joda.time.{Duration, Instant}

import scala.collection.mutable.ListBuffer

case class WorkPriority(priority: Int)
//case class WorkType(workType : String)
//case class WorkAmount(workAmount : Int) extends AnyVal


/**
 * Represents some work to be completed on a Resource.
 * Note the only parameter which needs to be defined is the <code>amount</code> field.
 * All other fields can be taken from the globally defined value.
 *
 * @param amount required
 * @param priority <i>global</i>
 * @param requiredSkills <i>global</i>
 * @param earliestStartTime <i>global</i>
 * @param latestStartTime <i>global</i>
 * @param earliestFinishTime <i>global</i>
 * @param latestFinishTime <i>global</i>
 * @param maxDuration <i>global</i>
 */
case class Work(
                 amount: Int,
                 // WorkType: WorkType,

                 name: String = "",

                 /** Optionally specify a priority */
                 priority: Option[WorkPriority] = None,

                 /** Constraints */

                 requiredSkills: List[Skill] = List(),

                 // optional parameters
                 earliestStartTime:Option[Instant] = None,
                 latestStartTime:Option[Instant] = None,
                 earliestFinishTime:Option[Instant] = None,
                 latestFinishTime:Option[Instant] = None,

                 // max duration
                 maxDuration:Option[Duration] = None,

                 dependencies:ListBuffer[Work] = ListBuffer()

               ) {
  def display = {
    if (name.isEmpty) {
      s"WORK ${requiredSkills.map(s => s.skillCode)}"
    } else {
      name
    }
  }
}
