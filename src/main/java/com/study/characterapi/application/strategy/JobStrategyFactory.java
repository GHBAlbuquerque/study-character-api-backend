package com.study.characterapi.application.strategy;

import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.valueobjects.Job;

public class JobStrategyFactory {

    public static Job createJob(JobType jobType) {
        return switch (jobType) {
            case WARRIOR -> new WarriorJob();
            case THIEF -> new ThiefJob();
            case MAGE -> new MageJob();
        };
    }
}
