package com.oe.ziel.domain

import org.assertj.core.api.Assertions._
import org.junit.jupiter.api.Test





class TestSkill {

  @Test
  def testEqual(): Unit = {
    val a = Skill(skillCode = SkillCode("A"), skillLevel = SkillLevel(1,1,1))
    val b = Skill(skillCode = SkillCode("A"), skillLevel = SkillLevel(1,1,1))
    assertThat(a.isProficientTo(b)).isTrue
    assertThat(a.isDeficientTo(b)).isFalse
  }

  @Test
  def testCodeDifferent(): Unit = {
    val a = Skill(skillCode = SkillCode("A"), skillLevel = SkillLevel(1,1,1))
    val b = Skill(skillCode = SkillCode("B"), skillLevel = SkillLevel(1,1,1))
    assertThat(a.isProficientTo(b)).isFalse
    assertThat(a.isDeficientTo(b)).isTrue
  }

}
