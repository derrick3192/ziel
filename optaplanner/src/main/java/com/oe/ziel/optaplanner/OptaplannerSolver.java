package com.oe.ziel.optaplanner;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.resource.Resource;
import com.oe.ziel.domain.work.Work;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;

import java.util.List;

public class OptaplannerSolver {

    public TaskAllocationSolution solve(
            List<Booking> bookings,
            List<Resource> resources
    ) {
        SolverConfig solverConfig = new SolverConfig();

        ScoreDirectorFactoryConfig scoreDirectorFactoryConfig = new ScoreDirectorFactoryConfig();
        scoreDirectorFactoryConfig.setEasyScoreCalculatorClass(PlanningEasyScoreCalculator.class);
        scoreDirectorFactoryConfig.setConstraintProviderClass(ZielConstraintProvider.class);

        //scoreDirectorFactoryConfig.setIncrementalScoreCalculatorClass(findImplementingClass(IncrementalScoreCalculator.class));

        SolverFactory<TaskAllocationSolution> factory = SolverFactory.create(solverConfig);
        Solver<TaskAllocationSolution> solver = factory.buildSolver();

        TaskAllocationSolution solution = new TaskAllocationSolution();
        solution.setResources(resources);
        solution.setBookings(bookings);

        return solver.solve(solution);
    }

}
