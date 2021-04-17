package com.oe.ziel.dsl.properties


import groovy.transform.CompileStatic

@CompileStatic
class PropertiesDiagramHelperExtensions {

    static com.oe.ziel.dsl.model.dsl.TypeDefinition property(com.oe.ziel.dsl.model.dsl.TypeDefinition typeDefinition, String type, String name) {
        typeDefinition.diagramDefinition.configure(PropertiesDiagramHelper) { PropertiesDiagramHelper helper ->
            helper.addProperty(typeDefinition.name, type, name)
        }
        return typeDefinition
    }

    static com.oe.ziel.dsl.model.dsl.TypeDefinition property(com.oe.ziel.dsl.model.dsl.TypeDefinition typeDefinition, Map<String, String> properties) {
        typeDefinition.diagramDefinition.configure(PropertiesDiagramHelper) { PropertiesDiagramHelper helper ->
            helper.addProperties(typeDefinition.name, properties)
        }
        return typeDefinition
    }

}
