package org.example.base;

import org.example.driver.DriverManager;
import org.example.utils.PropertyReader;

// static org.example.driver.DriverManager.driver;
import static org.example.driver.DriverManager.getDriver;

public class CommonToAll {

    public void openurl(){
        DriverManager.getDriver().get(PropertyReader.readkey("url"));
    }
}
