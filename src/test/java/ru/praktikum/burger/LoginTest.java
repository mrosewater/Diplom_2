package ru.praktikum.burger;

import io.restassured.response.ValidatableResponse;
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
    public void loginTest() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = userService.login(userCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
        userService.delete(token);
    }

    @Test
    public void invalidUserLoginTest() {
        ValidatableResponse response = userService.login(userCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 401, statusCode);
    }

}
