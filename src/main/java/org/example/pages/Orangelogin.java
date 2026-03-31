package org.example.pages;

import org.example.driver.DriverManager;
import org.example.utils.PropertyReader;
import org.example.utils.waithelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;

public class Orangelogin {
    WebDriver driver;

    public Orangelogin(WebDriver driver) {
        this.driver = driver;
    }

    private By uname = By.xpath("//input[@name=\"username\"]");
    private By pwd = By.xpath("//input[@name=\"password\"]");
    private By login = By.xpath("//button[@type=\"submit\"]");
    private By forgotpwd = By.xpath("//p[text()=\"Forgot your password? \"]");
    private By errormsg = By.xpath("//p[text()=\"Invalid credentials\"]");
    private By uemptymsg = By.xpath("//input[@name='username']/following::span[text()=\"Required\"][1]");
    private By pwdemptymsg = By.xpath("//input[@name='username']/following::span[text()=\"Required\"][2]");
     private By forgotpwdlink =By.xpath("//p[text()=\"Forgot your password? \"]");
     private By forgottxt= By.xpath("//p[text()=\"The OrangeHRM  system is not configured to receive email notifications. Please contact your OrangeHRM administrator to reset your password\"]");

    public String Invalidlogin(String name, String password) {
        driver.get(PropertyReader.readkey("orange_url"));
        driver.manage().window().maximize();
        waithelpers.checkVisibility(driver,uname);
        driver.findElement(uname).sendKeys(name);
        driver.findElement(pwd).sendKeys(password);
        driver.findElement(login).click();
        waithelpers.checkVisibility(driver,errormsg);
        String errortext = driver.findElement(errormsg).getText();
        return errortext;
    }
    public void validlogin(String name, String password) {
        driver.get(PropertyReader.readkey("orange_url"));
        driver.manage().window().maximize();
        waithelpers.checkVisibility(driver,uname);
        driver.findElement(uname).sendKeys(name);
        driver.findElement(pwd).sendKeys(password);
        driver.findElement(login).click();
    }
    public String[] Emptylogin() {
        driver.get(PropertyReader.readkey("orange_url"));
        driver.manage().window().maximize();
        waithelpers.checkVisibility(driver, uname);
        driver.findElement(login).click();
        String emsg = driver.findElement(uemptymsg).getText();
        String epwd = driver.findElement(pwdemptymsg).getText();
        return new String[]{emsg, epwd};
    }
     public String Forgotpwd(){
         driver.get(PropertyReader.readkey("orange_url"));
         driver.manage().window().maximize();
         waithelpers.checkVisibility(driver,forgotpwdlink);
         driver.findElement(forgotpwdlink).click();
         waithelpers.checkVisibility(driver,forgottxt);
         String fwdtext= driver.findElement(forgottxt).getText();
         driver.navigate().back();
         return fwdtext;
     }
    }



