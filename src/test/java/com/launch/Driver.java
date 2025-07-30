package com.launch;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class Driver
{
    public static void initDriver()
    {
        ChromeOptions options = new ChromeOptions();
        String osName = System.getProperty("os.name");
        options.addArguments("--disable-notifications");

        if (!osName.startsWith("Windows")) {
            options.addArguments(
                "--headless",
                "--disable-dev-shm-usage",
                "--no-sandbox"
            );
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setAcceptInsecureCerts(true);

        System.setProperty("chromeoptions.prefs", "profile.password_manager_leak_detection=false");

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 10000;
        Selenide.open("https://www.google.com");
    }

    public static void clearAllCookies()
    {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static void closeBrowser() {
        Selenide.closeWindow();
    }

    public static void sleep(long milliseconds) { Selenide.sleep(milliseconds); }

    public static void refresh() { Selenide.refresh(); }
}