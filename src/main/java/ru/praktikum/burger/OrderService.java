package ru.praktikum.burger;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderService extends RestAssuredService {

    private static final String BASE_PATH = "https://stellarburgers.nomoreparties.site/";

    @Step("Получение списка ингредиентов")
    public ArrayList getIngredients() {
        ArrayList<String> ingredients = given()
                .spec(getBaseSpec())
                .when()
                .get(BASE_PATH + "api/ingredients")
                .then()
                .extract()
                .path("data._id");
        return ingredients;
    }

    @Step("Получение некорректного списка ингредиентов")
    public ArrayList getInvalidIngredients() {
        ArrayList<String> ingredients = new ArrayList<String>();
        ingredients.add(RandomStringUtils.randomAlphanumeric(10));
        return ingredients;
    }

    @Step("Размещение заказа без авторизации")
    public ValidatableResponse placeUnauthorizedOrder(Ingrenients ingrenients) {
        return given()
                .spec(getBaseSpec())
                .body(ingrenients)
                .when()
                .post(BASE_PATH + "api/orders")
                .then();
    }

    @Step("Размещение заказа с авторизацией")
    public ValidatableResponse placeAuthorizedOrder(String token, Ingrenients ingrenients) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(ingrenients)
                .when()
                .post(BASE_PATH + "api/orders")
                .then();
    }

    @Step("Получение списка заказов с авторизацией")
    public ValidatableResponse GetOrderList(String token) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .when()
                .get(BASE_PATH + "api/orders")
                .then();
    }

    @Step("Получение списка заказов без авторизации")
    public ValidatableResponse GetOrderList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(BASE_PATH + "api/orders")
                .then();
    }

}
