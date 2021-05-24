package com.oe.solver.oscar

import com.oe.ziel.domain.{Resource, Skill, SkillCode, TaskAllocation, Work}
import com.oe.ziel.domain.solver.SolverConfiguration
import org.joda.time.{Duration, Instant}

import scala.collection.JavaConverters._
import scala.collection.JavaConverters.{collectionAsScalaIterableConverter, seqAsJavaListConverter}
/**
 * Compatibility layer between legacy solvers and new planning domain
 */
class OscarSolverJava(val minStartTime : Instant, val maxFinishTime : Instant) {


  def schedule(
                resourcesJava : java.util.List[com.oe.ziel.domain.resource.Resource],
                worksJava : java.util.List[com.oe.ziel.domain.work.Work]
              ) : java.util.List[com.oe.ziel.domain.booking.solver.TaskAllocation] = {

    // convert the resources
    val resourcesMap = resourcesJava.asScala.map(
      r => r -> new com.oe.ziel.domain.Resource(
        r.getSkills.asScala.map(e => (SkillCode(e._1) -> Skill(e._1))).toMap,
        Set()
      )
    ).toMap
    val resourcesInverted = (Map() ++ resourcesMap.map(_.swap))

    val resources = resourcesMap.toList.map { case (_, resourceScala) => resourceScala}

    val worksMap: Map[com.oe.ziel.domain.work.Work, com.oe.ziel.domain.Work] = worksJava.asScala.map(
      w => w -> com.oe.ziel.domain.Work(
        w.getDuration.getStandardHours.intValue(),
        w.getName,
        None,
        w.getRequiredSkills.values().asScala.map(skill => Skill(skill.getSkillCode)).toList,
        Option(w.getMinStartTime),
        Option(w.getMaxStartTime),
        Option(w.getMinFinishTime),
        Option(w.getMaxFinishTime),
        None
      )
    ).toMap
    val worksInverted = (Map() ++ worksMap.map(_.swap))

    // set dependencies
    worksMap foreach  {case (workJava, workScala) => workJava.getDependencies.forEach(dep => workScala.dependencies += worksMap(dep))}

    val works = worksMap.toList.map { case (_, workScala) => workScala}

    val solverConfig = SolverConfiguration(earliestStartTime = Some(this.minStartTime), latestFinishTime = Some(this.maxFinishTime), maxAllocation = Some(Duration.standardHours(8)))

    //val solver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)
    val solver = new OscarSolver(resources, works, solverConfig)

    solver.plan().map(
      t => new com.oe.ziel.domain.booking.solver.TaskAllocation(
        t.startTime,
        t.endTime,
        resourcesInverted(t.resource),
        t.work.name)
    ).asJava

  }

}
