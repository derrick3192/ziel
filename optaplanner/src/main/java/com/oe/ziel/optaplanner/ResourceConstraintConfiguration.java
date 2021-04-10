package com.oe.ziel.optaplanner;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;



@ConstraintConfiguration(constraintPackage = "com.oe.ziel.optaplanner")
public class ResourceConstraintConfiguration extends AbstractPersistable {

    public static final String RESOURCE_CONFLICT = "Resource Conflict";

    public static final String REQUIRED_SKILL = "Required Skill";

    public static final String PRIORITY_ORDER = "Priority";

    @ConstraintWeight(RESOURCE_CONFLICT)
    private HardMediumSoftScore resourceConflict = HardMediumSoftScore.ofHard(1);

    @ConstraintWeight(REQUIRED_SKILL)
    private HardMediumSoftScore requiredSkill = HardMediumSoftScore.ofMedium(1);

    @ConstraintWeight(PRIORITY_ORDER)
    private HardMediumSoftScore priorityOrder = HardMediumSoftScore.ofSoft(1);

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

    public HardMediumSoftScore getPriorityOrder() {
        return priorityOrder;
    }

    public void setPriorityOrder(HardMediumSoftScore priorityOrder) {
        this.priorityOrder = priorityOrder;
    }
}
