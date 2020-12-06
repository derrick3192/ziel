package com.oe.ziel.optaplanner;

import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

public class ResourceConstraintConfiguration {

    public static final String PRIORITY_ORDER = "Priority";

    public static final String RESOURCE_CONFLICT = "Resource Conflict";

    public static final String REQUIRED_SKILL = "Required Skill";

    protected Long id;

    @ConstraintWeight(RESOURCE_CONFLICT)
    private HardMediumSoftScore resourceConflict = HardMediumSoftScore.ofHard(10);

    @ConstraintWeight(REQUIRED_SKILL)
    private HardMediumSoftScore requiredSkill = HardMediumSoftScore.ofHard(10);

    @PlanningId
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HardMediumSoftScore getResourceConflict() {
        return resourceConflict;
    }

    public void setResourceConflict(HardMediumSoftScore resourceConflict) {
        this.resourceConflict = resourceConflict;
    }

    public HardMediumSoftScore getRequiredSkill() {
        return requiredSkill;
    }

    public void setRequiredSkill(HardMediumSoftScore requiredSkill) {
        this.requiredSkill = requiredSkill;
    }
}
