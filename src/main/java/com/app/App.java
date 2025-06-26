package com.app;

import com.app.pages.*;

public class App
{
    public LoginScreen loginScreen;
    public Header header;
    public Products products;
    public Cart cart;

    public App() {
        loginScreen = new LoginScreen("");
        header = new Header("");
        products = new Products("/inventory.html");
        cart = new Cart("/cart.html");
    }
}
