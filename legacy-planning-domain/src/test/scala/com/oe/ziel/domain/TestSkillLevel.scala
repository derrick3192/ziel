package com.oe.ziel.domain

import org.assertj.core.api.Assertions._
import org.junit.jupiter.api.Test





class TestSkillLevel {

  @Test
  def testEqual(): Unit = {
    val a = SkillLevel(1,1,1)
    val b = SkillLevel(1,1,1)
    assertThat(a === b).isTrue
    assertThat(a !== b).isFalse
    assertThat(a < b).isFalse
    assertThat(a > b).isFalse
  }

  @Test
  def testMinorSkillABiggerThanSkillB(): Unit = {
    val a = SkillLevel(1,1,1)
    val b = SkillLevel(1,2,1)
    assertThat(a === b).isFalse
    assertThat(a !== b).isTrue
    assertThat(a < b).isTrue
    assertThat(a > b).isFalse
  }

  @Test
  def testMajorSkillABiggerThanSkillB(): Unit = {
    val a = SkillLevel(1,1,1)
    val b = SkillLevel(2,1,1)
    assertThat(a === b).isFalse
    assertThat(a !== b).isTrue
    assertThat(a < b).isTrue
    assertThat(a > b).isFalse
  }

  @Test
  def testBGreaterThanOrEqual():Unit = {
    val a = SkillLevel(1,1,1)
    val b = SkillLevel(2,1,1)
    assertThat(a >= b).isFalse
    assertThat(a <= b).isTrue
  }

  @Test
  def testAGreaterThanOrEqual():Unit = {
    val a = SkillLevel(5,1,1)
    val b = SkillLevel(2,1,1)
    assertThat(a >= b).isTrue
    assertThat(a <= b).isFalse
  }

}
