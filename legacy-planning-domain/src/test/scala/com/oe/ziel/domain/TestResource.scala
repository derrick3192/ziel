package com.oe.ziel.domain

import org.assertj.core.api.Assertions._
import org.junit.jupiter.api.Test




class TestResource {

  @Test
  def canWorkReturnsTrueWhenCodesMatch(): Unit = {
    val resource = Resource(skills = List(Skill(SkillCode("A"))))
    val work = Work(1, requiredSkills = List(Skill(SkillCode("A"))))
    assertThat(resource canWork work) isTrue
  }

  @Test
  def canWorkReturnsFalseWhenCodesDontMatch(): Unit = {
    val resource = Resource(skills = List(Skill(SkillCode("B"))))
    val work = Work(1, requiredSkills = List(Skill(SkillCode("A"))))
    assertThat(resource canWork work) isFalse
  }

  @Test
  def canWorkReturnsTrueWhenLevelEquals(): Unit = {
    val resource = Resource(skills = List(Skill(SkillCode("A"), skillLevel = SkillLevel(1,1,1))))
    val work = Work(1, requiredSkills = List(Skill(SkillCode("A"), skillLevel = SkillLevel(1,2,1))))
    assertThat(resource canWork work) isFalse
  }

  @Test
  def canWorkReturnsFalseWhenLevelEquals(): Unit = {
    val resource = Resource(skills = List(Skill(SkillCode("A"), skillLevel = SkillLevel(1,1,1))))
    val work = Work(1, requiredSkills = List(Skill(SkillCode("A"), skillLevel = SkillLevel(1,2,1))))
    assertThat(resource canWork work) isFalse
  }


}
