package com.oe.solver.oscar

import com.oe.ziel.domain.solver.SolverConfiguration
import com.oe.ziel.domain.{Resource, TaskAllocation}


class OE {


  trait OEAssertResult{
    val valid : Boolean
    val message : String
  }

  case class OEAssertOnSomething(valid : Boolean, message : String) extends OEAssertResult
  case class ResourceAssert(valid : Boolean, message : String, resource: Resource) extends OEAssertResult


  trait OEAssert[T <: OEAssertResult] {
    val isValid:OEAssertResult
  }

  abstract class OEAssertOnPlanning(val solverConfiguration:SolverConfiguration) extends OEAssert[OEAssertResult]

  abstract class OEAssertOnResource(override val solverConfiguration:SolverConfiguration, val resource:Resource) extends OEAssertOnPlanning(solverConfiguration = solverConfiguration)

  abstract class OEAssertOnResourceAllocation(override val solverConfiguration:SolverConfiguration, override val resource:Resource, val taskAllocations: List[TaskAllocation]) extends OEAssertOnResource(solverConfiguration = solverConfiguration, resource = resource)

  


  type ResourceAsserter = Resource => ResourceAssert
  type ResourceAllocationAssertion = (Resource, List[TaskAllocation]) => (ResourceAssert)

  case class OETest(
                     solverConfiguration:SolverConfiguration = SolverConfiguration.DEFAULT_CONFIG,
                     expectedTaskAllocation:List[TaskAllocation],
                     actualTaskAllocation:List[TaskAllocation],
                     resourceAsserters:List[ResourceAsserter],
                   ) {


    lazy val allocationsByResource = actualTaskAllocation.groupBy(a => a.resource)

  }





}







