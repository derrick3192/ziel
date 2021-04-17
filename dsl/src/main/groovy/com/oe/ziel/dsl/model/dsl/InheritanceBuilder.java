package com.oe.ziel.dsl.model.dsl;

import com.oe.ziel.dsl.model.Type;

public final class InheritanceBuilder implements HasGanttDefinition {
    public InheritanceBuilder(GanttDefinition gantt, Type destination) {
        this.source = destination;
        this.gantt = gantt;
    }

    public RelationshipDefinition type(String destination) {
        return gantt.inheritance(source.getName(), destination);
    }

    @Override
    public GanttDefinition getGanttDefinition() {
        return gantt;
    }

    private final Type source;
    private final GanttDefinition gantt;
}
