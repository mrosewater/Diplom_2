package ru.praktikum.burger;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

public class OrderTest {

    OrderService orderService = new OrderService();
    Ingrenients ingredients = new Ingrenients(orderService.getIngredients());

    @Test
    public void placeUnauthorizedOrder() {
        ValidatableResponse response = orderService.placeUnauthorizedOrder(ingredients);
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 200, statusCode);
        int orderNumber = response.extract().path("order.number");
        Assert.assertNotNull(orderNumber);
    }

    @Test
    public void placeOrderWithoutIngredients() {
        ValidatableResponse response = orderService.placeUnauthorizedOrder(new Ingrenients());
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 400, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }

    @Test
    public void placeOrderWithInvalidIngredients() {
        ValidatableResponse response = orderService.placeUnauthorizedOrder(new Ingrenients(orderService.getInvalidIngredients()));
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 500, statusCode);
    }

    @Test
    public void getUnauthorizedOrderList() {
        ValidatableResponse response  = orderService.GetOrderList();
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("Неправильный статус.", 401, statusCode);
        boolean isSuccessful = response.extract().path("success");
        Assert.assertFalse(isSuccessful);
    }

}
