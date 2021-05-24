package com.oe.solver.oscar

import org.assertj.core.api.Assertions._
import org.junit.jupiter.api.Test
import com.oe.ziel.domain.{Resource, Skill, SkillCode, SkillLevel, TaskAllocation, Work}
import org.joda.time.{DateTime, Duration, Instant, LocalDate}
import TestUtil._
import com.oe.ziel.domain.solver.SolverConfiguration
import org.junit.jupiter.api.Test


class TestOscarSolver {

  implicit def skill2Some = (i:Skill) => Some(i)


  // DEFAULT AMOUNT OF WORK
  val WORK = 8

  // SKILLS
  private val SKILL_A = Skill(SkillCode("A"))
  private val SKILL_B = Skill(SkillCode("B"))
  private val SKILL_C = Skill(SkillCode("C"))
  private val SKILL_D = Skill(SkillCode("D"))

  private val SKILL_AA = Skill(SkillCode("A"), SkillLevel(1,1,1), 2.0)

  // RESOURCES
  private val resourceA = Resource(skills=List(SKILL_A))
  private val resourceB = Resource(skills=List(SKILL_B))
  private val resourceC = Resource(skills=List(SKILL_C))
  private val resourceD = Resource(skills=List(SKILL_D))

  private val resourceAA = Resource(skills=List(SKILL_AA))

  // WORK
  private val workA = Work(requiredSkills=List(SKILL_A), amount = WORK)
  private val workB = Work(requiredSkills=List(SKILL_B), amount = WORK)
  private val workC = Work(requiredSkills=List(SKILL_C), amount = WORK)
  private val workD = Work(requiredSkills=List(SKILL_D), amount = WORK)

  //@Test
  def resourcesAndTasksMatch(): Unit = {

    val resources = List(resourceD, resourceC, resourceB, resourceA)
    val works = List(workA, workB, workC, workD)

    val oeSolver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)

    println(oeSolver.solution(0))
    assertThat(oeSolver.areAllResourcesHavingWork).isTrue()
    assertThat(oeSolver.areAllResourcesAreFullyUtilized).isTrue()
  }

  @Test
  def cannotDoTaskAssertionError(): Unit = {
    val resourceA = Resource(skills=List(SKILL_A))
    val workB = Work(requiredSkills=List(SKILL_B), amount = WORK)
    val resources = List(resourceA)
    val works = List(workB)
    val oeSolver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)
    assertThat(oeSolver.isFeasible).isFalse
  }


  @Test
  def simpleCase01():Unit = {

    val workAmountA = 8
    val workAmountB = 5
    val workAmountC = 3

    val resourceA = Resource(skills=List(SKILL_A))
    val resourceB = Resource(skills=List(SKILL_B, SKILL_C))

    val workA = Work(amount = workAmountA, requiredSkills = List(SKILL_A))
    val workB = Work(amount = workAmountB, requiredSkills = List(SKILL_B))
    val workC = Work(amount = workAmountC, requiredSkills = List(SKILL_C))

    val resources = List(resourceA, resourceB)
    val works = List(workA, workB, workC)

    val oeSolver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)
    val sol = oeSolver.plan

    println(sol)
  }

  @Test
  def oneWorkerIsQuickerThanAnother(): Unit ={

    val resources = List(resourceA, resourceAA)
    val works = List(workA)

    val oeSolver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)

    println(oeSolver.solution)
  }

  @Test
  def oneResourceAllocated(): Unit = {
    val resources = List(resourceA, resourceAA)
    val works = List(workA)

    val oeSolver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)
    assertThat(oeSolver.areAllResourcesAreFullyUtilized).isFalse()
  }

}
