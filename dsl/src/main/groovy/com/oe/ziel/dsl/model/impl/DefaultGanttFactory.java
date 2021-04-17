package com.oe.ziel.dsl.model.impl;

import com.oe.ziel.dsl.model.Gantt;
import com.oe.ziel.dsl.model.dsl.GanttDefinition;

import java.util.function.Consumer;

public class DefaultGanttFactory {

    public static Gantt build(Consumer<GanttDefinition> definition) {
        DefaultGantt gantt = new DefaultGantt();
        definition.accept(gantt);
        gantt.postprocess();
        return gantt;
    }

}
