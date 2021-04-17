package com.oe.ziel.dsl.model.impl

import com.oe.ziel.dsl.model.Relationship
import com.oe.ziel.dsl.model.RelationshipType
import com.oe.ziel.dsl.model.dsl.GanttDefinition
import com.oe.ziel.dsl.model.dsl.RelationshipDefinition
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.PackageScope
import groovy.transform.ToString

@ToString(excludes = 'ganttDefinition')
@PackageScope
@CompileStatic
@EqualsAndHashCode
class DefaultRelationship implements Relationship, RelationshipDefinition {

    private final DefaultGantt diagram

    final DefaultType source
    final RelationshipType type
    final DefaultType destination

    DefaultRelationship(DefaultGantt diagram, DefaultType source, RelationshipType type = RelationshipType.ASSOCIATION, DefaultType destination) {
        this.diagram = diagram
        this.source = source
        this.type = type
        this.destination = destination
    }

    boolean bidirectional

    DefaultRelationship bidirectional(boolean bidirectional) {
        this.bidirectional = bidirectional
        return this
    }


    String sourceCardinality
    String sourceTitle

    DefaultRelationship source(String cardinality, String title = null) {
        this.sourceCardinality = cardinality
        this.sourceTitle = title
        this
    }

    String destinationCardinality
    String destinationTitle

    DefaultRelationship destination(String cardinality, String title = null) {
        this.destinationCardinality = cardinality
        this.destinationTitle = title
        this
    }

    DefaultRelationship called(String sourceTitle) {
        this.sourceTitle = sourceTitle
        this
    }

    @Override
    GanttDefinition getGanttDefinition() {
        return diagram
    }
}
