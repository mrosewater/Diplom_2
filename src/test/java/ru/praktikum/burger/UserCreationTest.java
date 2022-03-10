package ru.praktikum.burger;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserCreationTest {

    private User user;
    private UserService userService;
    String token;

    @Before
    public void setUp() {
        user = new User().getRandom();
        userService = new UserService();
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createRequestReturnsSuccessTest() {
        ValidatableResponse response = userService.create(user);
        token =  response.extract().path("accessToken").toString().substring(7);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    public void doubleRequestReturnsErrorTest() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = userService.create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 403, statusCode);
    }

    @After
    public void tearDown() {
        userService.delete(token);
    }

}
