package com.app.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public abstract class BasePage
{
    protected Properties prop;
    protected String pageUrl = "https://www.saucedemo.com";

    public BasePage(String path)
    {
        this.pageUrl += path;
        prop = new Properties();
        try {
            String appConfigPath = "src/main/resources/locators.properties";
            FileInputStream objFile = new FileInputStream(appConfigPath);
            prop.load(new InputStreamReader(objFile, Charset.forName("UTF-8")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void open() { Selenide.open(pageUrl); }

    public void open(String url)
    {
        Selenide.open(url);
    }

    public String getCurrentUrl() { return WebDriverRunner.getWebDriver().getCurrentUrl(); }

    public void refresh() {
        open(getCurrentUrl());
    }

    protected void createElement(WebElement element, String innerHtml) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].innerHTML = arguments[1];", element, innerHtml);
    }

    protected void setAttribute(WebElement element, String attName, String attValue) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
    }

    protected void dragAndDrop(WebElement elementDrag, WebElement elementDrop)
    {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.clickAndHold(elementDrag)
                .release(elementDrop)
                .build().perform();
    }
}
