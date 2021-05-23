package com.oe.ziel.domain.booking.solver;

import com.oe.ziel.domain.booking.Booking;
import com.oe.ziel.domain.resource.Resource;
import com.oe.ziel.domain.work.Work;

import java.util.List;

public interface Scheduler {

    List<TaskAllocation> schedule(List<Resource> resources, List<Work> works);

}
