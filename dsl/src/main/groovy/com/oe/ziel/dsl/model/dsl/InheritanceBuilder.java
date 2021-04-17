package com.oe.ziel.dsl.model.dsl;

import com.oe.ziel.dsl.model.Type;

public final class InheritanceBuilder implements HasDiagramDefinition {
    public InheritanceBuilder(GanttDefinition diagram, Type destination) {
        this.source = destination;
        this.diagram = diagram;
    }

    public RelationshipDefinition type(String destination) {
        return diagram.inheritance(source.getName(), destination);
    }

    @Override
    public GanttDefinition getGanttDefinition() {
        return diagram;
    }

    private final Type source;
    private final GanttDefinition diagram;
}
