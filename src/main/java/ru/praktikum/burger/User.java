package ru.praktikum.burger;

import org.apache.commons.lang3.RandomStringUtils;

public class User {

    public String name;
    public String email;
    public String password;

    public User() {

    }

    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public static User getRandom() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new User(name, email, password);
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public static User getNameOnly() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        return new User().setName(name);
    }

    public static User getEmailOnly() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        return new User().setEmail(email);
    }

    public static User getNameAndEmail() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        return new User().setName(name).setEmail(email);
    }

    public static User getPasswordOnly() {
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new User().setPassword(password);
    }

    public static User getEmailAndPassword() {
        final String email = RandomStringUtils.randomAlphabetic(10) + "@example.com";
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new User().setEmail(email).setPassword(password);
    }

    public static User getNameAndPassword() {
        final String name = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new User().setName(name).setPassword(password);
    }

}