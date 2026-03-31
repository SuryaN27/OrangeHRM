package org.example.pages;

import org.example.utils.PropertyReader;
import org.example.utils.waithelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Orangedashboard {

    WebDriver driver;

    public Orangedashboard(WebDriver driver){
        this.driver=driver;
    }

    //private By d_name=By.xpath("//h6[text()=PIM]");
    private By dname= By.xpath("//h6[text()=\"PIM\"]");

    public String Verifyusername(String name){
        driver.get(PropertyReader.readkey("orange_url"));
        driver.manage().window().maximize();
        waithelpers.checkVisibility(driver,dname);
        String dtext =driver.findElement(dname).getText();
        return dtext;
    }

}
