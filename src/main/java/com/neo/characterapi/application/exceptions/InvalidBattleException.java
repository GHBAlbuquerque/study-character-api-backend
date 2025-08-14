package com.neo.characterapi.application.exceptions;

public class InvalidBattleException extends RuntimeException {
    public InvalidBattleException(String message) {
        super(message);
    }
}
