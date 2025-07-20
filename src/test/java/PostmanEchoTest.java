import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    //@Test
    public void postRequest_shouldReturnSentData() {
        String sentData = "some data";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body(sentData)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                // Проверяем, что в поле "data" содержится отправленная строка
                .body("data", equalTo(sentData));
    }

    @Test
    public void postRequest_withWrongJsonPath_shouldFail() {
        String sentData = "some data";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=UTF-8")
                .body(sentData)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                // Неверное поле - тест должен упасть
                .body("wrongField", equalTo(sentData));
    }
}

