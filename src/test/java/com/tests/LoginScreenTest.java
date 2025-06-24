package com.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @BeforeMethod(description = "Открыть страницу")
    public void projectCreation() throws IOException {
        app.loginPage.open();
    }

    @Test (description = "Отображение ошибки при аутентификации без пароля")
    public void checkNoPasswordErrorMessage() {
        String expectedErrorMessage = "Epic sadface: Password is required",
                username = "secret_sauce";

        app.loginPage.login(username, "");
        String actualErrorMessage = app.loginPage.getErrorMessage();
        checkEqualsStep(actualErrorMessage, expectedErrorMessage);
    }

    @Test (description = "Отображение ошибки при аутентификации без логина")
    public void checkNoLoginErrorMessage() {
        String expectedErrorMessage = "Epic sadface: Username is required",
                password = "secret_sauce";

        app.loginPage.login("", password);
        String actualErrorMessage = app.loginPage.getErrorMessage();
        checkEqualsStep(actualErrorMessage, expectedErrorMessage);
    }

    @Test (description = "Отображение ошибки при аутентификации с неверным паролем")
    public void checkWrongPasswordErrorMessage() {
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service",
                username = "standart_user",
                password = "wrong_password";

        app.loginPage.login(username, password);
        String actualErrorMessage = app.loginPage.getErrorMessage();
        checkEqualsStep(actualErrorMessage, expectedErrorMessage);
    }
}