package org.example.tests;

import io.qameta.allure.Description;
import org.apache.logging.log4j.core.net.Priority;
import org.example.basetest.CommonToAllTest;
import org.example.driver.DriverManager;
import org.example.listeners.RetryAnalyzer;
import org.example.listeners.ScreenshotListener;
import org.example.pages.Orangedashboard;
import org.example.pages.Orangelogin;
import org.example.utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Listeners(ScreenshotListener.class)
//@Test(retryAnalyzer = RetryAnalyzer.class)
public class Login extends CommonToAllTest {
    private static final Logger logger = LogManager.getLogger(Login.class);

    @Description("Login with valid careadentials")
    @Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
    public void validLogin() {
       // WebDriver driver = new ChromeDriver();
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        logger.info("Orange HRM Website is dispalyed");
        orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Orange HRM login sucessfully");
        Orangedashboard orangedashboard = new Orangedashboard(DriverManager.getDriver());
        String uname = orangedashboard.Verifyusername(PropertyReader.readkey("ohr_expected_username"));
        Assert.assertEquals(uname, PropertyReader.readkey("ohr_expected_username"));
        logger.info("Username verified sucessfully");

    }

    @Description("Login with invalid careadentials")
    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
    public void invalidLogin() {
       // WebDriver driver = new ChromeDriver();
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        logger.info("Orange HRM Website is dispalyed");
        String errottxt = orangelogin.invalidLogin(PropertyReader.readkey("wohr_username"), PropertyReader.readkey("wohr_password"));
        Assert.assertEquals(PropertyReader.readkey("invalidmsg"), errottxt);
        logger.info("error msg displayed sucessfully");

    }

    @Description("Verify that the validation message is displayed for empty submit")
    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void emptySubmit() {
      //  WebDriver driver = new ChromeDriver();
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        String[] errors = orangelogin.emptyLogin();
        logger.info("error msg displayed sucessfully");
        Assert.assertEquals(errors[0], "Required");
        Assert.assertEquals(errors[1], "Required");

    }
    @Description("fooelink")
    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void vaildforgotpwdtext(){
        Orangelogin orangelogin=new Orangelogin(DriverManager.getDriver());
        String fwdtext=orangelogin.Forgotpwd();
        Assert.assertEquals(PropertyReader.readkey("fwdtext").trim(),fwdtext.trim());

    }
}

