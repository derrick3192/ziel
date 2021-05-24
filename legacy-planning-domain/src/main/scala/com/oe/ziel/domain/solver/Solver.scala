package com.oe.ziel.domain.solver

import com.oe.ziel.constraint._
import com.oe.ziel.domain.grid.GridBuilder
import com.oe.ziel.domain.{Resource, TaskAllocation, VarInt, Work}
import org.joda.time.Duration

import scala.collection.Set


trait Solver {

  /** Standard members */
  val resources:List[Resource]
  val works:List[Work]
  val solverConfiguration: SolverConfiguration

  lazy val resourcesForWork :Map[Work, List[Resource]] = works map(w => (w -> resources.filter(r => r.canWork(w)))) toMap
  lazy val canResourcesDoAllTasks: Boolean = !resourcesForWork.exists(_._2.isEmpty)

  lazy val isFeasible:Boolean = canResourcesDoAllTasks

  lazy val solution = plan():List[TaskAllocation]

  lazy val allocationsOfResource: Map[Resource, List[TaskAllocation]] = resources.map(r => (r -> solution.filter(_.resource == r))) toMap
  lazy val totalAllocationDurationForResources: Map[Resource, Duration] = allocationsOfResource map { case (r, al) => (r, al.foldLeft(Duration.ZERO)((d:Duration, a:TaskAllocation) => a.duration.plus(d))) }
  lazy val areAllResourcesHavingWork: Boolean = allocationsOfResource.forall(_._2.nonEmpty)
  lazy val areAllResourcesAreFullyUtilized: Boolean = solverConfiguration.maxAllocation.forall(maxAllocation =>
    totalAllocationDurationForResources.values.forall(a => a.isEqual(maxAllocation))
  )

  def workConstraints(works : Set[Work]): Set[ConstraintOnWork] = works.flatMap(w => {

      val earliestStartTimeConstraint  = w.earliestStartTime.map  (t => ConstraintOnEarliestStartTime(w, t))
      val latestStartTimeConstraint    = w.latestStartTime.map    (t => ConstraintOnLatestStartTime(w, t))
      val earliestFinishTimeConstraint = w.earliestFinishTime.map (t => ConstraintOnEarliestFinishTime(w, t))
      val latestFinishTimeConstraint   = w.latestFinishTime.map   (t => ConstraintOnLatestFinishTime(w, t))

      val maxDurationConstraint = w.maxDuration.map(max => ConstraintOnMaxWorkDuration(w, max))

      Set(
        earliestStartTimeConstraint,
        latestStartTimeConstraint,
        earliestFinishTimeConstraint,
        latestFinishTimeConstraint,
        maxDurationConstraint
      )
    }).flatten

  def plan() : List[TaskAllocation]
}