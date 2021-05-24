package com.oe.ziel.domain

import org.joda.time.Instant

trait WorkOrder {
  def minStartTime:Instant
  def maxStartTime:Instant
  def minFinishTime:Instant
  def maxFinishTime:Instant
}
