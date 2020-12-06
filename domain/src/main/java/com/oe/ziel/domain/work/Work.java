package com.oe.ziel.domain.work;

import org.joda.time.Duration;

import java.util.HashMap;
import java.util.Map;

public class Work {

    /**
     * Label to help identify the work.
     */
    private String label;

    /**
     * The Standard Duration, that is how long it will take to complete the job with a speed factor of 1. (0.9 would mean a resource would only do 90% of the work in the same amount of time. 2.0 would be if a resource would complete twice the amount of work for the same amount of time.
     */
    private Duration amount;

    /**
     * The Standard Cost, that is the cost given a cost factor of 1, (1.1 would be a 10% increase).
     */
    private double cost = 1.0;

    /**
     * The priority of the Job, that is if 2 jobs are of equal priority and can be done at the same time then the higher priority job should be done first.
     */
    private int priority = 0;

    /**
     * The required skills to complete the job.
     */
    private Map<String, Skill> requiredSkills = new HashMap<>();


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Duration getAmount() {
        return amount;
    }

    public void setAmount(Duration amount) {
        this.amount = amount;
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
}
