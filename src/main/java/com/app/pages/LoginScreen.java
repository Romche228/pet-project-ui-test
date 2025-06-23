package com.app.pages;

import org.json.JSONObject;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginScreen extends BasePage {
    String jsonString = new String(Files.readAllBytes(Paths.get("./config.json")));
    JSONObject json = new JSONObject(jsonString);
    String contourCode = (String) json.getString("contour_code");

    public LoginScreen(String path) throws IOException { super(path); }

    public void login(String username, String password)
    {
        $(prop.getProperty("login_screen.input.username.css")).setValue(username);
        $(prop.getProperty("login_screen.input.password.css")).setValue(password);
        $x(prop.getProperty("login_screen.button.login.xpath")).click();
    }

    public String getErrorMessage() { return $x(prop.getProperty("login_screen.h3.error_message.xpath")).getText(); }
}
