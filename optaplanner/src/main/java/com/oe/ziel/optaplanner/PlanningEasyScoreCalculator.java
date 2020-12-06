package com.oe.ziel.optaplanner;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

// TODO - should not be object
public class PlanningEasyScoreCalculator implements EasyScoreCalculator<Object, HardSoftScore> {


    @Override
    public HardSoftScore calculateScore(Object o) {
        return null;
    }
}