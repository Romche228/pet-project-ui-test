package com.app.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class Products extends BasePage {

    public Products(String path) { super(path); }

    protected SelenideElement getProductItem(int productNum) {
        return $$(prop.getProperty("products.div.item.css")).get(productNum);
    }

    public void addProductToCart(int productNum) {
        getProductItem(productNum).$x(prop.getProperty("products.button.add_to_cart.xpath")).click();
    }

    public void removeProductFromCart(int productNum) {
        getProductItem(productNum).$x(prop.getProperty("products.button.remove.xpath")).click();
    }

    public void openProductItemViaImage(int productNum) {
        getProductItem(productNum).$x(prop.getProperty("products.img.item_image.xpath")).click();
    }

    public void openProductItemViaName(int productNum) {
        getProductItem(productNum).$(prop.getProperty("products.div.item_name.css")).click();
    }

    public String getProductItemName(int productNum) {
        return getProductItem(productNum).$(prop.getProperty("products.div.item_name.css")).getText();
    }

    public String getProductItemPrice(int productNum) {
        return getProductItem(productNum).$(prop.getProperty("products.div.item_price.css")).getText();
    }

    public String getProductItemDescription(int productNum) {
        return getProductItem(productNum).$(prop.getProperty("products.div.item_description.css")).getText();
    }
}
