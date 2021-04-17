package com.oe.ziel.dsl.model.impl


import com.oe.ziel.dsl.model.RelationshipType
import com.oe.ziel.dsl.model.dsl.AggregationOrCompositionBuilder
import com.oe.ziel.dsl.model.dsl.DiagramDefinition
import com.oe.ziel.dsl.model.dsl.From
import com.oe.ziel.dsl.model.dsl.InheritanceBuilder
import com.oe.ziel.dsl.model.dsl.TypeDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

@ToString(excludes = 'diagramDefinition')
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultType implements com.oe.ziel.dsl.model.Type, TypeDefinition {

    private final DefaultDiagram diagram

    final String name

    DefaultType(DefaultDiagram diagram, String name) {
        this.name = name
        this.diagram = diagram
    }

    @Override
    InheritanceBuilder inherits(From from) {
        return new InheritanceBuilder(diagram, this)
    }

    @Override
    AggregationOrCompositionBuilder has(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(diagram, this, RelationshipType.AGGREGATION, sourceCardinality as String)
    }

    @Override
    AggregationOrCompositionBuilder owns(Object sourceCardinality) {
        return new AggregationOrCompositionBuilder(diagram, this, RelationshipType.COMPOSITION, sourceCardinality as String)
    }

    @Override
    DiagramDefinition getDiagramDefinition() {
        return diagram
    }
}
