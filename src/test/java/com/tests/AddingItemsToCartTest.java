package com.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddingItemsToCartTest extends BaseTest {

    String expectedItemName, expectedItemDescription, expectedItemPrice;

    @BeforeClass (description = "Добавление товара в корзину")
    public void addingItemsToCart() {
        String username = "standard_user",
                password = "secret_sauce";

        app.loginScreen.open();
        app.loginScreen.login(username, password);

        int inventoryItemsQuantity = app.products.getInventoryItemsQuantity();
        int randomInventoryItemIndex = (int) Math.floor(Math.random() * inventoryItemsQuantity);

        app.products.addItemToCart(randomInventoryItemIndex);
        expectedItemName = app.products.getItemName(randomInventoryItemIndex);
        expectedItemDescription = app.products.getItemDescription(randomInventoryItemIndex);
        expectedItemPrice = app.products.getItemPrice(randomInventoryItemIndex);
        app.header.openCart();
    }

    @Test (description = "Проверка имени добавленного в Корзину товара")
    public void checkItemNameInCart() {
        String actualItemName = app.cart.getItemName(0);
        checkEqualsStep(actualItemName, expectedItemName);
    }

    @Test (description = "Проверка описания добавленного в Корзину товара")
    public void checkItemDescriptionInCart() {
        String actualItemDescription = app.cart.getItemDescription(0);
        checkEqualsStep(actualItemDescription, expectedItemDescription);
    }

    @Test (description = "Проверка цены добавленного в Корзину товара")
    public void checkItemPriceInCart() {
        String actualItemPrice = app.cart.getItemPrice(0);
        checkEqualsStep(actualItemPrice, expectedItemPrice);
    }

    @AfterTest (description = "Очистка корзины")
    public void removeItemsInCart() {
        int itemsNum = app.cart.getCartItemsQuantity();

        while (itemsNum > 0) {
            app.cart.removeItemFromCart(0);
            itemsNum = app.cart.getCartItemsQuantity();
        }
    }
}