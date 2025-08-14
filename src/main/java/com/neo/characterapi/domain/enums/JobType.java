package com.neo.characterapi.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum JobType {

    WARRIOR("80% of strength + 20% of dexterity", "60% of dexterity + 20% of intelligence"),
    THIEF("25% of strength + 100% of dexterity + 25% of intelligence", "80% of dexterity"),
    MAGE("20% of strength + 20% of dexterity + 120% of intelligence", "40% of dexterity + 10% of strength");

    private String attackModifierDescription;
    private String speedModifierDescription;

    JobType(String attackModifierDescription, String speedModifierDescription) {
        this.attackModifierDescription = attackModifierDescription;
        this.speedModifierDescription = speedModifierDescription;
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
