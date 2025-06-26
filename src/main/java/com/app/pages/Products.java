package com.app.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class Products extends BasePage {

    public Products(String path) { super(path); }

    protected SelenideElement getItem(int itemIndex) {
        return $$(prop.getProperty("products.div.item.css")).get(itemIndex);
    }

    public int getInventoryItemsQuantity() {
        return $$(prop.getProperty("products.div.item.css")).size();
    }

    public void addItemToCart(int itemIndex) {
        getItem(itemIndex).$x(prop.getProperty("products.button.add_to_cart.xpath")).click();
    }

    public void removeItemFromCart(int itemIndex) {
        getItem(itemIndex).$x(prop.getProperty("products.button.remove.xpath")).click();
    }

    public void openItemViaImage(int itemIndex) {
        getItem(itemIndex).$x(prop.getProperty("products.img.item_image.xpath")).click();
    }

    public void openItemViaName(int itemIndex) {
        getItem(itemIndex).$(prop.getProperty("products.div.item_name.css")).click();
    }

    public String getItemName(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("products.div.item_name.css")).getText();
    }

    public String getItemPrice(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("products.div.item_price.css")).getText();
    }

    public String getItemDescription(int itemIndex) {
        return getItem(itemIndex).$(prop.getProperty("products.div.item_description.css")).getText();
    }
}
