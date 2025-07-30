package com.app.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selenide.*;

public class LoginScreen extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginScreen.class);

    public LoginScreen(String path) { super(path); }

    public void login(String username, String password)
    {
        $(prop.getProperty("login_screen.input.username.css")).setValue(username);
        $(prop.getProperty("login_screen.input.password.css")).setValue(password);
        $x(prop.getProperty("login_screen.button.login.xpath")).click();

        try {
            WebDriverRunner.getWebDriver().switchTo().alert().accept();
        } catch (Exception a) {
        };
    }

    public String getErrorMessage() { return $x(prop.getProperty("login_screen.h3.error_message.xpath")).getText(); }
}
