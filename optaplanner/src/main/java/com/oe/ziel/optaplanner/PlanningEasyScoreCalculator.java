package com.oe.ziel.optaplanner;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.EasyScoreCalculator;

// TODO - should not be object
public class PlanningEasyScoreCalculator implements EasyScoreCalculator<TaskAllocationSolution, HardSoftScore> {

    @Override
    public HardSoftScore calculateScore(TaskAllocationSolution taskAllocationSolution) {
        return HardSoftScore.of(1,1 );
    }


}