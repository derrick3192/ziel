package com.oe.ziel.dsl.properties

import com.oe.ziel.dsl.model.dsl.TypeDefinition
import groovy.transform.CompileStatic

@CompileStatic
class PropertiesGanttHelperExtensions {

    static TypeDefinition property(TypeDefinition typeDefinition, String type, String name) {
        typeDefinition.ganttDefinition.configure(PropertiesGanttHelper) { PropertiesGanttHelper helper ->
            helper.addProperty(typeDefinition.name, type, name)
        }
        return typeDefinition
    }

    static TypeDefinition property(TypeDefinition typeDefinition, Map<String, String> properties) {
        typeDefinition.ganttDefinition.configure(PropertiesGanttHelper) { PropertiesGanttHelper helper ->
            helper.addProperties(typeDefinition.name, properties)
        }
        return typeDefinition
    }

}
