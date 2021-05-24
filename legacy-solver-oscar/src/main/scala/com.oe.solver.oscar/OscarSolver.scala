package com.oe.solver.oscar

import com.oe.ziel.DEFAULTS
import com.oe.ziel.domain.solver.{Solver, SolverConfiguration}
import com.oe.ziel.domain.{Resource, TaskAllocation, Work}
import oscar.cp._

import scala.util.Random

//

class OscarSolver(
                   val resources:List[Resource],
                   val works:List[Work],
                   val solverConfiguration: SolverConfiguration) extends CPModel with Solver {


  /** Standard members */

  override def plan: List[TaskAllocation] ={

    val noResources = this.resources.length


    val nTasks = this.works.size
    val durationsData = Array.tabulate(nTasks)(w => works(w).amount)
    val horizon = durationsData.sum // horizon is used as the maximum value here

    val resourcesVars = Array.fill(nTasks)(CPIntVar(0 to (noResources - 1 )))

    val durations = Array.tabulate(nTasks)(t => CPIntVar(durationsData(t)))
    val starts = Array.tabulate(nTasks)(t => CPIntVar(0 to horizon - durations(t).min))
    val ends = Array.tabulate(nTasks)(t => starts(t) + durations(t))

    /** Select possible resources for a given task */
    for (i <- 0 until nTasks) {
      val work = works(i)
      val resourceIndexesToDoWork = resourcesForWork(work).map(resources.indexOf(_))
      assert(resourceIndexesToDoWork.size != 0)
      resourcesVars(i) = CPIntVar(resourceIndexesToDoWork)
    }

    /** NOTE DEMANDS NOT USED AT THE MOMENT */
    //val demands = Array.fill(nTasks)(one)

    val makeSpan = maximum(ends)


    // Resources - should this be for each resource which is capable of doing a task?
    for (r <- 0 until noResources) {

      // all possible start times of all
      // resources = the resources which the task can be completed
      add(unaryResource(starts, durations, ends, resourcesVars, r))
    }


    // dependent tasks constraint
    for (i <- 0 until nTasks) {
      val work = works(i)
      val startOfWork = starts(i)
      for (preTask <- work.dependencies) {
        val p = works.indexOf(preTask)
        val endOfDependency = ends(p)
        add(endOfDependency <= startOfWork)
      }
    }

    //val t1 = CPIntVar(1 to 3)
    //val t2 = CPIntVar(1 to 3)

    //val r = t1 === 3
    //val r2 = t1 >= t2

    /** NOTE DEMANDS NOT USED AT THE MOMENT */
    //add(maxCumulativeResource(starts, durations, ends, demands, CPIntVar(2)))

    minimize(makeSpan) search {
      //    splitLastConflict(resources ++ starts)
      splitLastConflict(starts ++ resourcesVars, i => i)
    }



    var durationsSolution:Array[Int] = Array.tabulate(nTasks)(i => i)
    var resourcesSolution:Array[Int] = Array.tabulate(nTasks)(i => i)
    var startsSolution:Array[Int] = Array.tabulate(nTasks)(i => i)



    def printProblem() = {
      println("Makespan of " + makeSpan.value)
      for (t <- 0 until nTasks) {
        print("Task " + t)
        print(" of duration " + durations(t).value)
        print(" is executed on resource " + resourcesVars(t).value)
        println(" at time " + starts(t).value)
      }

      durationsSolution = Array.tabulate(nTasks)(i => durations(i).value)
      resourcesSolution = Array.tabulate(nTasks)(i => resourcesVars(i).value)
      startsSolution = Array.tabulate(nTasks)(i => starts(i).value)

      println()
    }

    onSolution {
      printProblem()
    }

    println(start())

    println("Finished")
    println("Final Solution")

    def printSolution(): Unit = {
      for (t <- 0 until nTasks) {

        val work = works(t)
        val resource = resources(resourcesSolution(t))

        print("Task " + work.display)
        print(" of duration " + durationsSolution(t))
        print(" is executed on resource " + resourcesSolution(t) +  " skills: " + resource.skills.map(_._1))
        println(" at time " + startsSolution(t))
      }
    }

    printSolution()

    val allocations = ( 0 until nTasks ).toList map(i => TaskAllocation(
      solverConfiguration.grid.instant(startsSolution(i)),
      solverConfiguration.grid.instant(startsSolution(i).intValue() + durations(i).value),
      resources(resourcesSolution(i)),
      works(i)
    ))

    allocations

  }
}