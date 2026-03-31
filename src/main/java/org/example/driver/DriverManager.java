package org.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.example.utils.PropertyReader;

public class DriverManager {

//    public static WebDriver getDriver() {
//        return driver;
//    }
//
//    public static void setDriver(WebDriver driver) {
//        DriverManager.driver = driver;
//    }
//
//    public static WebDriver driver;
//
//    public static void init() {
//        String browser = PropertyReader.readkey("browser");
//        browser = browser.toLowerCase();
//
//        switch (browser) {
//            case "edge":
//                EdgeOptions edgeoptions = new EdgeOptions();
//                edgeoptions.addArguments("--start-maximized");
//                edgeoptions.addArguments("--guest");
//                driver = new EdgeDriver(edgeoptions);
//                break;
//            case "chrome":
//                ChromeOptions chromeoptions = new ChromeOptions();
//                chromeoptions.addArguments("--start-maximized");
//                driver = new ChromeDriver(chromeoptions);
//                break;
//            case "firefox":
//                FirefoxOptions firefoxoptions = new FirefoxOptions();
//                firefoxoptions.addArguments("--start-maximized");
//                driver = new FirefoxDriver(firefoxoptions);
//                break;
//            default:
//                System.out.println("Not browser supported!!");
//
//        }
//    }
//
//    //when we want to close the browser
//    public static void down() {
//        if (getDriver() != null) {
//            driver.quit();
//            driver = null;
//        }
//
//    }
private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get Driver
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Set Driver
    public static void setDriver(WebDriver driverRef) {
        driver.set(driverRef);
    }
    public static void unload(){
        driver.remove();
    }

    // Initialize Driver
    public static void init() {

        String browser = PropertyReader.readkey("browser");
        browser = browser.toLowerCase();

        WebDriver driverInstance = null;

        switch (browser) {

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--guest");
                driverInstance = new EdgeDriver(edgeOptions);
                break;

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverInstance = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driverInstance = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException("Browser not supported!");
        }

        // Set driver to ThreadLocal
        setDriver(driverInstance);
    }

    // Quit Driver
    public static void down() {

        if (getDriver() != null) {
        getDriver().quit();
           unload();
//        }

             // VERY IMPORTANT
        }
    }
}
