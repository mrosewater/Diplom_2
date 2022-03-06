package ru.praktikum.burger;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserService extends RestAssuredService {

    private static final String BASE_PATH = "https://stellarburgers.nomoreparties.site/";

    public ValidatableResponse create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(BASE_PATH + "api/auth/register")
                .then();
    }

    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(BASE_PATH + "api/auth/login")
                .then();
    }

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

    public ValidatableResponse unauthorizedEdit(UserCredentials updatedCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(updatedCredentials)
                .when()
                .patch(BASE_PATH + "api/auth/user")
                .then();
    }

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
