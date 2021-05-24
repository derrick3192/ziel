package com.oe.ziel.domain.resource;

import com.oe.ziel.domain.work.Skill;
import com.oe.ziel.domain.work.Work;

import java.util.*;
import java.util.stream.Collectors;

public class Resource {

    // TODO - this is unique as a Resource is a case class in scala which causes problems in conversions resources who are identical come back the same
    private String name = UUID.randomUUID().toString();

    private Set<String> tags = new HashSet<>();
    private Map<String, Skill> skills = new HashMap<>();


    public Resource(String... skills) {
        this(Arrays.stream(skills).map(Skill::new).collect(Collectors.toList()));
    }

    public Resource(Collection<Skill> skills) {
        for (Skill skill : skills) {
            this.skills.put(skill.getSkillCode(), skill);
        }
    }
    public Resource(Skill... skills) {
        for (Skill skill : skills) {
            this.skills.put(skill.getSkillCode(), skill);
        }
    }

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


    // if no skill present 100
    // 10 - if minor doesn't match
    // if major matches, but not other, 50
    // if major and minor matches,

    /**
     * 10 - if patch doesn't match
     * 20 - if minor doesn't match
     * 50 - if major doesn't match
     * 100 - if skill not present
     * @param work the work the resource is to perform
     * @return
     */
    public int skillDifference(Work work) {
        int score = 0;
        Map<String, Skill> requiredSkills = work.getRequiredSkills();
        for (Skill requiredSkill : requiredSkills.values()) {
            Skill resourceSkill = skills.get(requiredSkill.getSkillCode());
            if (resourceSkill == null) {
                score += 100;
            } else {
                return resourceSkill.compare(requiredSkill);
            }
        }
        return score;
    }


    public boolean canWork(Work work) {
        Set<String> requiredSkills = work.getRequiredSkills().keySet();
        return requiredSkills.stream().allMatch(required -> skills.containsKey(required));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resource{\"skills=" + skills.keySet().stream().map(String::valueOf).collect(Collectors.joining(",")) +'}';
    }
}
