package com.app.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginScreen extends BasePage {

    public LoginScreen(String path) { super(path); }

    public void login(String username, String password)
    {
        $(prop.getProperty("login_screen.input.username.css")).setValue(username);
        $(prop.getProperty("login_screen.input.password.css")).setValue(password);
        $x(prop.getProperty("login_screen.button.login.xpath")).click();
    }

    public String getErrorMessage() { return $x(prop.getProperty("login_screen.h3.error_message.xpath")).getText(); }
}
