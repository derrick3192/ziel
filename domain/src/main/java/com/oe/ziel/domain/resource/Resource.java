package com.oe.ziel.domain.resource;

import com.oe.ziel.domain.work.Skill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Resource {

    private Set<String> tags = new HashSet<>();
    private Map<String, Skill> skills = new HashMap<>();

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Skill> skills) {
        this.skills = skills;
    }
}
