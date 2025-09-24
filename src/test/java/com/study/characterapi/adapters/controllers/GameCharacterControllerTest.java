package com.study.characterapi.adapters.controllers;

import com.study.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.study.characterapi.domain.enums.JobType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameCharacterControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void createCharacter_ShouldReturnCreatedCharacter() {
        final var request = new CreateGameCharacterDto("New_Hero", JobType.WARRIOR.name());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .body("name", equalTo("New_Hero"));
    }

    @Test
    void createCharacter_ShouldReturnErrorIfNameTooLong() {
        final var request = new CreateGameCharacterDto("Heroooooooooooooooooooooo", JobType.WARRIOR.name());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(400);
    }

    @Test
    void createCharacter_ShouldReturnErrorIfNameTooShort() {
        final var request = new CreateGameCharacterDto("Ash", JobType.WARRIOR.name());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(400);
    }

    @Test
    void createCharacter_ShouldReturnErrorIfInvalidName() {
        final var request = new CreateGameCharacterDto("875646*&", JobType.WARRIOR.name());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(400);
    }

    @Test
    void createCharacter_ShouldReturnErrorIfNullName() {
        final var request = new CreateGameCharacterDto(null, JobType.WARRIOR.name());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(400);
    }

    @Test
    void createCharacter_ShouldReturnErrorIfInvalidJob() {
        final var request = new CreateGameCharacterDto("White_Paladin", "PALADIN");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(400);
    }

    @Test
    void getAllCharacters_ShouldReturnListOfCharacters() {
        final var request = new CreateGameCharacterDto("Hero", JobType.WARRIOR.name());


        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(201);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .when()
                .get("/characters")
                .then()
                .statusCode(200)
                .body("$", notNullValue());
    }

    @Test
    void getCharacterDetails_ShouldReturnDetailedCharacter() {
        final var request = new CreateGameCharacterDto("Mage", JobType.MAGE.name());

        final var id =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .port(port)
                        .body(request)
                        .when()
                        .post("/characters")
                        .then()
                        .statusCode(201)
                        .extract().path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .when()
                .get("/characters/{id}", id)
                .then()
                .statusCode(200)
                .body("name", equalTo("Mage"));
    }

}