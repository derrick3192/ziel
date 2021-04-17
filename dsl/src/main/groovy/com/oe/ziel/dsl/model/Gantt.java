package com.oe.ziel.dsl.model;

import com.oe.ziel.dsl.model.dsl.GanttDefinition;
import com.oe.ziel.dsl.model.impl.DefaultDiagramFactory;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public interface Gantt {

    static Gantt create(Consumer<GanttDefinition> definition) {
        return DefaultDiagramFactory.build(definition);
    }

    Collection<? extends Note> getNotes();
    Collection<? extends Type> getTypes();
    Collection<? extends Relationship> getRelationships();

    Map<String, Object> getMetadata();

}
