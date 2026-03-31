package org.example.basetest;

import org.example.driver.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonToAllTest {

    @BeforeMethod
    public void Setup(){
        DriverManager.init();
    }
    @AfterMethod
    public void Teardown(){
    DriverManager.down();
    }
}
