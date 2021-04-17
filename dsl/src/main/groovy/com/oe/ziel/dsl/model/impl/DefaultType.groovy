package com.oe.ziel.dsl.model.impl


import com.oe.ziel.dsl.model.RelationshipType
import com.oe.ziel.dsl.model.dsl.AggregationOrCompositionBuilder
import com.oe.ziel.dsl.model.dsl.GanttDefinition
import com.oe.ziel.dsl.model.dsl.From
import com.oe.ziel.dsl.model.dsl.InheritanceBuilder
import com.oe.ziel.dsl.model.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

@ToString(excludes = 'ganttDefinition')
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultType implements com.oe.ziel.dsl.model.Type, TypeDefinition {

    private final DefaultGantt gantt

    final String name

    DefaultType(DefaultGantt gantt, String name) {
        this.name = name
        this.gantt = gantt
    }

    @Override
    InheritanceBuilder inherits(From from) {
        return new InheritanceBuilder(gantt, this)
    }

    @Override
    AggregationOrCompositionBuilder has(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(gantt, this, RelationshipType.AGGREGATION, sourceCardinality as String)
    }

    @Override
    AggregationOrCompositionBuilder owns(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(gantt, this, RelationshipType.COMPOSITION, sourceCardinality as String)
    }

    @Override
    GanttDefinition getGanttDefinition() {
        return gantt
    }
}
