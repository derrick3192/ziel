package com.oe.ziel.optaplanner;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.resource.Resource;
import org.optaplanner.core.api.domain.constraintweight.ConstraintConfigurationProvider;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.score.buildin.hardmediumsoft.HardMediumSoftScore;

import java.util.List;

public class TaskAllocationSolution extends AbstractPersistable {

    @ConstraintConfigurationProvider
    private ResourceConstraintConfiguration resourceConstraintConfiguration;

    @PlanningEntityCollectionProperty
    private List<TaskAllocation> taskAllocations;

    @ProblemFactCollectionProperty
    private List<Resource> resources;

    @ProblemFactCollectionProperty
    private List<Booking> bookings;

    @PlanningScore
    private HardMediumSoftScore score = null;

    public ResourceConstraintConfiguration getResourceConstraintConfiguration() {
        return resourceConstraintConfiguration;
    }

    public void setResourceConstraintConfiguration(ResourceConstraintConfiguration resourceConstraintConfiguration) {
        this.resourceConstraintConfiguration = resourceConstraintConfiguration;
    }

    public List<TaskAllocation> getTaskAllocations() {
        return taskAllocations;
    }

    public void setTaskAllocations(List<TaskAllocation> taskAllocations) {
        this.taskAllocations = taskAllocations;
    }

    public HardMediumSoftScore getScore() {
        return score;
    }

    public void setScore(HardMediumSoftScore score) {
        this.score = score;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
