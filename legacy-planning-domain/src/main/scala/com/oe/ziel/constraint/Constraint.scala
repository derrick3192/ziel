package com.oe.ziel.constraint

import com.oe.ziel.domain.{SkillLevel, Work}
import org.joda.time.{Duration, Instant}

sealed trait Constraint {
}

trait ConstraintOnUniVar[T] extends Constraint {
  def valid(t : T): Boolean
}

sealed trait ConstraintOnWork extends Constraint {
  def work : Work
}


case class ConstraintOnMaxWorkDuration(work : Work, duration : Duration) extends ConstraintOnWork {

}

case class ConstraintOnSkillLevel(work : Work, minMajor : Option[Int], minMinor : Option[Int], minPatch : Option[Int]) extends ConstraintOnWork {
  def valid(t: SkillLevel): Boolean = {
    minMajor.map(major => t.major >= major).getOrElse(true) &&
      minMinor.map(minor => t.minor >= minor).getOrElse(true) &&
      minPatch.map(patch => t.patch >= patch).getOrElse(true)
  }
}

case class ConstraintOnEarliestStartTime(work : Work, earliestStartTime : Instant) extends ConstraintOnWork {
//  override def valid(t: Instant): Boolean = !(t isBefore earliestStartTime)
}

case class ConstraintOnLatestStartTime(work : Work, latestStartTime : Instant) extends ConstraintOnWork {
//  override def valid(t: Instant): Boolean = !(t isAfter latestStartTime)
}

case class ConstraintOnEarliestFinishTime(work : Work, earliestFinishTime : Instant) extends ConstraintOnWork {
//  override def valid(t: Instant): Boolean = !(t isBefore earliestFinishTime)
}

case class ConstraintOnLatestFinishTime(work : Work, latestFinishTime : Instant) extends ConstraintOnWork {
//  override def valid(t: Instant): Boolean = !(t isAfter latestFinishTime)
}