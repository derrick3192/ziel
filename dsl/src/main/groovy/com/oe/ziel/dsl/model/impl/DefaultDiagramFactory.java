package com.oe.ziel.dsl.model.impl;

import com.oe.ziel.dsl.model.Diagram;
import com.oe.ziel.dsl.model.dsl.DiagramDefinition;

import java.util.function.Consumer;

public class DefaultDiagramFactory {

    public static Diagram build(Consumer<DiagramDefinition> definition) {
        DefaultDiagram diagram = new DefaultDiagram();
        definition.accept(diagram);
        diagram.postprocess();
        return diagram;
    }

}
