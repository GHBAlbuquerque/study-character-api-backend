package com.neo.characterapi.adapters.controllers;

import com.neo.characterapi.adapters.dto.request.BattleRequestDto;
import com.neo.characterapi.adapters.dto.request.CreateGameCharacterDto;
import com.neo.characterapi.domain.enums.JobType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BattleControllerTest {

    @LocalServerPort
    private int port;

    private Integer id1;
    private Integer id2;

    private static String randomLetter() {
        final StringBuilder random  = new StringBuilder(String.valueOf((char) ('A' + new Random().nextInt(26))));
        random.append((char) ('A' + new Random().nextInt(26)));

        return random.toString();
    }


    @BeforeEach
    void setup() {

        final var request = new CreateGameCharacterDto("BttlWarrior_" + randomLetter(), JobType.WARRIOR.name());
        final var request2 = new CreateGameCharacterDto("BttlMage_" + randomLetter(), JobType.MAGE.name());

        id1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        id2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(request2)
                .when()
                .post("/characters")
                .then()
                .statusCode(201)
                .extract()
                .path("id");
    }

    @Test
    void shouldReturnBattleResult() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(Long.valueOf(id1), Long.valueOf(id2)))
                .when()
                .post("/battles")
                .then()
                .statusCode(200)
                .body("battleLog", notNullValue());

    }

    @Test
    void shouldReturnBadRequestWhenCharactersAreTheSame() {
        final String message = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(Long.valueOf(id1), Long.valueOf(id1)))
                .when()
                .post("/battles")
                .then()
                .statusCode(400)
                .extract()
                .path("detail");

        assert message.equals("Characters cannot be the same. Please chose a different opponent.");
    }

    @Test
    void shouldReturnBadRequestWhenIdIsNull() {
        final var message = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(null, null))
                .when()
                .post("/battles")
                .then()
                .statusCode(400)
                .extract()
                .path("detail");

        assert message.equals("Invalid arguments.");
    }

    @Test
    void shouldReturnBadRequestWhenIdIsNegative() {
        final var message = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(-1L, -1L))
                .when()
                .post("/battles")
                .then()
                .statusCode(400)
                .extract()
                .path("detail");

        assert message.equals("Invalid arguments.");
    }

    @Test
    void shouldReturnNotFoundWhenOneCharacterDoesNotExist() {
        final var message = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(Long.valueOf(id1), 300L))
                .when()
                .post("/battles")
                .then()
                .statusCode(404)
                .extract()
                .path("detail");

        assert message.equals("Couldn't find GameCharacter with id 300 ");
    }

    @Test
    void shouldReturnBadRequestWhenCharactersAreDead() {
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(Long.valueOf(id1), Long.valueOf(id2)))
                .when()
                .post("/battles")
                .then()
                .statusCode(200);

        final var message = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .port(port)
                .body(new BattleRequestDto(Long.valueOf(id1), Long.valueOf(id2)))
                .when()
                .post("/battles")
                .then()
                .statusCode(400)
                .extract()
                .path("detail");

        assert message.equals("One of the characters is dead, battle cannot continue.");
    }

}