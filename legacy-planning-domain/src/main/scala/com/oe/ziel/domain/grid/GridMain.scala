package com.oe.ziel.domain.grid

import com.oe.ziel.DEFAULTS
import org.joda.time.DateTimeZone

object GridMain {


  def main(args: Array[String]): Unit = {
    println("\n\n\n-------RUNNING------\n\n\n")


    val g1 = GridStepEachHour(DEFAULTS.startOfDefaultWorkDay, DEFAULTS.endOfDefaultWorkDay)

    println(s"g1.duration=${g1.duration}")
    (0 to 8).foreach(i => println(s"g1.t($i)=${g1.t(i)}"))
    println ("\n-------\n")
    (0 to 8).foreach(i => println(s"g1.h($i)=${g1.h(i)}"))
    println ("\n-------\n")
    (0 to 8).foreach(i => println(s"g1.instant($i)=${g1.instant(i).toDateTime(DEFAULTS.AEST)}"))
    println ("\n-------\n")
  }

}
