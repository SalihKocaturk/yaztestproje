package org.sample.rstttest.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test
    public void testGetPost() {
        Response response = given()
                .log().all()
        .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1))
                .time(lessThan(1000L))
                .extract().response();

//        System.out.println("🔍 GET Yanıt Detayları:");
//        System.out.println("Status Kodu: " + response.statusCode());
//        System.out.println("Başlıklar: " + response.getHeaders());
//        System.out.println("İçerik Tipi: " + response.contentType());
//        System.out.println("Yanıt Gövdesi:\n" + response.getBody().asPrettyString());
//        System.out.println("Yanıt Süresi: " + response.time() + " ms");
    }
    @Test
    public void testCreatePost() {
        String jsonData = """
            {
              "title": "selamlar",
              "body": "test1",
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
                .body("title", equalTo("selamlar"))
                .time(lessThan(1000L)); // ⏱️ Süre kontrolü
//        System.out.println("📝 POST Yanıt Detayları:");
//        System.out.println("Status Kodu: " + response.statusCode());
//        System.out.println("Başlıklar: " + response.getHeaders());
//        System.out.println("İçerik Tipi: " + response.contentType());
//        System.out.println("Yanıt Gövdesi:\n" + response.getBody().asPrettyString());
//        System.out.println("Yanıt Süresi: " + response.time() + " ms");
    }
}
