package com.oe.ziel.dsl.model.dsl;

import java.util.Collections;
import java.util.Map;

public interface GanttHelper extends GanttContentDefinition {

    default Map<String, Object> getMetadata() {
        return Collections.emptyMap();
    }

}
