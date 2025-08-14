package com.neo.characterapi.domain.entities;

import com.neo.characterapi.domain.enums.CharacterStatus;
import com.neo.characterapi.domain.enums.JobType;
import com.neo.characterapi.domain.valueobjects.JobAttributes;

public class GameCharacter {

    private Long id;
    private String name;
    private JobType jobType;
    private JobAttributes jobAttributes;
    private CharacterStatus characterStatus;
    private Integer currentHealth;

    public GameCharacter(Long id, String name, JobType jobType, JobAttributes jobAttributes) {
        this.id = id;
        this.name = name;
        this.jobType = jobType;
        this.jobAttributes = jobAttributes;
        this.characterStatus = CharacterStatus.ALIVE;
        this.currentHealth = jobAttributes.getHealth();
    }

    public GameCharacter(String name, JobType jobType, JobAttributes jobAttributes) {
        this.name = name;
        this.jobType = jobType;
        this.jobAttributes = jobAttributes;
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
        return jobType;
    }

    public String getJob() {
        return jobType.name();
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

    public boolean isAlive(){
        return this.characterStatus == CharacterStatus.ALIVE;
    }

    public boolean isDead() {
        return this.characterStatus == CharacterStatus.DEAD;
    }

    public void killCharacter(){
        this.currentHealth = 0;
        this.characterStatus = CharacterStatus.DEAD;
    }

    public void reviveCharacter(){
        this.currentHealth = this.jobAttributes.getHealth();
        this.characterStatus = CharacterStatus.ALIVE;
    }

    public void takeDamage(Integer damage){// convert Double → int
        this.currentHealth -= damage;

        if(this.currentHealth <= 0){
            this.currentHealth = 0;
            this.killCharacter();
        }
    }

    public Double getSpeed(){
        return this.getJobAttributes().calculateSpeed(this.getJobType());
    }

    public Double getAttack(){
        return this.getJobAttributes().calculateSpeed(this.getJobType());
    }
}
