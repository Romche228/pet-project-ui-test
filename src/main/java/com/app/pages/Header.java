package com.app.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Header extends BasePage {

    public Header(String path) { super(path); }

    public void openSideBar() {
        $x(prop.getProperty("header.button.menu.xpath")).click();
    }

    public void openCart() {
        $x(prop.getProperty("header.link.cart.xpath")).click();
    }

    public int getCartItemsNum() {
        String badgeText = $(prop.getProperty("header.span.counter.css")).getText();
        return Integer.valueOf(badgeText);
    }
}
