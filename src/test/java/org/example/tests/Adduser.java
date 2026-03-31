package org.example.tests;

import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.Priority;
import org.example.basetest.CommonToAllTest;
import org.example.driver.DriverManager;
import org.example.listeners.RetryAnalyzer;
import org.example.listeners.ScreenshotListener;
import org.example.pages.Orangelogin;
import org.example.pages.Usermanagement;
import org.example.utils.PropertyReader;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

@Listeners(ScreenshotListener.class)
@Test(retryAnalyzer = RetryAnalyzer.class)
public class Adduser extends CommonToAllTest {
    private static final Logger logger = LogManager.getLogger(Adduser.class);

    @Description("Verify adding new user")
    @Test(priority = 1)
    public void adduser() throws InterruptedException {
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validlogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in sucessfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        usermanagement.adduser(PropertyReader.readkey("employeename"), PropertyReader.readkey("username"), PropertyReader.readkey("addpwd"));
        logger.info("Useradded sucessfully");
//        String jhon=usermanagement.verifyuser();
//        Assert.assertEquals(PropertyReader.readkey("addedname"),jhon);
        DriverManager.getDriver().quit();
    }

    @Description("Verify the added user")
    @Test(priority = 2)
    public void Verifyadduser() {
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validlogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in sucessfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        usermanagement.verifyuser();
        String username=usermanagement.getusername();
        Assert.assertEquals(username, "Surya66");
        DriverManager.getDriver().quit();
    }
    @Description("Verify the user is able to edit the added user")
    @Test(priority = 3,enabled = false)
    public void editaddeduser() throws InterruptedException {
        Orangelogin orangelogin=new Orangelogin(DriverManager.getDriver());
        orangelogin.validlogin(PropertyReader.readkey("ohr_username"),PropertyReader.readkey("ohr_password"));
        logger.info("Logged in sucessfully");
        Usermanagement usermanagement=new Usermanagement(DriverManager.getDriver());
        logger.info("Edit user page is displayed");
        usermanagement.edituser();
        usermanagement.verifyuser();
        String updstedname =usermanagement.getusername();
        Assert.assertEquals(updstedname,"Tejasurya99");
    }
}

