package com.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod (description = "Открыть страницу")
    public void projectCreation() {
        app.loginScreen.open();
    }

    @Test (description = "Отображение ошибки при аутентификации без пароля")
    public void checkNoPasswordErrorMessage() {
        String expectedErrorMessage = "Epic sadface: Password is required",
                username = "standard_user";

        app.loginScreen.login(username, "");
        String actualErrorMessage = app.loginScreen.getErrorMessage();
        checkEqualsStep(actualErrorMessage, expectedErrorMessage);
    }

    @Test (description = "Отображение ошибки при аутентификации без логина")
    public void checkNoLoginErrorMessage() {
        String expectedErrorMessage = "Epic sadface: Username is required",
                password = "secret_sauce";

        app.loginScreen.login("", password);
        String actualErrorMessage = app.loginScreen.getErrorMessage();
        checkEqualsStep(actualErrorMessage, expectedErrorMessage);
    }

    @Test (description = "Отображение ошибки при аутентификации с неверным паролем")
    public void checkWrongPasswordErrorMessage() {
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service",
                username = "standard_user",
                password = "wrong_password";

        app.loginScreen.login(username, password);
        String actualErrorMessage = app.loginScreen.getErrorMessage();
        checkEqualsStep(actualErrorMessage, expectedErrorMessage);
    }

    @Test (description = "Авторизация с корректными логином и паролем")
    public void checkCorrectAuthorisation() {
        String expectedPageTitle = "Products",
                username = "standard_user",
                password = "secret_sauce";

        app.loginScreen.login(username, password);
        String actualPageTitle = app.header.getPageTitle();
        checkEqualsStep(actualPageTitle, expectedPageTitle);
    }
}