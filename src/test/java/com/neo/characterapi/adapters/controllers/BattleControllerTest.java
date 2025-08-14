package com.neo.characterapi.adapters.controllers;

import com.neo.characterapi.adapters.dto.request.BattleRequestDto;
import com.neo.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.neo.characterapi.application.strategy.JobStrategyFactory;
import com.neo.characterapi.domain.entities.GameCharacter;
import com.neo.characterapi.domain.enums.JobType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BattleControllerTest {

    @LocalServerPort
    private int port;

    @Test
        //TODO
    void shouldReturnBattleResult() {
        final var request = new CreateGameCharacterDto("Warrior_2", JobType.WARRIOR.name());
        final var request2 = new CreateGameCharacterDto("Mage_2", JobType.MAGE.name());

        Long id = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        Long id2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request2)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(1L, 2L))
                .when()
                .post("/battles")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturnBadRequestWhenCharactersAreTheSame() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(1L, 1L))
                .when()
                .post("/battles")
                .then()
                .statusCode(400);
    }

    @Test
    void shouldReturnBadRequestWhenCharactersAreDead() {
        final var request = new CreateGameCharacterDto("Mock_Warrior", JobType.WARRIOR.name());
        final var request2 = new CreateGameCharacterDto("Mock_Mage", JobType.MAGE.name());

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request2)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(1L, 2L))
                .when()
                .post("/battles")
                .then()
                .statusCode(200);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(1L, 2L))
                .when()
                .post("/battles")
                .then()
                .statusCode(400);
    }

    private GameCharacter createCharacter(String name, JobType jobType) {
        return new GameCharacter(name, JobStrategyFactory.createJob(jobType));
    }
}