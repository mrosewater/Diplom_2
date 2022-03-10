package ru.praktikum.burger;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ValidateUserCreationTest {

    UserService userService = new UserService();
    User user;
    private final int expectedStatusCode;

    public ValidateUserCreationTest(User user, int expectedStatusCode) {
        this.user = user;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {User.getNameOnly(), 403},
                {User.getEmailOnly(), 403},
                {User.getNameAndEmail(), 403},
                {User.getPasswordOnly(), 403},
                {User.getEmailAndPassword(), 403},
                {User.getNameAndPassword(), 403},
        };
    }

    @Test
    @DisplayName("Создание пользователя если одно из обязательных полей не заполнено")
    public void invalidRequestTest() {

        ValidatableResponse response = userService.create(user);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Status code is wrong.", expectedStatusCode, statusCode);
    }

}
