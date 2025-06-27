package com.launch;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;

public abstract class Driver
{
    public static void initDriver()
    {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        String osName = System.getProperty("os.name");

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

        Configuration.browser = "chrome";
        Configuration.browserVersion = "90.0";
        Configuration.browserSize = "1920x1080";
        Configuration.driverManagerEnabled = false;
        Configuration.browserCapabilities = capabilities;
        Configuration.timeout = 10000;
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