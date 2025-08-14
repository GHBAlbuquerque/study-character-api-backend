package com.neo.characterapi.application.strategy;

import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.Job;

public class JobStrategyFactory {

    public static Job createJob(JobType jobType) {
        return switch (jobType) {
            case WARRIOR -> new WarriorJob();
            case THIEF -> new ThiefJob();
            case MAGE -> new MageJob();
        };
    }
}
