package com.oe.ziel.dsl.model;

public interface Relationship {

    Type getSource();

    RelationshipType getType();

    Type getDestination();

    boolean isBidirectional();

    String getSourceCardinality();

    String getSourceTitle();

    String getDestinationCardinality();

    String getDestinationTitle();
}
