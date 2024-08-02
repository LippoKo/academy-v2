package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.control.TeamService;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class TeamResourceTest {

    @Inject
    TeamService teamService;

    private Team team;
    Jsonb jsonb = JsonbBuilder.create();

    @BeforeEach
    public void setUp() {
        team = new Team();
        team.setName("Portito");
        team.setProduct("PSS");
        team.setDefault_location("PORTO");

        teamService.addTeam(team);
    }

    @Test
    void addTeamEnPoint() {
        Team createTeam = new Team();
        createTeam.setName("Portito");
        createTeam.setProduct("PSS");
        createTeam.setDefault_location("PORTO");

        given().contentType(ContentType.JSON).body(jsonb.toJson(createTeam))
                .post("/workstation/teams")
                .then().statusCode(201).log().all();
    }

    @Test
    void findAllEndPoint() {

        List<Team> teamList = teamService.findAllTeams();

        Team[] actualTeams = given().when().get("/workstation/teams").then().statusCode(200).extract().as(Team[].class);

        List<Team> actualTeamList = Arrays.asList(actualTeams);

        assertEquals(teamList, actualTeamList);
    }

    @Test
    void findTeamByIdEndpoint() {
        given().get("/workstation/teams/" + team.getId())
                .then().statusCode(200)
                .body(is(jsonb.toJson(team))).log().all();
    }

    @Test
    void updateTeamEndpoint() {
        Team updatedTeam = new Team();
        updatedTeam.setName("Updated Zeni");
        updatedTeam.setProduct("Updated PIT");
        updatedTeam.setDefault_location("Porto");

        given().contentType(ContentType.JSON)
                .body(jsonb.toJson(updatedTeam))
                .put("/workstation/teams/" + team.getId())
                .then().statusCode(200).log().all();
    }

    @Test
    void deleteTeamEndpoint() {
        given().delete("/workstation/teams/" + team.getId())
                .then().statusCode(204).log().all();
    }
}