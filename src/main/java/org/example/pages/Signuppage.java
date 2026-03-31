package org.example.pages;
import org.example.utils.PropertyReader;
import org.example.utils.waithelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;
// TTA login page
public class Signuppage {
    WebDriver driver;

    public Signuppage(WebDriver driver) {
        this.driver = driver;
    }

    private By signin_btn = By.xpath("//button[text()=\"Sign Up\"]");
    private By u_name = By.xpath("//input[@placeholder=\"John Doe\"]");
    private By pwd = By.xpath("//input[@type=\"password\"]");
    private By emailfiled = By.xpath("//input[@type=\"email\"]"); //button[@type="submit"]
    private By submit = By.xpath("//button[@type=\"submit\"]");


    public void newuser_ignin(String username, String email, String pwod) {
        driver.get(PropertyReader.readkey("url"));
        driver.manage().window().maximize();
        waithelpers.waitImplicitWait(driver,10);
        driver.findElement(signin_btn).click();
        System.out.println("sign click");
        driver.findElement(u_name).sendKeys(username);
        driver.findElement(emailfiled).sendKeys(email);
        driver.findElement(pwd).sendKeys(pwod);
        driver.findElement(submit).click();
    }
}