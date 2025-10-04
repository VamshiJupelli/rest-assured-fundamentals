import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class VideoGameTests extends VideoGameConfig {

    String gameBodyJson = """
            {
                "category": "Platform",
                "name": "Mario",
                "rating": "Mature",
                "releaseDate": "1985-09-13",
                "reviewScore": 95
            }
            """;

    @Test
    public void getALlGames() {
        given()
                .when()
                .get(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByJson() {
        given()
                .body(gameBodyJson)
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void createNewGameByXml() {
        String gameBodyXml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <VideoGameRequest>
                    <category>Platform</category>
                    <name>Mario</name>
                    <rating>Mature</rating>
                    <releaseDate>1985-09-13</releaseDate>
                    <reviewScore>95</reviewScore>
                </VideoGameRequest>
                """;
        given()
                .body(gameBodyXml)
                .contentType("application/xml")
                .accept("application/xml")
                .when()
                .post(VideoGameEndpoints.ALL_VIDEO_GAMES)
                .then();
    }

    @Test
    public void updateGame() {
        given()
                .body(gameBodyJson)
                .when()
                .put("/videogame/3")
                .then();
    }

    @Test
    public void deleteGame() {
        given()
                .accept("text/plain")
                .when()
                .delete("/videogame/8")
                .then();
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5)
                .when()
                .get(VideoGameEndpoints.SINGLE_VIDEO_GAME)
                .then();
    }
}

