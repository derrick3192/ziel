package com.oe.ziel.domain

object AnimalTest {


  sealed trait Animal {
    def makeNoise:Unit
  }

  sealed trait Bird extends Animal {
    def flying:Boolean
  }

  final case class Penguin() extends Bird {
    override def flying: Boolean = false
    override def makeNoise: Unit = println("quickyquickytooto")
  }

  final case class Pigeon() extends Bird {
    override def flying: Boolean = true
    override def makeNoise: Unit = println("brrrrr.brrrr")
  }

  sealed trait Dog extends Animal {
    def bark():Unit
    def makeNoise = bark()
  }

  final case class Husky() extends Dog() {
    override def bark(): Unit = println("WOoooo00o0o0")
  }

  final case class Chiwawa() extends Dog() {
    override def bark(): Unit = println("Yap yap")
  }


  /**
   * Compile safe method handles all cases
   * @param animal
   */
  def crazyMatch(animal: Animal): Unit = {
    animal match {
      case bird: Bird  => println("I know this is a Bird") ; animal.asInstanceOf[Bird] match {
        case Penguin() => println("I know this is a Penguin") ;
        case Pigeon() => println("Here is a pigeon")
      }
      case dog: Dog => println("I know this is a Dog") ; animal.asInstanceOf[Dog] match {
        case Husky() => println("I know this is a Husky") ;
        case Chiwawa() => println("I know this is a Husky") ;
      }
    }
    println("---")
  }


  def main(args: Array[String]): Unit = {


    val animal:Animal = Penguin()

    val dog:Dog = Chiwawa()


    println("crazy animal/bird/penguin")
    crazyMatch(animal)

    println("crazy dog")
    crazyMatch(dog)



  }


}

