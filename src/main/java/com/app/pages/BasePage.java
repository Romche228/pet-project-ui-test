package com.app.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.app.helpers.Trim;
import com.codeborne.selenide.ex.ElementShould;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Properties;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage
{
    protected Properties prop;
    protected String pageUrl = "https://www.saucedemo.com/";

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

    public void open()
    {
        String url = Trim.leftTrim(pageUrl, "/");
        Selenide.open(url);
    }

    public void open(String url)
    {
        Selenide.open(url);
    }

    public String getCurrentUrl() { return WebDriverRunner.getWebDriver().getCurrentUrl(); }

    public void refresh() {
        open(getCurrentUrl());
        waitForLoader();
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

    public void waitForLoader()
    {
        try {
            if($x(prop.getProperty("backstage.div.loader.xpath")).should(visible, Duration.ofSeconds(2)).is(visible)) {
                try {
                    $x(prop.getProperty("backstage.div.loader.xpath")).shouldBe(disappear, Duration.ofSeconds(5));
                } catch(com.codeborne.selenide.ex.ElementNotFound | ElementShould e) {}
            }
        } catch(com.codeborne.selenide.ex.ElementNotFound | ElementShould e) {}
    }
}
