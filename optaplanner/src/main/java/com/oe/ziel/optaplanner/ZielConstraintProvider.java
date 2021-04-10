package com.oe.ziel.optaplanner;


import static org.optaplanner.core.api.score.stream.Joiners.equal;
import static org.optaplanner.core.api.score.stream.Joiners.filtering;
import static org.optaplanner.core.api.score.stream.Joiners.greaterThan;
import static org.optaplanner.core.api.score.stream.Joiners.lessThan;
import static org.optaplanner.core.api.score.stream.Joiners.overlapping;

import com.oe.ziel.domain.resource.Resource;
import org.joda.time.Duration;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;


// https://github.com/kiegroup/optaplanner/blob/master/optaplanner-examples/src/main/java/org/optaplanner/examples/conferencescheduling/optional/score/ConferenceSchedulingConstraintProvider.java
public abstract class ZielConstraintProvider implements ConstraintProvider {


    /**
     * The function to convert form a difference in a Duration to a Score.
     * @param duration to convert to the score to be used in the optimization problem
     * @return score for the given duration
     */
    abstract int durationToScore(Duration duration);


    @Override
    public Constraint[] defineConstraints(ConstraintFactory factory) {
        return new Constraint[] {
                resourceConflict(factory),
                skillRequirement(factory),
                priorityRequirement(factory)
        };
    }

    private Constraint priorityRequirement(ConstraintFactory factory) {
        return factory.fromUniquePair(TaskAllocation.class)
                .filter((a1, a2) -> a1.getWork().getPriority() != a2.getWork().getPriority())
                .penalizeConfigurable(ResourceConstraintConfiguration.PRIORITY_ORDER, (a1, a2) -> {
                    TaskAllocation first = a1.getWork().getPriority() > a2.getWork().getPriority() ? a1 : a2;
                    TaskAllocation last  = a1.getWork().getPriority() < a2.getWork().getPriority() ? a1 : a2;
                    Duration lead = new Duration(first.getEndTime(), last.getEndTime());
                    return lead.getMillis() < 0 ? durationToScore(lead.abs()) : 0;
                });
    }

    private Constraint resourceConflict(ConstraintFactory factory) {
        return factory.fromUniquePair(TaskAllocation.class,
                overlapping(TaskAllocation::getStartTime, TaskAllocation::getEndTime))
                .ifExists(Resource.class,
                        filtering((allocation1, allocation2, resource) -> allocation1.getResource() == resource && allocation2.getResource() == resource))
                .penalizeConfigurable(ResourceConstraintConfiguration.RESOURCE_CONFLICT, (allocation1, allocation2) -> durationToScore(allocation1.overlappingDuration(allocation2))); // TODO configurable penalization function
    }

    private Constraint skillRequirement(ConstraintFactory factory) {
        return factory.from(TaskAllocation.class)
                .filter(allocation -> allocation.getWork().getRequiredSkills().size() > 0)
                .penalizeConfigurable(ResourceConstraintConfiguration.REQUIRED_SKILL, allocation -> allocation.getResource().skillDifference(allocation.getWork()));
    }


}
