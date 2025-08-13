package com.neo.characterapi.application.exceptions;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(String message) {
        super(message);
    }
}
