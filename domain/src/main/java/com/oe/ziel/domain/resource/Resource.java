package com.oe.ziel.domain.resource;

import com.oe.ziel.domain.work.Skill;
import com.oe.ziel.domain.work.Work;

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


}
