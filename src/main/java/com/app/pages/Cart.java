package com.app.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class Cart extends BasePage {

    public Cart(String path) { super(path); }

    protected SelenideElement getItem(int itemIndex) {
        return $$(prop.getProperty("cart.div.item.css")).get(itemIndex);
    }

    public int getCartItemsQuantity() {
        return $$(prop.getProperty("cart.div.item.css")).size();
    }

    public void removeItemFromCart(int itemIndex) {
        getItem(itemIndex).$x(prop.getProperty("cart.button.remove.css")).click();
    }

    public void openItemViaName(int itemIndex) {
        getItem(itemIndex).$(prop.getProperty("cart.div.item_name.css")).click();
    }

    public String getItemQuantity(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("cart.div.quantity.css")).getText();
    }

    public String getItemName(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("cart.div.item_name.css")).getText();
    }

    public String getItemPrice(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("cart.div.item_price.css")).getText();
    }

    public String getItemDescription(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("cart.div.item_description.css")).getText();
    }

    public void continueShoppingButtonClick(int itemIndex) {
        getItem(itemIndex).$(prop.getProperty("cart.button.continue_shopping.xpath")).click();
    }

    public void continueCheckoutClick(int itemIndex) {
        getItem(itemIndex).$(prop.getProperty("cart.button.checkout.xpath")).click();
    }
}
