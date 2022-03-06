package ru.praktikum.burger;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredService {

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://qa-scooter.praktikum-services.ru/")
                .setContentType(ContentType.JSON)
                .build();
    }

}
