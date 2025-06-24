package com.app;

import com.app.pages.*;

import java.io.IOException;

public class App
{
    public LoginScreen loginScreen;
    public Products products;

    public App() throws IOException {
        loginScreen = new LoginScreen("");
        products = new Products("/inventory.html");
    }
}
