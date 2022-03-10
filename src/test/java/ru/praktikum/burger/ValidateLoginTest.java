package ru.praktikum.burger;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class ValidateLoginTest {

    private User user = new User().getRandom();
    private UserCredentials userCredentials = new UserCredentials(user.email, user.password);
    private UserService userService =new UserService();

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void invalidUserLoginTest() {
        ValidatableResponse response = userService.login(userCredentials);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 401, statusCode);
    }

}
