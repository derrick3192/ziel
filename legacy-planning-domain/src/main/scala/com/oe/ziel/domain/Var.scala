package com.oe.ziel.domain


trait Constraint[V <: VarInt[V]] {
  def vars:Set[V] = Set()
  def valid:Boolean
}

trait UnaryResourceConstraint[V <: VarInt[V]] extends Constraint[V] {
  def resource : Resource
  def work : Work
}

trait VarInt[T <: VarInt[T]] {

  def unary_-():T

  def + (y: T):T
  def - (y: T):T = this + (-y) // this is a yee old trick from the great Martin Odersky
  def / (y: T):T
  def * (y: T):T

  def <(y: T):Constraint[T]
  def <(y: Int):Constraint[T]
  def >(y: T):Constraint[T]
  def >(y: Int):Constraint[T]
  def <=(y: T):Constraint[T]
  def <=(y: Int):Constraint[T]
  def >=(y: T):Constraint[T]
  def >=(y: Int):Constraint[T]
  def ===(v: Int):Constraint[T]
  def ===(y: T):Constraint[T]
  def !==(v: Int):Constraint[T]
  def !==(y: T):Constraint[T]

  def value:Int

}

object VarInt {

}
