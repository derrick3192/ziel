package com.oe.ziel.dsl.model.impl;

import com.oe.ziel.dsl.model.Gantt;
import com.oe.ziel.dsl.model.dsl.GanttDefinition;

import java.util.function.Consumer;

public class DefaultDiagramFactory {

    public static Gantt build(Consumer<GanttDefinition> definition) {
        DefaultGantt diagram = new DefaultGantt();
        definition.accept(diagram);
        diagram.postprocess();
        return diagram;
    }

}
