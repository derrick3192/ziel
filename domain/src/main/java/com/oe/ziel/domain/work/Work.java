package com.oe.ziel.domain.work;

import org.joda.time.Duration;
import org.joda.time.Instant;

import java.util.*;
import java.util.stream.Collectors;

public class Work {

    public Work(){}

    public Work(List<Skill> requiredSkill, Integer duration) {
        this.setDuration(duration);
        requiredSkill.forEach(this::setSkill);
    }

    public Work(Skill requiredSkill, Integer duration) {
        this.setSkill(requiredSkill);
        this.setDuration(duration);
    }

    /**
     * Help identify the name of the work.
     */
    protected String name;

    /**
     * The Standard Duration, that is how long it will take to complete the work with a speed factor of 1. (0.9 would mean a resource would only do 90% of the work in the same amount of time. 2.0 would be if a resource would complete twice the amount of work for the same amount of time.
     */
    protected Duration duration;

    /**
     * The Standard Cost, that is the cost given a cost factor of 1, (1.1 would be a 10% increase).
     */
    protected double cost = 1.0;

    /**
     * The priority of the Work, that is if 2 works are of equal priority and can be done at the same time then the higher priority works should be done first.
     */
    protected Integer priority = 0;

    /**
     * Description of the work
     */
    protected String description;

    /**
     * Latest tim eth work can be done
     */
    protected Instant maxStartTime;

    /**
     * The Maximum finish time of the job
     */
    protected Instant maxFinishTime;

    /**
     * The required skills to complete the work.
     */
    private Map<String, Skill> requiredSkills = new HashMap<>();

    /**
     * Work which needs to be completed before this.
     */
    private List<Work> dependencies = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Double hours) {
        setDuration(((int) Math.ceil(hours)));
    }

    public void setDuration(Integer hours) {
        setDuration(Duration.standardHours(hours));
    }

    public void setDuration(Duration amount) {
        this.duration = amount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Map<String, Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setSkill(String skillCode) {
        this.setSkill(new Skill(skillCode, 0, 0, 0));
    }

    public void setSkill(String skillCode, Integer major) {
        this.setSkill(new Skill(skillCode, major, 0, 0));
    }

    public void setSkill(String skillCode, Integer major, Integer minor, Integer patch) {
        this.setSkill(new Skill(skillCode, major, minor, patch));
    }

    public void setSkill(Skill skill) {
        requiredSkills.put(skill.getSkillCode(), skill);
    }

    public void setRequiredSkills(Map<String, Skill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getMaxStartTime() {
        return maxStartTime;
    }

    public void setMaxStartTime(Instant maxStartTime) {
        this.maxStartTime = maxStartTime;
    }

    public Instant getMaxFinishTime() {
        return maxFinishTime;
    }

    public void setMaxFinishTime(Instant maxFinishTime) {
        this.maxFinishTime = maxFinishTime;
    }

    public List<Work> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Work> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public String toString() {
        return "WorkSpec{" +
                "name='" + name + '\'' +
                ", amount=" + duration.getStandardHours() +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", maxStartTime=" + maxStartTime +
                ", maxFinishTime=" + maxFinishTime +
                ", dependencies=" + (dependencies.isEmpty() ? "NONE" : dependencies.stream().map(Work::getName).collect(Collectors.joining(","))) +
        '}';
    }

}
