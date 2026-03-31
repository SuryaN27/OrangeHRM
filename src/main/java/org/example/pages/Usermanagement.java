package org.example.pages;

import org.example.utils.PropertyReader;
import org.example.utils.waithelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class Usermanagement {
    private static final Logger log = LoggerFactory.getLogger(Usermanagement.class);
    WebDriver driver;
    Actions actions;
    public Usermanagement(WebDriver driver) {
        this.driver = driver;
        this.js= (JavascriptExecutor) driver;
    }

    private By admin=By.xpath("//span[text()=\"Admin\"]");
    private By adduser= By.xpath("//h6[text()=\"Add User\"]");
    private By addbtn=By.xpath("//div[@class=\"orangehrm-header-container\"]/button");
    private By userrole=By.xpath("(//div[@class=\"oxd-select-text-input\"])[1]");
    private By adminoptn =By.xpath("//div[@role='listbox']/div[2]");
    private By statusoptn = By.xpath("//div[@role='listbox']/div[2]");
    private By ststusdisabledoption=By.xpath("//div[@role='listbox']/div[3]");
    private By empname= By.xpath("//input[@placeholder=\"Type for hints...\"]");
    private By employee_name= By.xpath("(//div[@role='option']/span)[1]");
    private By status= By.xpath("(//div[@class=\"oxd-select-text-input\"])[2]");
    private By username= By.xpath("//label[text()=\"Username\"]/following::input[1]");
    private By pwd= By.xpath("(//input[@type=\"password\"])[1]");
    private By confirmpwd= By.xpath("(//input[@type=\"password\"])[2]");
  //  private By savebtn= By.xpath("//button[@type=\"submit\"]");
    private By savebtn= By.xpath("(//button)[3]");
    private By cancelbtn= By.xpath("(//button[@type=\"button\"])[2]");
    private By vrfyuser=By.xpath("//div[text()=\"Johnathon Bahringer\"]");
    private By getVrfyuser= By.xpath("((//div[@class=\"oxd-table-row oxd-table-row--with-border\"])[8]/div/div)[2]");
    private By recfond= By.xpath("//span[contains(normalize-space(),'Records Found')]");
    private By edituser =By.xpath("(//i[@class=\"oxd-icon bi-pencil-fill\"])[7]");
    JavascriptExecutor js= (JavascriptExecutor) driver;
   public void adduser(String emplyeename, String usernames, String pwds) throws InterruptedException {
       //driver.get(PropertyReader.readkey("url"));
  // driver.manage().window().maximize();
       waithelpers.checkVisibility(driver,admin);
       driver.findElement(admin).click();
       waithelpers.checkVisibility(driver,addbtn);
       driver.findElement(addbtn).click();
       waithelpers.checkVisibility(driver,adduser);
       String addusertxt=driver.findElement(adduser).getText();
       driver.findElement(userrole).click();
       waithelpers.checkVisibility(driver,adminoptn);
       driver.findElement(adminoptn).click();
       driver.findElement(empname).sendKeys(emplyeename);
       waithelpers.checkVisibility(driver,employee_name);
       driver.findElement(employee_name).click();
       driver.findElement(status).click();
       waithelpers.checkVisibility(driver,statusoptn);
       driver.findElement(statusoptn).click();
       waithelpers.checkVisibility(driver,username);
       driver.findElement(username).click();
       driver.findElement(username).sendKeys(usernames);
       waithelpers.checkVisibility(driver,pwd);
       driver.findElement(pwd).sendKeys(pwds);
       driver.findElement(confirmpwd).sendKeys(pwds);
       Thread.sleep(5000);
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       WebElement savebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));
       log.info("button visible");
       js.executeScript("arguments[0].click();", savebtn);
       Thread.sleep(5000);
       log.info("button clicked");
      // waithelpers.checkVisibility(driver,recfond);

   }
    public void verifyuser() {
        waithelpers.checkVisibility(driver, admin);
        driver.findElement(admin).click();
        waithelpers.checkVisibility(driver, recfond);
        js.executeScript("window.scrollBy(500,0);");
        js.executeScript("window.scrollBy(0,500);");
    }

    public String getusername() {
       waithelpers.checkVisibility(driver, getVrfyuser);
       return driver.findElement(getVrfyuser).getText();

        }

    public void edituser() throws InterruptedException {
        waithelpers.checkVisibility(driver, admin);
        driver.findElement(admin).click();
        waithelpers.checkVisibility(driver, recfond);
        js.executeScript("window.scrollBy(500,0);");
        js.executeScript("window.scrollBy(0,500);");
        waithelpers.checkVisibility(driver, edituser);
        driver.findElement(edituser).click();
        waithelpers.checkVisibility(driver, empname);
        driver.findElement(empname).click();
        driver.findElement(empname).sendKeys(Keys.CONTROL+"a");
        driver.findElement(empname).sendKeys(Keys.DELETE);
        driver.findElement(empname).sendKeys("Naveen Kumar");
        waithelpers.checkVisibility(driver,employee_name);
        driver.findElement(employee_name).click();
        waithelpers.checkVisibility(driver, username);
        driver.findElement(username).click();
        driver.findElement(username).sendKeys(Keys.CONTROL+"a");
        driver.findElement(username).sendKeys(Keys.DELETE);
        driver.findElement(username).sendKeys("Tejasurya99");
        driver.findElement(status).click();
        waithelpers.checkVisibility(driver, ststusdisabledoption);
        driver.findElement(ststusdisabledoption).click();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement savebtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type=\"submit\"]")));
        log.info("button visible");
        js.executeScript("arguments[0].click();", savebtn);
        Thread.sleep(5000);
        log.info("button clicked");
        waithelpers.checkVisibility(driver, admin);
    }





    }


