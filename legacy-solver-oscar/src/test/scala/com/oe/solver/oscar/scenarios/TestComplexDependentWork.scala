package com.oe.solver.oscar.scenarios

import com.oe.solver.oscar.OscarSolver
import com.oe.ziel.domain.solver.SolverConfiguration
import com.oe.ziel.domain.{Resource, Work}
import org.junit.jupiter.api.Test

@Test
class TestComplexDependentWork extends AbstractBaseSolverTest {


  @Test
  def testIsWorkDependent():Unit = {

      val workAmountA = 8
      val workAmountB = 5
      val workAmountC = 3

      val resourceA = Resource(skills=List(SKILL_A))
      val resourceB = Resource(skills=List(SKILL_B, SKILL_C))

      val workA = Work(amount = workAmountA, requiredSkills = List(SKILL_A))
      val workB = Work(amount = workAmountB, requiredSkills = List(SKILL_B))
      val workC = Work(amount = workAmountC, requiredSkills = List(SKILL_C), dependencies = List(workA, workB))

      val resources = List(resourceA, resourceB)
      val works = List(workA, workB, workC)

      val oeSolver = new OscarSolver(resources, works, SolverConfiguration.DEFAULT_CONFIG)
      val sol = oeSolver.plan

      println(sol)
  }

}
