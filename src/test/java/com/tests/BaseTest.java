package com.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import com.app.App;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static com.launch.Driver.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Unit test for simple App.
 */
public abstract class BaseTest
{
    protected static App app;
    protected static String username, password, contourCode;

    @BeforeSuite(description = "Получение данных пользователя из Vault")
    public static void getUserCredentials() throws IOException {

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }

//        JSONObject response = HttpURLConnectionExample.getSecret();
//        username = response.getJSONObject("data").getString("login");
//        password = response.getJSONObject("data").getString("password");


        String jsonString = new String(Files.readAllBytes(Paths.get("./config.json")));
        JSONObject json = new JSONObject(jsonString);
        contourCode = (String) json.getString("contour_code");

        app = new App();
    }

    @BeforeTest(description = "Запуск драйвера браузера")
    public static void setUp() throws IOException {
        initDriver();
        app.loginScreen.open();
    }

    @AfterMethod(description = "Вложения")
    public static void postcondition(ITestResult result)
    {
        try
        {
            if(result.getStatus() != ITestResult.SUCCESS)
                Allure.addAttachment("Скриншот", new ByteArrayInputStream(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES)));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @AfterTest(description = "Завершение сессии браузера")
    public static void tearDown() throws IOException {
        clearAllCookies();
        closeBrowser();
    }

    @Step("Сравнение фактического результата \"{actual}\" и ожидаемого \"{expected}\"")
    public static void checkEqualsStep(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Фактический результат не соответствует ожидаемому.\n");
    }

    @Step("Сравнение фактического результата \"{actual}\" и ожидаемого \"{expected}\"")
    public static void checkEqualsStep(int actual, int expected) {
        Assert.assertEquals(actual, expected, "Фактический результат не соответствует ожидаемому.\n");
    }

    @Step("Сравнение фактического результата \"{actual}\" и ожидаемого \"{expected}\"")
    public static void checkEqualsStep(Boolean actual, Boolean expected) {
        Assert.assertEquals(actual, expected, "Фактический результат не соответствует ожидаемому.\n");
    }

    public static String getRandomName()
    {
        String uniqueKey = UUID.randomUUID().toString().replaceAll("\\W", "");
        char firstChar = uniqueKey.replaceAll("\\d", "").toCharArray()[0];
        int firstCharIndex = uniqueKey.indexOf(firstChar);
        return uniqueKey.substring(firstCharIndex, firstCharIndex + 12);
    }
}
