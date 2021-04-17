package com.oe.ziel.dsl.model.dsl.groovy;

import com.oe.ziel.dsl.model.dsl.GanttDefinition;
import com.oe.ziel.dsl.model.Gantt;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;
import space.jasan.support.groovy.closure.ConsumerWithDelegate;

public class GanttGroovyStaticExtensions {

    // https://youtrack.jetbrains.com/issue/IDEA-201914
    // name cannot be the same - therefore build for groovy and create for java
    public static Gantt build(Gantt self, @DelegatesTo(value = GanttDefinition.class, strategy = Closure.DELEGATE_FIRST) Closure definition) {
        return Gantt.create(ConsumerWithDelegate.create(definition));
    }

}
