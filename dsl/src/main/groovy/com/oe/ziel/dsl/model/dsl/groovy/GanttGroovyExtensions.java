package com.oe.ziel.dsl.model.dsl.groovy;

import com.oe.ziel.dsl.model.dsl.*;
import com.oe.ziel.dsl.model.RelationshipType;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import groovy.transform.NamedParam;
import groovy.transform.NamedParams;
import groovy.transform.stc.ClosureParams;
import groovy.transform.stc.FirstParam;
import groovy.transform.stc.SimpleType;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

import java.util.Map;

public class GanttGroovyExtensions {

    private static final String CARDINALITY = "cardinality";
    private static final String TITLE = "title";

    public static TypeDefinition type(
        GanttDefinition self,
        String name,
        @DelegatesTo(value = TypeDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.oe.ziel.dsl.model.dsl.TypeDefinition")
        Closure<? extends GanttContentDefinition> builder
    ) {
        return self.type(name, ConsumerWithDelegate.create(builder));
    }

    public static RelationshipDefinition aggregation(
        GanttDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.oe.ziel.dsl.model.dsl.RelationshipDefinition")
        Closure<? extends GanttContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.AGGREGATION, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition composition(
        GanttDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.oe.ziel.dsl.model.dsl.RelationshipDefinition")
        Closure<? extends GanttContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.COMPOSITION, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition inheritance(
        GanttDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.oe.ziel.dsl.model.dsl.RelationshipDefinition")
        Closure<? extends GanttContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.INHERITANCE, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition association(
        GanttDefinition self,
        String source,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.oe.ziel.dsl.model.dsl.RelationshipDefinition")
        Closure<? extends GanttContentDefinition> additionalProperties
    ) {
        return self.relationship(source, RelationshipType.ASSOCIATION, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition relationship(
        GanttDefinition self,
        String source,
        RelationshipType relationshipType,
        String destination,
        @DelegatesTo(value = RelationshipDefinition.class, strategy = Closure.DELEGATE_FIRST)
        @ClosureParams(value = SimpleType.class, options = "com.oe.ziel.dsl.model.dsl.RelationshipDefinition")
        Closure<? extends GanttContentDefinition> additionalProperties
    ) {
        return self.relationship(source, relationshipType, destination, ConsumerWithDelegate.create(additionalProperties));
    }

    public static RelationshipDefinition source(
        RelationshipDefinition self,
        @NamedParams({
            @NamedParam(value = CARDINALITY, type = String.class),
            @NamedParam(value = TITLE, type = String.class)
        })
        Map<String, String> cardinalityAndTitle
    ) {
        return self.source(cardinalityAndTitle.get(CARDINALITY), cardinalityAndTitle.get(TITLE));
    }

    public static RelationshipDefinition destination(
        RelationshipDefinition self,
        @NamedParams({
            @NamedParam(value = CARDINALITY, type = String.class),
            @NamedParam(value = TITLE, type = String.class)
        })
            Map<String, String> cardinalityAndTitle
    ) {
        return self.destination(cardinalityAndTitle.get(CARDINALITY), cardinalityAndTitle.get(TITLE));
    }

    public static <H extends GanttHelper, R> H configure(
        GanttDefinition self,
        @DelegatesTo.Target("helper") Class<H> helper,
        @DelegatesTo(value = DelegatesTo.Target.class, target = "helper", strategy = Closure.DELEGATE_FIRST, genericTypeIndex = 0)
        @ClosureParams(FirstParam.FirstGenericType.class)
        Closure<R> configuration
    ) {
        return self.configure(helper, ConsumerWithDelegate.create(configuration));
    }

}
