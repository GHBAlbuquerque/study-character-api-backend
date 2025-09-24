package com.study.characterapi.domain.entities;

import com.study.characterapi.domain.enums.CharacterStatus;
import com.study.characterapi.domain.enums.JobType;
import com.study.characterapi.domain.valueobjects.Job;
import com.study.characterapi.domain.valueobjects.JobAttributes;

import java.util.Objects;

public class GameCharacter {

    private Long id;
    private String name;
    private Job job;
    private JobAttributes jobAttributes;
    private CharacterStatus characterStatus;
    private Integer currentHealth;

    public GameCharacter(String name, Job job) {
        this.name = name;
        this.job = job;
        this.jobAttributes = new JobAttributes(job.getBaseAttributes());
        this.characterStatus = CharacterStatus.ALIVE;
        this.currentHealth = jobAttributes.getHealth();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JobType getJobType() {
        return job.getJobType();
    }

    public String getJobName() {
        return job.getJobType().name();
    }

    public JobAttributes getJobAttributes() {
        return jobAttributes;
    }

    public CharacterStatus getCharacterStatus() {
        return characterStatus;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public boolean isAlive() {
        return this.characterStatus == CharacterStatus.ALIVE;
    }

    public boolean isDead() {
        return this.characterStatus == CharacterStatus.DEAD;
    }

    public void killCharacter() {
        this.currentHealth = 0;
        this.characterStatus = CharacterStatus.DEAD;
    }

    public void reviveCharacter() {
        this.currentHealth = this.jobAttributes.getHealth();
        this.characterStatus = CharacterStatus.ALIVE;
    }

    public void takeDamage(Integer damage) {// convert Double → int
        this.currentHealth -= damage;

        if (this.currentHealth <= 0) {
            this.currentHealth = 0;
            this.killCharacter();
        }
    }

    public Double getSpeed() {
        return this.job.calculateSpeed(this.getJobAttributes());
    }

    public Double getAttack() {
        return job.calculateAttack(this.getJobAttributes());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameCharacter that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
