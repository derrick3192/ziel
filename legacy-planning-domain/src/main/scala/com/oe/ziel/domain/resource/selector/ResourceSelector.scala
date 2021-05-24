package com.oe.ziel.domain.resource.selector

import com.oe.ziel.domain.{Resource, ResourceRepository, Skill, Work}
import com.softwaremill.macwire._

trait ResourceSelector {
  def selectedResources:List[Resource]
}

class ResourceSelectorByAbility(resourceRepository: ResourceRepository, skills : Set[Skill]) extends ResourceSelector {
  override def selectedResources: List[Resource] = resourceRepository.withSkillRequirement(skills)
}
