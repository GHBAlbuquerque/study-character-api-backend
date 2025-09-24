package com.study.characterapi.domain.valueobjects;


import com.study.characterapi.domain.enums.JobType;

public interface Job {
    JobType getJobType();
    JobAttributes getBaseAttributes();
    Double calculateAttack(JobAttributes jobAttributes);
    Double calculateSpeed(JobAttributes jobAttributes);
}
