package com.oe.ziel.domain.constraints;

/**
 * Represents a constraint
 */
public interface Constraint {

    int calculateScore();

    int getWeight();

    int setWeight(int weight);

}
