package com.neo.characterapi.domain.valueobjects;


import com.neo.characterapi.domain.enums.JobType;

public interface Job {
    JobType getJobType();
    JobAttributes getBaseAttributes();
    Double calculateAttack(JobAttributes jobAttributes);
    Double calculateSpeed(JobAttributes jobAttributes);
}
