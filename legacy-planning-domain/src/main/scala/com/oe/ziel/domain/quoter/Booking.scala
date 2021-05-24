package com.oe.ziel.domain.quoter

import com.oe.ziel.domain.Work


case class UserInput[+T <: AnyVal](serviceOption: ServiceOption[T], userSelection : T)




trait Booking {
  def userInputs: Set[UserInput[AnyVal]]
  def serviceOffering: ServiceOffering
  def work = serviceOffering.work(this)
}


case class BookingSingleServiceOffering(serviceOffering: ServiceOffering, userInputs: Set[UserInput[AnyVal]]) extends Booking {

}
