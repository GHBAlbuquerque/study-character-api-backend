package com.neo.characterapi.domain.entities;

import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public class Character {

    private Long id;
    private String name;
    private JobType jobType;
    private JobAttributes jobAttributes;

    public Character(Long id, String name, JobType jobType, JobAttributes jobAttributes) {
        this.id = id;
        this.name = name;
        this.jobType = jobType;
        this.jobAttributes = jobAttributes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public JobAttributes getJobAttributes() {
        return jobAttributes;
    }

    public void setJobAttributes(JobAttributes jobAttributes) {
        this.jobAttributes = jobAttributes;
    }
}
