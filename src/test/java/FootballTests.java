import config.FootballConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FootballTests extends FootballConfig {

    @Test
    public void getDetailsOfOneArea() {
        given()
                .queryParam("areas", 2076)
                .when()
                .get("/areas");
    }

    @Test
    public void getDetailsOfMultipleAreas() {
        String areaIds = "2076,2077,2082";
        given()
                .queryParam("areas", areaIds)
                .when()
                .get("/areas");
    }

    @Test
    public void getDateFounded() {
        given()
                .queryParam("areas", 2076)
                .when()
                .get("teams/57")
                .then()
                .body("founded", equalTo(1886));
    }

    @Test
    public void getFirstTeamName() {
        given()
                .when()
                .get("/competitions/2021/teams")
                .then()
                .body("teams.name[0]", equalTo("Arsenal FC"));
    }

    /* Extracting response as a String and printing it to the console */
    @Test
    @DisplayName("Extracting response as a String and printing it to the console")
    public void getAllTeamData() {
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    @Test
    @DisplayName("Extracting response using Response class and printing it to the console")
    public void getAllTeamData_DoCheckFirst() {
        Response response = given()
                .when()
                .get("teams/57")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();
        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    @DisplayName("Extracting headers from the response")
    public void extractHeaders() {
        Response response = get("teams/57")
                .then()
                .extract().response();
        String contentTypeHeader = response.getContentType();
        System.out.println("Header Content-Type is: " + contentTypeHeader);

        String apiVersionHeader = response.getHeader("X-API-Version");
        System.out.println("Header X-API-Version is: " + apiVersionHeader);
    }

    @Test
    @DisplayName("Extracting explicit data from the body with JSON path - first team name")
    public void extractFirstTeamName() {
        String firstTeamName = get("/competitions/2021/teams")
                .jsonPath()
                .getString("teams.name[0]");
        System.out.println("First Team Name is: " + firstTeamName);
    }

    @Test
    @DisplayName("Extracting explicit data from the body with JSON path - all team names")
    public void extractAllTeamNames() {
        Response response = get("/competitions/2021/teams")
                .then()
                .extract().response();

        List<String> allTeamNames = response.path("teams.name");
        for (String teamName : allTeamNames) {
            System.out.println("Team Name: " + teamName);
        }
    }
}
