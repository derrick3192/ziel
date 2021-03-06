package com.oe.ziel.optaplanner;


import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.resource.Resource;
import com.oe.ziel.domain.work.Skill;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.jupiter.api.Test;
import org.testng.annotations.Ignore;

import java.util.Arrays;

/**
 * TODO - work on optaplanner solver still on-going
 */
public class TestOptaplannerSolver {

    final Skill SKILL_A = new Skill("A");
    final Skill SKILL_B = new Skill("B");
    final Skill SKILL_C = new Skill("C");


    private Resource resourceA = new Resource(SKILL_A);
    private Resource resourceB = new Resource(SKILL_B);
    private Resource resourceC = new Resource(SKILL_C);
    private Resource resourceBC = new Resource(SKILL_B, SKILL_C);


    @Ignore
    public void testOptaPlanner() {
        OptaplannerSolver solver = new OptaplannerSolver();

        Duration workAmountA = Duration.standardHours(8);
        Duration workAmountB = Duration.standardHours(5);
        Duration workAmountC = Duration.standardHours(3);

        Booking booking = new Booking();
        booking.setCreatedAt(Instant.now());

        solver.solve(
                Arrays.asList(booking),
                Arrays.asList(resourceA, resourceB, resourceC)
        );

    }

}
