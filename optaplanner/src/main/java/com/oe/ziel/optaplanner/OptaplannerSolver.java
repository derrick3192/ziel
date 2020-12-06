package com.oe.ziel.optaplanner;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;

public class OptaplannerSolver {

    public AllocationPlan solve(AllocationPlan plan) {
        SolverConfig solverConfig = new SolverConfig();

        ScoreDirectorFactoryConfig scoreDirectorFactoryConfig = new ScoreDirectorFactoryConfig();
        scoreDirectorFactoryConfig.setEasyScoreCalculatorClass(PlanningEasyScoreCalculator.class);
        scoreDirectorFactoryConfig.setConstraintProviderClass(ZielConstraintProvider.class);
        //scoreDirectorFactoryConfig.setIncrementalScoreCalculatorClass(findImplementingClass(IncrementalScoreCalculator.class));

        SolverFactory<AllocationPlan> factory = SolverFactory.create(solverConfig);
        Solver<AllocationPlan> solver = factory.buildSolver();
        return solver.solve(plan);
    }

}
