package com.oe.ziel.domain.work;

import org.joda.time.Duration;
import org.joda.time.Instant;

import java.util.HashMap;
import java.util.Map;

public class Work {

    /**
     * Help identify the name of the work.
     */
    protected String name;

    /**
     * The Standard Duration, that is how long it will take to complete the job with a speed factor of 1. (0.9 would mean a resource would only do 90% of the work in the same amount of time. 2.0 would be if a resource would complete twice the amount of work for the same amount of time.
     */
    protected Duration duration;

    /**
     * The Standard Cost, that is the cost given a cost factor of 1, (1.1 would be a 10% increase).
     */
    protected double cost = 1.0;

    /**
     * The priority of the Job, that is if 2 jobs are of equal priority and can be done at the same time then the higher priority job should be done first.
     */
    protected Integer priority = 0;


    /**
     * Description of the job
     */
    protected String description;

    protected Instant maxStartTime;

    protected Instant maxFinishTime;


    /**
     * The required skills to complete the job.
     */
    private Map<String, Skill> requiredSkills = new HashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Integer hours) {
        setAmount(Duration.standardHours(hours));
    }

    public void setAmount(Duration amount) {
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



}
