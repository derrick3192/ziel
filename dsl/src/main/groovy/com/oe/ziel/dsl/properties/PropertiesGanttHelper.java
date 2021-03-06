package com.oe.ziel.dsl.properties;

import com.oe.ziel.dsl.model.Type;
import com.oe.ziel.dsl.model.dsl.GanttHelper;
import com.oe.ziel.dsl.model.Gantt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertiesGanttHelper implements GanttHelper {

    private static final String METADATA_KEY = "com.oe.ziel.dsl.properties.PropertiesDiagramHelper.typeToProperties";

    private final Map<String, Map<String, String>> typeToProperties = new LinkedHashMap<>();

    @SuppressWarnings("unchecked")
    public static Map<String, String> getProperties(Gantt gantt, Type type) {
        Map<String, Map<String, String>> typeToProperties =
            (Map<String, Map<String, String>>) gantt.getMetadata().computeIfAbsent(METADATA_KEY, (key) -> new LinkedHashMap<>());
        return typeToProperties.computeIfAbsent(type.getName(), (key) -> new LinkedHashMap<>());
    }

    public PropertiesGanttHelper addProperty(String owner, String type, String name) {
        Map<String, String> properties = typeToProperties.computeIfAbsent(owner, (key) -> new LinkedHashMap<>());
        properties.put(name, type);
        return this;
    }

    public PropertiesGanttHelper addProperties(String owner, Map<String, String> newProperties) {
        Map<String, String> properties = typeToProperties.computeIfAbsent(owner, (key) -> new LinkedHashMap<>());
        properties.putAll(newProperties);
        return this;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return Collections.singletonMap(METADATA_KEY, typeToProperties);
    }
}
