package com.base;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("firefox")String browser) {
        DriverFactory.setDriver(browser);
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}