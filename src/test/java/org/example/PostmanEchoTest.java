package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class PostmanEchoTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("GET")
    void get_echo_args() {
        given()
                .queryParam("alpha", "1")
                .queryParam("beta",  "2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.alpha", equalTo("1"))
                .body("args.beta",  equalTo("2"));
    }

    @Test
    @DisplayName("POST raw JSON")
    void post_raw_json() {
        String body = "{\"name\":\"Vladimir\",\"age\":30}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Vladimir"))
                .body("json.age",  equalTo(30));
    }

    @Test
    @DisplayName("POST form-data")
    void post_form_data() {
        given()
                .multiPart("x", "100")
                .multiPart("y", "200")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.x", equalTo("100"))
                .body("form.y", equalTo("200"))
                .body("files", anEmptyMap());
    }

    @Test
    @DisplayName("PUT")
    void put_urlencoded() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("k1", "foo")
                .formParam("k2", "bar")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("form.k1", equalTo("foo"))
                .body("form.k2", equalTo("bar"));
    }

    @Test
    @DisplayName("PATCH")
    void patch_form_data() {
        given()
                .multiPart("a", "10")
                .multiPart("b", "20")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("form.a", equalTo("10"))
                .body("form.b", equalTo("20"));
    }

    @Test
    @DisplayName("DELETE")
    void delete_raw_json() {
        String del = "{\"delete\":true,\"id\":123}";
        given()
                .contentType(ContentType.JSON)
                .body(del)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json.delete", equalTo(true))
                .body("json.id",     equalTo(123));
    }
}
