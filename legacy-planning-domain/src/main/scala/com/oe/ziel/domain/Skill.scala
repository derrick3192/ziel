package com.oe.ziel.domain

import com.oe.ziel.DEFAULTS


case class SkillCode(code : String) extends AnyVal


/**
 * Tasks may require skills in order to be completed.
 *
 * @param skillCode the SkillCode must match the task
 * @param skillLevel the level which the resource is at in regards to this skill
 */
case class Skill(
             skillCode : SkillCode,
             skillLevel : SkillLevel = SkillLevel(1,1,1),
             performancePercentage : Double = DEFAULTS.PERFORMANCE, // how quickly the task is done
             costPerWorkUnit : Int = DEFAULTS.COST) {

  def isProficientTo(skill: Skill) = skillCode == skill.skillCode && skillLevel >= skill.skillLevel
  def isDeficientTo(skill: Skill) = !isProficientTo(skill)
  def duration(workAmount : Int) = (workAmount * performancePercentage / 100.0).toInt

}


object Skill {
  def apply(code : String):Skill = Skill(SkillCode(code))
}