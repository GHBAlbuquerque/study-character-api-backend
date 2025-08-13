package com.neo.characterapi.domain.valueobjects;

import com.neo.characterapi.domain.enums.JobType;

public class JobAttributes {

    private Integer health;
    private Integer strength;
    private Integer dexterity;
    private Integer intelligence;

    public JobAttributes(Integer health, Integer strength, Integer dexterity, Integer intelligence) {
        this.health = health;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
    }

    public Double calculateAttack(JobType jobtype){
        return switch (jobtype) {
            case WARRIOR -> strength * 0.8 + dexterity * 0.2;
            case THIEF -> strength * 0.25 + dexterity + intelligence * 0.25;
            case MAGE -> strength * 0.20 + dexterity * 0.20 + intelligence * 1.20;
        };
    }

    public Double calculateSpeed(JobType jobtype){
        return switch (jobtype) {
            case WARRIOR -> dexterity * 0.6 + intelligence * 0.2;
            case THIEF -> dexterity * 0.8;
            case MAGE -> dexterity * 0.4 + strength * 0.1;
        };
    }

    public Integer getHealth() {
        return health;
    }
}
