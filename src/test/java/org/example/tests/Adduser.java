package org.example.tests;

import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.Priority;
import org.example.basetest.CommonToAllTest;
import org.example.driver.DriverManager;
import org.example.listeners.RetryAnalyzer;
import org.example.listeners.ScreenshotListener;
import org.example.pages.Orangedashboard;
import org.example.pages.Orangelogin;
import org.example.pages.Usermanagement;
import org.example.utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;

@Listeners(ScreenshotListener.class)
//@Test(retryAnalyzer = RetryAnalyzer.class)
public class Adduser extends CommonToAllTest {
    private static final Logger logger = LogManager.getLogger(Adduser.class);

    @Test(priority = 1)
    public void add_Employee(){
   Orangelogin orangelogin=new Orangelogin(DriverManager.getDriver());
   orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");
        Orangedashboard orangedashboard=new Orangedashboard(DriverManager.getDriver());
        orangedashboard.add_Employee();

    }
    @Description("Verify adding new user")
    @Test(priority = 3)
    public void adduser() throws InterruptedException {
        logger.info("Starting test: adduser");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        logger.info("Attempting to add user - Employee: [{}], Username: [{}]",
                PropertyReader.readkey("employeename"), PropertyReader.readkey("username"));
        usermanagement.adduser(PropertyReader.readkey("employeename"), PropertyReader.readkey("username"), PropertyReader.readkey("addpwd"));
      //  WebElement emptxt=DriverManager.getDriver().findElement(By.xpath("//input[@placeholder=\"Type for hints...\"]"));
        logger.info("User added successfully");
        DriverManager.getDriver().quit();
        logger.info("Browser closed. Test: adduser completed");
    }

    @Description("Verify that adding new user is deleted sucessfully ")
    @Test(priority = 2)
    public void deleteuser() {
        logger.info("Starting test: Verifyadduser");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        logger.info("Navigating to user management to verify added user");
        usermanagement.deleteUser();
    }

    @Description("Verify the user is able to edit the added user")
    @Test(priority = 3, enabled = false)
    public void editaddeduser() throws InterruptedException {
        logger.info("Starting test: editaddeduser");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        logger.info("Initiating edit user action");
        usermanagement.edituser();
        logger.info("Edit performed. Verifying updated user details");
        usermanagement.deleteUser();
        String updatedname = usermanagement.getusername();
        logger.info("Retrieved updated username from UI: [{}]", updatedname);
        Assert.assertEquals(updatedname, "Tejasurya99");
        logger.info("Assertion passed - updated username matches expected value: [Tejasurya99]");
    }

    @Description("Verify that user is able to search System users")
    @Test(priority = 4)
    public void searchSystemusers() {
        logger.info("Starting test: searchSystemusers");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        logger.info("Performing system user search");
        String searchedname = usermanagement.searchSystemusers();
        logger.info("Search returned username: [{}]", searchedname);
        Assert.assertEquals("Surya99", searchedname);
        logger.info("Assertion passed - search result matches expected value: [Surya99]");
    }

    @Description("Verify that user is able to reset System users search")
    @Test(priority = 5)
    public void resetsearchSystemusers() throws InterruptedException {
        logger.info("Starting test: resetsearchSystemusers");
        Orangelogin orangelogin = new Orangelogin(DriverManager.getDriver());
        orangelogin.validLogin(PropertyReader.readkey("ohr_username"), PropertyReader.readkey("ohr_password"));
        logger.info("Logged in successfully");
        Usermanagement usermanagement = new Usermanagement(DriverManager.getDriver());
        logger.info("Performing search reset");
        String emptyusername = usermanagement.resetsearchSystemusers();
        logger.info("Username field value after reset: [{}]", emptyusername.trim().isEmpty() ? "empty" : emptyusername);
        Assert.assertTrue(emptyusername.trim().isEmpty());
        logger.info("Assertion passed - username field is empty after reset");
    }
}