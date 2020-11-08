package com.oe.ziel.domain.constraints;

import com.oe.ziel.domain.resource.Resource;

public abstract class UnaryResourceConstraint extends Constraint {

    private final Resource resource;

    public UnaryResourceConstraint(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
