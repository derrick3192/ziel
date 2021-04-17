package com.oe.ziel.dsl.export;

import com.oe.ziel.dsl.model.Type;
import com.oe.ziel.dsl.properties.PropertiesGanttHelper;
import com.oe.ziel.dsl.model.Gantt;
import com.oe.ziel.dsl.model.Note;
import com.oe.ziel.dsl.model.Relationship;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class YumlGanttPrinter implements GanttPrinter {

    public String print(Gantt gantt) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        for (Note note : gantt.getNotes()) {
            printWriter.println(print(note));
        }

        Map<String, Type> orphanTypes = gantt.getTypes().stream().collect(toMap(Type::getName, identity()));

        for (Relationship relationship : gantt.getRelationships()) {
            orphanTypes.remove(relationship.getSource().getName());
            orphanTypes.remove(relationship.getDestination().getName());

            printWriter.println(print(gantt, relationship));
        }

        for (Type type : orphanTypes.values()) {
            printWriter.println(print(gantt, type));
        }

        return stringWriter.toString();
    }

    private String print(Gantt gantt, Type type) {
        Map<String, String> properties = PropertiesGanttHelper.getProperties(gantt, type);

        if (properties.isEmpty()) {
            return String.format("[%s]", type.getName());
        }

        String propertiesFormatted = properties
            .entrySet()
            .stream()
            .map((e) -> e.getKey() + ":" + e.getValue())
            .collect(Collectors.joining(";"));

        return String.format("[%s|%s]", type.getName(), propertiesFormatted);
    }

    private String print(Gantt gantt, Relationship r) {
        switch (r.getType()) {
            case ASSOCIATION:
                return String.format("%s%s%s-%s>%s",
                    print(gantt, r.getSource()),
                    r.isBidirectional() ? "<" : "",
                    cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()),
                    cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()),
                    print(gantt, r.getDestination())
                );
            case AGGREGATION:
                return String.format("%s<>%s-%s>%s",
                    print(gantt, r.getSource()),
                    cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()),
                    cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()),
                    print(gantt, r.getDestination())
                );
            case COMPOSITION:
                return String.format("%s++%s-%s>%s",
                    print(gantt, r.getSource()),
                    cardinalityAndTitle(r.getSourceCardinality(), r.getSourceTitle()),
                    cardinalityAndTitle(r.getDestinationCardinality(), r.getDestinationTitle()),
                    print(gantt, r.getDestination())
                );
            case INHERITANCE:
                return String.format("%s^%s", print(gantt, r.getDestination()), print(gantt, r.getSource()));
        }
        throw new IllegalArgumentException("Unknown type of relationship: " + r.getType());
    }

    private String print(Note note) {
        if (note.getColor() != null) {
            return String.format("[note: %s{bg:%s}]", note.getText(), note.getColor());
        }

        return String.format("[note: %s]", note.getText());
    }

    private static String cardinalityAndTitle(String cardinality, String title) {
        if (cardinality != null) {
            return title != null ? (title + " " + cardinality) : cardinality;
        }

        return title != null ? title : "";
    }
}
