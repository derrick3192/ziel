package com.oe.ziel.domain

import org.joda.time.{Duration, Instant}
import squants.Time


case class Variable[VALUE](val name:String, val value:VALUE, val guid:String) {
}

object Variable {
  def apply[VALUE](name:String, value:VALUE): Variable[VALUE] = Variable[VALUE](name, value, java.util.UUID.randomUUID.toString)
}



object Main2 {
  def main(args: Array[String]): Unit = {
    val v1 = Variable[Int]("test", 23)
    val v2 = Variable[Int]("test", 23, "guid")


    val v1Copy = v1.copy(value = 23)

    println(v1)
    println(v2)
    println(v1Copy)


  }
}




/**
 * UNCOMMMENT
 */
//abstract case class Constraint[VALUE]() {
//  def score:VALUE // negative is bad, positive is good
//  def valid:Boolean
//}
//
//abstract case class ConstraintOnTask[VALUE](val task: Task) extends Constraint[VALUE] {}
//
//abstract case class PredicateConstraint[VALUE](variable: Variable[VALUE], value: VALUE) extends Constraint[VALUE] {
//  def predicate: (Variable[VALUE],VALUE) => VALUE
//  override def score = this.predicate(this.variable, this.value)
//}
//
//
//
//

///**
// *
// * @tparam VAR type of the variables which have the constraint TODO - is this needed? They should all be the same anyway
// * @tparam VALUE the value of the constraint negative is bad, positive is good
// */
//abstract case class Constraint[VAR, VALUE]() {
//  def category:Long = 1
//  def score:VALUE // negative is bad, positive is good
//  def valid:Boolean
//}
//
//abstract case class PredicateTypeConstraint[VAR, VALUE]() extends Constraint[VAR, VALUE] {
//
//}
//
//
//
//
//trait ConstraintOfSingleInstant extends Constraint[Instant, Time] {
//  val instant: Instant
//
//  def score(instant:Instant):Long
//  override def score = score(instant)
//
//  def valid(instant:Instant):Long
//  override def valid = score()
//
//}
//
//
//
//
//abstract sealed case class ConstraintOnTask(val task: Task) extends Constraint {}
//
//
//sealed case class ConstraintOnTaskEarliestStartTime(override val task: Task, instant: Instant) extends ConstraintOnTask(task) with ConstraintOfSingleInstant {
//  override def valid(instant: Instant): Boolean = this.instant isAfter instant
//  override def score: Long = new Duration(instant, this.instant).getMillis
//}
//
//
//sealed case class ConstraintOnTaskLatestStartTime(override val task: Task, val instant: Instant) extends ConstraintOnTask(task) with ConstraintOfSingleInstant {
//
//}
//
//
//sealed case class ConstraintOnTaskEarliestFinishTime(override val task: Task, val instant: Instant) extends ConstraintOnTask(task) with ConstraintOfSingleInstant
//sealed case class ConstraintOnTaskLatestFinishTime(override val task: Task, val instant: Instant) extends ConstraintOnTask(task) with ConstraintOfSingleInstant
//
//
//
//

