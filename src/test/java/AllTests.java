import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.Card;
import ru.yandex.praktikum.Personality;

import java.io.File;
// дополнительный статический импорт нужен, чтобы использовать given, get и then
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

public class AllTests {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzFmMjM5MGQzYjg2YTAwM2Q2N2QxMzYiLCJpYXQiOjE2NjI5ODUxMDQsImV4cCI6MTY2MzU4OTkwNH0.zWHg1ZT1JJ4LPcOwOwFfcEWpVMnvmK6CBt_DCXcQM6c";
    // аннотация Before показывает, что метод будет выполняться перед каждым тестовым методом
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-mesto.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check status code of /users/me")
    @Description("This test checks status code only")
    public void getMyInfoStatusCode() {
        // given помогает добавить метод авторизации к запросу
        given()
                // указываем протокол и данные авторизации
                .auth().oauth2(bearerToken)
                // отправляем GET запрос на ручку, которая указана как параметр. В ответ вернётся объект класса "Response"
                .get("/api/users/me")
                // Метод then() валидирует ответ. "assertThat" сравнивает значения
                .then().statusCode(200);
    }

    @Test
    @DisplayName("Extract body")
    public void testExtraction() {
        Response response = given().auth().oauth2(bearerToken).get("/api/users/me");
        // в респонсе лежит JSON; data - ключ, name - значение
        response.then().assertThat().body("data.name", equalTo("Жак-Ив Кусто"));
        System.out.println(response.body().asString());
    }

    @Test
    @DisplayName("POST using JSON from file")
    public void postTestFromFile() {
        File json = new File("src/resources/newCard.json");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(bearerToken)
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
    }

    @Test
    @DisplayName("POST using JSON from string")
    public void postTestFromString() {
        String json = "{\"name\": \"Очень интересное место\", \"link\": \"https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(bearerToken)
                        .and()
                        .body(json)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
        System.out.println(response.body().asString());
    }

    @Test
    @DisplayName("POST using JSON from class == serialization")
    public void postTestFromCard() {
        Card card = new Card("Интересное место", "https://code.s3.yandex.net/qa-automation-engineer/java/files/paid-track/sprint1/photoSelenide.jpg");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .auth().oauth2(bearerToken)
                        .and()
                        .body(card)
                        .when()
                        .post("/api/cards");
        response.then().assertThat().body("data._id", notNullValue())
                .and()
                .statusCode(201);
        System.out.println(response.body().asString());
    }
    @Test
    public void patchFromClass() {
        Personality personality = new Personality("Жак-Ив Кусто", "Нефильтрованный");
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .auth().oauth2(bearerToken)
                        .and()
                        .body(personality)
                        .when()
                        .patch("/api/users/me");
        response.then().assertThat().body("data.about", is("Нефильтрованный"))
                .and()
                .statusCode(200);
        System.out.println(response.body().asString());
    }
/*
    @Test
    public void deserialization() {
        User user =
                given()
                        .header("Content-Type", "application/json")
                        .auth().oauth2(bearerToken)
                        .and()
                        .body()
                        .as(User.class)
                        .get("/api/users/me");
    }
    */
}
