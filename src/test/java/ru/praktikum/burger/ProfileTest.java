package ru.praktikum.burger;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProfileTest {

    private User user;
    private UserCredentials userCredentials;
    private UserService userService;
    String token;

    @Before
    public void setUp() {
        user = new User().getRandom();
        userCredentials = new UserCredentials(user.email, user.password);
        userService = new UserService();
    }

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    public void editProfileTest() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        userService = new UserService();
        userCredentials = new UserCredentials(user.email, user.password);
        ValidatableResponse response = userService.edit(userCredentials, token);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
    }

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    public void unauthorizedEditProfileTest() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = userService.unauthorizedEdit(userCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 401, statusCode);
    }

    @After
    public void tearDown() {
        userService.delete(token);
    }

}
