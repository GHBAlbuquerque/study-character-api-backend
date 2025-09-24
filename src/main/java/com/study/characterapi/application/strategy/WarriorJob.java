package com.study.characterapi.application.strategy;

import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.valueobjects.Job;
import com.study.characterapi.domain.valueobjects.JobAttributes;

public class WarriorJob implements Job {

    @Override
    public JobType getJobType() {
        return JobType.WARRIOR;
    }

    @Override
    public JobAttributes getBaseAttributes() {
        return new JobAttributes(20, 10, 5, 5);
    }

    @Override
    public Double calculateAttack(JobAttributes jobAttributes) {
        return jobAttributes.getStrength() * 0.8 + jobAttributes.getDexterity() * 0.2;
    }

    @Override
    public Double calculateSpeed(JobAttributes jobAttributes) {
        return jobAttributes.getDexterity() * 0.6 + jobAttributes.getIntelligence() * 0.2;
    }
}
