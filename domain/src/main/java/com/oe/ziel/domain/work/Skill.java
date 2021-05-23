package com.oe.ziel.domain.work;

public class Skill {

    public Skill(){}

    public Skill(String skillCode) {
        this.skillCode = skillCode;
    }

    public Skill(String skillCode, int major, int minor, int patch) {
        this.skillCode = skillCode;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * Unique Code for the string to identify the Skill
     */
    private String skillCode;

    // Skill Levels

    private int major = 0;
    private int minor = 0;
    private int patch = 0;


    private double speedFactor;

    private double costFactor;

    public String getSkillCode() {
        return skillCode;
    }

    public void setSkillCode(String skillCode) {
        this.skillCode = skillCode;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getPatch() {
        return patch;
    }

    public void setPatch(int patch) {
        this.patch = patch;
    }

    public double getSpeedFactor() {
        return speedFactor;
    }

    public void setSpeedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
    }

    public int compare(Skill requiredSkill) {
        if (
                major > requiredSkill.major ||
                major == requiredSkill.major && minor > requiredSkill.minor ||
                major == requiredSkill.major && minor == requiredSkill.minor && patch >= requiredSkill.patch
        ) {
            return 0;
        } else if (major < requiredSkill.major) {
            return 80;
        } else if (minor < requiredSkill.minor) {
            return 40;
        } else {
            return 10;
        }
    }
}
