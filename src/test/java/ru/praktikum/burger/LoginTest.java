package ru.praktikum.burger;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {

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
    @DisplayName("Логин под существующим пользователем")
    public void loginTest() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = userService.login(userCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
        userService.delete(token);
    }

    @After
    public void tearDown() {
        userService.delete(token);
    }

}
