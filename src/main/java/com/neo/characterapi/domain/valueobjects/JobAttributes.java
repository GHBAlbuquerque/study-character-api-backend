package com.neo.characterapi.domain.valueobjects;

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

    public JobAttributes(JobAttributes jobAttributes) {
        this.health = jobAttributes.getHealth();
        this.strength = jobAttributes.getStrength();
        this.dexterity = jobAttributes.getDexterity();
        this.intelligence = jobAttributes.getIntelligence();
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getStrength() {
        return strength;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public Integer getIntelligence() {
        return intelligence;
    }
}
