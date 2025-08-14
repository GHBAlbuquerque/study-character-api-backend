package com.neo.characterapi.application.strategy;

import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.Job;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public class MageJob implements Job {

    @Override
    public JobType getJobType() {
        return JobType.MAGE;
    }

    @Override
    public JobAttributes getBaseAttributes() {
        return new JobAttributes(12, 5, 6, 10);
    }

    @Override
    public Double calculateAttack(JobAttributes jobAttributes) {
        return jobAttributes.getStrength() * 0.20 + jobAttributes.getDexterity() * 0.20 + jobAttributes.getIntelligence() * 1.20;
    }

    @Override
    public Double calculateSpeed(JobAttributes jobAttributes) {
        return jobAttributes.getDexterity() * 0.4 + jobAttributes.getStrength() * 0.1;
    }
}
