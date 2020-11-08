package com.oe.ziel.domain.constraints;

/**
 * Represents a constraint
 */
public abstract class Constraint {


    /** If the constraint is valid */
    public boolean isValid() {
        return calculateScore() > 0;
    }

    /** Calculate the score of the constraint */
    abstract int calculateScore();

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
