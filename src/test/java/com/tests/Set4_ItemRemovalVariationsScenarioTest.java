package com.tests;

import com.base.BaseTest;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Set4_ItemRemovalVariationsScenarioTest extends BaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get("https://gb-saa-test.vercel.app/#");
        cartPage = new CartPage(driver);
    }

    //TestCase 8: Both Headphones and Mug are removed. Only T-Shirt remains. Checkout is enabled. Total = $19.99.
    @Test(priority = 8)
    public void OnlyTShirtInCart() {
        cartPage.removeItemByName("Bluetooth Headphones");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        Assert.assertTrue(cartPage.isTshirtVisible(), "Only T-Shirt should remain");
        Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout should be enabled with only T-Shirt");
        Assert.assertEquals(cartPage.getTotalAmount(), "19.99", "Total should be $19.99");
    }

    //TestCase 9:T-Shirt and Mug are removed. Only Headphones remain. Checkout is enabled. Total = $85.00.
    @Test(priority = 9)
    public void OnlyHeadphonesInCart() {
        cartPage.removeItemByName("Kid’s T-shirt");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        Assert.assertTrue(cartPage.isHeadphonesVisible(), "Only Headphones should remain");
        Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout should be enabled with only Headphones");
        Assert.assertEquals(cartPage.getTotalAmount(), "85.00", "Total should be $85.00");
    }

    //TestCase 10:Mug, then Headphones, then T-Shirt are removed one-by-one. Cart ends empty. Checkout is disabled. Total = $0.00.
    @Test(priority = 10)
    public void NothingInTheCart() {
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Bluetooth Headphones");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Kid’s T-shirt");
        cartPage.confirmRemove();

        Assert.assertFalse(cartPage.isCheckoutEnabled(), "Checkout should be disabled with empty cart");
        Assert.assertEquals(cartPage.getTotalAmount(), "0.00", "Total should be $0.00 with empty cart");
    }
}