package com.oe.ziel.dsl.model.dsl;

public interface TypeDefinition extends GanttContentDefinition, HasGanttDefinition {

    String getName();

    InheritanceBuilder inherits(From from);
    AggregationOrCompositionBuilder has(Object sourceCardinality);
    AggregationOrCompositionBuilder owns(Object sourceCardinality);

}
