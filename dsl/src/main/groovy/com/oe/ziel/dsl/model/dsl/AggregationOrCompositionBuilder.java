package com.oe.ziel.dsl.model.dsl;

import com.oe.ziel.dsl.model.Type;
import com.oe.ziel.dsl.model.RelationshipType;

public final class AggregationOrCompositionBuilder implements HasGanttDefinition {

    public AggregationOrCompositionBuilder(GanttDefinition gantt, Type destination, RelationshipType relationshipType, String cardinality) {
        this.gantt = gantt;
        this.destination = destination;
        this.relationshipType = relationshipType;
        this.cardinality = cardinality;
    }

    public AggregationOrCompositionBuilder to(Object upperBound) {
        return new AggregationOrCompositionBuilder(gantt, destination, relationshipType, cardinality + ".." + upperBound);
    }

    public RelationshipDefinition type(String aSource) {
        return gantt.relationship(aSource, relationshipType, destination.getName()).source(cardinality);
    }

    @Override
    public GanttDefinition getGanttDefinition() {
        return gantt;
    }

    private final GanttDefinition gantt;
    private final Type destination;
    private final RelationshipType relationshipType;
    private final String cardinality;
}
