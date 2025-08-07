package com.tests;

import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Set10_TShirtOutOfStockTest extends BaseTest{

    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get(Constants.URL);
        cartPage = new CartPage(driver);
    }
    /*
     * This test is currently disabled because the functionality for simulating
     * or displaying “T-shirt out-of-stock” status is not implemented on the UI.
     *
     * Once the out-of-stock logic is available on the frontend,
     * this test can be enabled to validate the behavior.
     */
    @Test(enabled = false)
    public void verifyTShirtGoesOutOfStockAfterOthersRemoved() {

        // Remove Travel Mug
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        // Remove Bluetooth Headphones
        cartPage.removeItemByName("Bluetooth Headphones");
        cartPage.confirmRemove();

        // Simulate T-shirt going out of stock (you'll implement this)
        cartPage.simulateOutOfStock("Kid’s T-shirt");

        // Assertions
        Assert.assertFalse(cartPage.isCheckoutEnabled(), "Checkout should be disabled when all items are out of stock");
        Assert.assertEquals(cartPage.getTotalAmount(), "0.00", "Total should be $0.00 when nothing is purchasable");
        Assert.assertTrue(cartPage.isItemOutOfStock("Kid’s T-shirt"), "T-shirt should show out-of-stock label");
    }
}
