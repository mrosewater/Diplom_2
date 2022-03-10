package ru.praktikum.burger;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserService extends RestAssuredService {

    private static final String BASE_PATH = "https://stellarburgers.nomoreparties.site/";

    @Step("Создание пользователя")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(BASE_PATH + "api/auth/register")
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(BASE_PATH + "api/auth/login")
                .then();
    }

    @Step("Редактирование пользователя с авторизацией")
    public ValidatableResponse edit(UserCredentials updatedCredentials, String token) {
        return given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(updatedCredentials)
                .when()
                .patch(BASE_PATH + "api/auth/user")
                .then();
    }

    @Step("Редактирование пользователя без авторизации")
    public ValidatableResponse unauthorizedEdit(UserCredentials updatedCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(updatedCredentials)
                .when()
                .patch(BASE_PATH + "api/auth/user")
                .then();
    }

    @Step("Удаление пользователя")
    public void delete(String token) {
        given()
                .spec(getBaseSpec())
                .auth()
                .oauth2(token)
                .body(token)
                .when()
                .delete(BASE_PATH + "api/auth/user");
    }

}
