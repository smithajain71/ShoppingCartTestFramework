package com.tests;

import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Set7_DiscountExpiryLogicTest extends BaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get(Constants.URL);
        cartPage = new CartPage(driver);
    }

    /*
    Discount Expiry Logic Failure
    Given: The Bluetooth Headphones are in the cart with a 15% discount and a 2-minute countdown timer
    When: The discount timer reaches 0 seconds
    Then: The price should reset to $100.00, the discount label should be removed, and the countdown timer
    should disappear
    Reason for failure :
    BugID: 3 Discount Expiry Logic Failure
     */
    @Test(priority = 14)
    public void verifyDiscountExpiryBehavior() throws InterruptedException {
        // Assert discount and timer are initially present

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        Assert.assertTrue(cartPage.isDiscountDisplayed(), "Discount should be shown initially");
       Assert.assertTrue(cartPage.isTimerDisplayed(), "Timer should be visible initially");

        // Wait 2 minutes for timer to expire (simulate countdown expiry)
        Thread.sleep(120000); // 2 minutes in milliseconds

        // After 2 mins: Discount label and timer should be gone, price reset to $100.00
        Assert.assertFalse(cartPage.isDiscountDisplayed(), "Discount label should be removed after expiry");
        Assert.assertFalse(cartPage.isTimerDisplayed(), "Timer should disappear after expiry");
        Assert.assertEquals(cartPage.getTotalAmount(), "100.00", "Price should reset to $100.00 after discount expires");
    }
}