package org.example.tests;

import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.basetest.CommonToAllTest;
import org.example.driver.DriverManager;
import org.example.listeners.RetryAnalyzer;
import org.example.listeners.ScreenshotListener;
import org.example.pages.Myinfo;
import org.example.pages.Orangelogin;
import org.example.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenshotListener.class)
@Test(retryAnalyzer = RetryAnalyzer.class)
public class My_info extends CommonToAllTest {
    private static final Logger logger = LogManager.getLogger(Login.class);

    @Description("Adding the Personal details")
    @Test(priority = 1)
    public void addmy_info() {
        logger.info("=== TEST STARTED: addmy_info ===");

        logger.info("Initializing OrangeHRM login page");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());

        logger.info("Attempting login with username: {}", PropertyReader.readkey("ohr_username"));
        orangelogin.validlogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");

        logger.info("Navigating to My Info page");
        Myinfo myinfo = new Myinfo(DriverManager.getDriver());

        logger.info("Filling and submitting personal details form");
        String smessage = myinfo.addmyinfo();
        logger.debug("Response message received: {}", smessage);

        logger.info("Asserting success message");
        Assert.assertEquals(smessage, "Success");
        logger.info("Assertion passed - Personal details saved successfully");

        logger.info("=== TEST COMPLETED: addmy_info ===");
    }

    @Description("Adding contact details")
    @Test(priority = 2)
    public void contactdetails() {
        logger.info("=== TEST STARTED: contactdetails ===");

        logger.info("Initializing OrangeHRM login page");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());

        logger.info("Attempting login with username: {}", PropertyReader.readkey("ohr_username"));
        orangelogin.validlogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");

        logger.info("Navigating to My Info - Contact Details section");
        Myinfo myinfo = new Myinfo(DriverManager.getDriver());

        logger.info("Filling contact details form");
        myinfo.contact_details();
        logger.info("Contact details submitted successfully");

        logger.info("=== TEST COMPLETED: contactdetails ===");
    }
}
