package com.oe.solver.oscar.scenarios

import com.oe.ziel.domain.{Resource, Skill, SkillCode, SkillLevel, Work}

class AbstractBaseSolverTest {

  // DEFAULT AMOUNT OF WORK
  val WORK = 8

  // SKILLS
  protected val SKILL_A = Skill(SkillCode("A"))
  protected val SKILL_B = Skill(SkillCode("B"))
  protected val SKILL_C = Skill(SkillCode("C"))
  protected val SKILL_D = Skill(SkillCode("D"))

  protected val SKILL_AA = Skill(SkillCode("A"), SkillLevel(1,1,1), 2.0)

  // RESOURCES
  protected val resourceA = Resource(skills=List(SKILL_A))
  protected val resourceB = Resource(skills=List(SKILL_B))
  protected val resourceC = Resource(skills=List(SKILL_C))
  protected val resourceD = Resource(skills=List(SKILL_D))

  protected val resourceAA = Resource(skills=List(SKILL_AA))

  // WORK
  protected val workA = Work(requiredSkills=List(SKILL_A), amount = WORK)
  protected val workB = Work(requiredSkills=List(SKILL_B), amount = WORK)
  protected val workC = Work(requiredSkills=List(SKILL_C), amount = WORK)
  protected val workD = Work(requiredSkills=List(SKILL_D), amount = WORK)

}
