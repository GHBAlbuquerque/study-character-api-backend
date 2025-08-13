package com.neo.characterapi.domain.enums;

import com.neo.characterapi.domain.valueobjects.JobAttributes;

import java.util.Arrays;
import java.util.List;

public enum JobType {

    WARRIOR(new JobAttributes(20, 10, 5, 5), "80% of strength + 20% of dexterity", "60% of dexterity + 20% of intelligence"),
    THIEF(new JobAttributes(15, 4, 10, 4), "25% of strength + 100% of dexterity + 25% of intelligence", "80% of dexterity"),
    MAGE(new JobAttributes(12, 5, 6, 10), "20% of strength + 20% of dexterity + 120% of intelligence", "40% of dexterity + 10% of strength");

    private JobAttributes baseAttributes;
    private String attackModifierDescription;
    private String speedModifierDescription;

    JobType(JobAttributes baseAttributes, String attackModifierDescription, String speedModifierDescription) {
        this.baseAttributes = baseAttributes;
        this.attackModifierDescription = attackModifierDescription;
        this.speedModifierDescription = speedModifierDescription;
    }

    public JobAttributes getBaseAttributes() {
        return baseAttributes;
    }

    public String getAttackModifierDescription() {
        return attackModifierDescription;
    }

    public String getSpeedModifierDescription() {
        return speedModifierDescription;
    }

    public static List<String> names() {
        return Arrays.stream(JobType.values()).map(Enum::name).toList();
    }
}
