package com.oe.ziel.domain

/**
 * Skill level represents he level of a ability a resource has to do a task for a given Skill. A resource must meet the skill requirements of the task in order to be assigned to it.
 * @param major resources which do not have this level can never be scheduled to do this task
 * @param minor a resource may still do a task, even if its minor level is not sufficient, subject to a penalty
 * @param patch patch is disregarded but can be used for documenting changes which have no effect
 */
case class SkillLevel(major: Int, minor: Int, patch: Int) {


  def === (skillLevel: SkillLevel) = major == skillLevel.major && minor == skillLevel.minor

  def !== (skillLevel: SkillLevel) = ! (===(skillLevel))

  // GT
  def > (skillLevel: SkillLevel) = major > skillLevel.major || major == skillLevel.major && minor > skillLevel.minor

  // LT
  def < (skillLevel: SkillLevel) = major < skillLevel.major || major == skillLevel.major && minor < skillLevel.minor

  // GTE
  def >= (skillLevel: SkillLevel) = ===(skillLevel) || >(skillLevel)

  // LTE
  def <= (skillLevel: SkillLevel) = ===(skillLevel) || <(skillLevel)

}