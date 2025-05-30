package org.sample.rstttest.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void testGetPost() {
        given()
                .log().all()
        .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1))
                .time(lessThan(1000L)); // ⏱️ Süre kontrolü doğrudan matcher ile
    }

    @Test
    public void testCreatePost() {
        String jsonData = """
            {
              "title": "foo",
              "body": "bar",
              "userId": 1
            }
        """;

        given()
                .header("Content-type", "application/json")
                .log().all()
                .body(jsonData)
        .when()
                .post("https://jsonplaceholder.typicode.com/posts")
        .then()
                .log().all()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .time(lessThan(1000L)); // ⏱️ Süre kontrolü
    }
}
