package ru.yandex.praktikum;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class API {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }
/*
    @Test
    public void getMyInfoStatusCode() {
        given()
                .auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzFmMjM5MGQzYjg2YTAwM2Q2N2QxMzYiLCJpYXQiOjE2NjI5ODUxMDQsImV4cCI6MTY2MzU4OTkwNH0.zWHg1ZT1JJ4LPcOwOwFfcEWpVMnvmK6CBt_DCXcQM6c")
                .get("/api/users/me")
                .then().statusCode(200).assertThat().body("data.name", equalTo("Жак-Ив Кусто"));
    }
*/
    @Test
    public void testExtraction() {
        Response response = given().auth().oauth2("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzFmMjM5MGQzYjg2YTAwM2Q2N2QxMzYiLCJpYXQiOjE2NjI5ODUxMDQsImV4cCI6MTY2MzU4OTkwNH0.zWHg1ZT1JJ4LPcOwOwFfcEWpVMnvmK6CBt_DCXcQM6c").get("/api/users/me");
        response.then().assertThat().body("data.name", equalTo("Жак-Ив Кусто"));
        System.out.println(response.body().asString());
    }
}