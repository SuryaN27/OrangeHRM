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
    private By pim=By.xpath("//span[text()=\"PIM\"]");
   // private By pim=By.xpath("//span[text()=\"PIM\"]");
    private By addEmp=By.xpath("//a[text()=\"Add Employee\"]");
    private By empf_name=By.xpath("//input[@name=\"firstName\"]");
    private By empl_name=By.xpath("//input[@name=\"lastName\"]");
    private By empsavebtn=By.xpath("//button[@type=\"submit\"]");
    private By spop=By.xpath("//p[text()=\"Successfully Saved\"]");


    public void add_Employee(){
        driver.manage().window().maximize();
        waithelpers.checkVisibility(driver,pim);
        driver.findElement(pim).click();
        waithelpers.checkVisibility(driver,addEmp);
        driver.findElement(addEmp).click();
        waithelpers.checkVisibility(driver,empf_name);
        driver.findElement(empf_name).sendKeys(PropertyReader.readkey("Empname"));
        waithelpers.checkVisibility(driver,empl_name);
        driver.findElement(empl_name).sendKeys(PropertyReader.readkey("LEmpname"));
        waithelpers.checkVisibility(driver,empsavebtn);
        driver.findElement(empsavebtn).click();
        waithelpers.checkVisibility(driver,spop);
         String text=driver.findElement(spop).getText();
        System.out.println(text);




    }

    public String Verifyusername(String name){
        driver.get(PropertyReader.readkey("orange_url"));
        driver.manage().window().maximize();
        waithelpers.checkVisibility(driver,pim);
        driver.findElement(pim).click();
        waithelpers.checkVisibility(driver,dname);
        String dtext =driver.findElement(dname).getText();
        return dtext;
    }

}
