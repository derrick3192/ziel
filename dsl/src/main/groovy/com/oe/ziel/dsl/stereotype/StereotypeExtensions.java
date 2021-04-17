package com.oe.ziel.dsl.stereotype;

import com.oe.ziel.dsl.model.dsl.*;

public class StereotypeExtensions {

    public static TypeDefinition stereotype(GanttDefinition diagram, String name) {
        return diagram.type("<<" + name + ">>");
    }

    public static RelationshipDefinition stereotype(AggregationOrCompositionBuilder builder, String name) {
        return builder.type("<<" + name + ">>");
    }

    public static RelationshipDefinition stereotype(InheritanceBuilder builder, String name) {
        return builder.type("<<" + name + ">>");
    }

}
