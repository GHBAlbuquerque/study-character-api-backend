package com.study.characterapi.application.strategy;

import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.valueobjects.Job;
import com.study.characterapi.domain.valueobjects.JobAttributes;

public class ThiefJob implements Job {

    @Override
    public JobType getJobType() {
        return JobType.THIEF;
    }

    @Override
    public JobAttributes getBaseAttributes() {
        return new JobAttributes(15, 4, 10, 4);
    }

    @Override
    public Double calculateAttack(JobAttributes jobAttributes) {
        return jobAttributes.getStrength() * 0.25 + jobAttributes.getDexterity() + jobAttributes.getIntelligence() * 0.25;
    }

    @Override
    public Double calculateSpeed(JobAttributes jobAttributes) {
        return jobAttributes.getDexterity() * 0.8;
    }
}
