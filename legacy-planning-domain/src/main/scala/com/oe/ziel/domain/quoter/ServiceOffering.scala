package com.oe.ziel.domain.quoter

import com.oe.ziel.domain.Work


sealed trait ServiceOption[+T]{
  def label : String
  def default : T
  def position : Int
}


trait Bounded[T] {
  def min : T
  def max : T
}


case class ServiceOptionInt(label : String, position : Int, default : Int, min : Int, max : Int) extends ServiceOption[Int] with Bounded[Int]
case class ServiceOptionDouble(label : String, position : Int, default : Double, min : Double, max : Double) extends ServiceOption[Double] with Bounded[Double]
case class ServiceOptionBoolean(label: String, position : Int, default : Boolean) extends ServiceOption[Boolean]
case class ServiceOptionString(label : String, position : Int, default : String) extends ServiceOption[String]


sealed trait ServiceOffering {
  def serviceOptions : Set[ServiceOption[AnyVal]]
  def addOption(serviceOption : ServiceOption[AnyVal]):this.type
  def work(booking : Booking):Set[Work]
}