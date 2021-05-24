package com.oe.ziel.domain



case class Tag(tag : String) extends AnyVal

/**
 * Represents a resource. Resources are the assets of an organisation, they are able to do perform the tasks, so long as they have the required skills
 * @param skills the skill the resource has to do the job
 */
case class Resource(skills: Map[SkillCode, Skill], tags: Set[Tag]) {

  def mySkillLevel(skill : Skill): Option[Skill] = skills.get(skill.skillCode)
  def hasSufficientSkill(skill : Skill): Boolean = mySkillLevel(skill) forall( resourceSkill => resourceSkill.isProficientTo(skill))

  /**
   * Is a resource able to do the specified work? To do the job, the resource must have the required skill level to do
   * the task at hand.
   * @param work the work which the res
   * @return true if the resource can do the work
   */
  def canWork(work : Work) = work.requiredSkills.forall(workMinSkill => skills.get(workMinSkill.skillCode) match {
    case Some(resourceMinSkill) => resourceMinSkill.isProficientTo(workMinSkill)
    case None => false // if the resource does not have the skill return false
  }) // if there are no skills for this job return true


  def duration(work : Work) = for {
    workMinSkill <- work.requiredSkills
    resourceCorrespondingSkill <- skills.get(workMinSkill.skillCode)
  } yield resourceCorrespondingSkill.duration(work.amount)

}

case object Resource {
  def apply(skills: Iterable[Skill] = List(), tags: Set[Tag] = Set()):Resource = {
    Resource(skills = skills map (skill => skill.skillCode -> skill) toMap, tags)
  }
}





trait ResourceRepository {

  type ResourceList = List[Resource]

  /** All Resources */
  def resources : List[Resource]

  /** All Resources */
  def withSkillRequirement(skills: Set[Skill]) : List[Resource]

}