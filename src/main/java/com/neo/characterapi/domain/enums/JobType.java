package com.neo.characterapi.domain.enums;

import com.neo.characterapi.domain.valueobjects.JobAttributes;

public enum JobType {

    WARRIOR(new JobAttributes(20, 10, 5, 5)),
    THIEF(new JobAttributes(15, 4, 10, 4)),
    MAGE(new JobAttributes(12, 5, 6, 10));

    private JobAttributes baseAttributes;

    JobType(JobAttributes baseAttributes) {
        this.baseAttributes = baseAttributes;
    }
}
