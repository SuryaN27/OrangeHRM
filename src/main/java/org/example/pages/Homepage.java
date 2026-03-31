package org.example.pages;

import org.example.utils.PropertyReader;
import org.example.utils.waithelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage {
     WebDriver driver;
    public Homepage(WebDriver driver){
        this.driver=driver;
    }
    private By Dashboard =By.xpath("//h1[text()=\"Dashboard\"]");

  public String verifyuser() {
      driver.get(PropertyReader.readkey("url"));
      waithelpers.checkVisibility(driver,Dashboard);
      String text =driver.findElement(Dashboard).getText();
      return text;
  }
  }
