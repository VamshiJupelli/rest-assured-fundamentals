import config.FootballConfig;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
}
