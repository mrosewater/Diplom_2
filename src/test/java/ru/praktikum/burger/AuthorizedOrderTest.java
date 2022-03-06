package ru.praktikum.burger;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthorizedOrderTest {

    OrderService orderService = new OrderService();
    Ingrenients ingredients = new Ingrenients(orderService.getIngredients());
    UserService userService = new UserService();
    User user;
    String token;

    @Before
    public void setUp() {
        user = new User().getRandom();
    }

    @Test
    public void placeAuthorizedOrder() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response = orderService.placeAuthorizedOrder(token, ingredients);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
        int orderNumber = response.extract().path("order.number");
        Assert.assertNotNull(orderNumber);
    }

    @Test
    public void getAuthorizedOrderList() {
        token = userService.create(user).extract().path("accessToken").toString().substring(7);
        ValidatableResponse response  = orderService.GetOrderList(token);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertTrue(isSuccessful);
    }

    @After
    public void tearDown() {
        userService.delete(token);
    }

}
