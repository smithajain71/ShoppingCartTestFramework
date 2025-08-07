package com.tests;
import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Set3_Tshirt_Headphones_CheckoutScenarioTest extends BaseTest{

    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get(Constants.URL);
        cartPage = new CartPage(driver);
    }

    //T-Shirt and Headphones quantities updated to 2 each. Mug is removed. All items are in stock. Checkout is enabled. Total = $209.98.
    //Failure Reason BugID:6 -Incorrect Default Total
    @Test(priority = 5)
    public void TShirt2_Headphones2_Checkout() {
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        cartPage.updateQuantity("Kid’s T-shirt", 2);
        cartPage.updateQuantity("Bluetooth Headphones", 2);

        Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout should be enabled");
        Assert.assertEquals(cartPage.getTotalAmount(), "209.98", "Total should be $209.98");
    }

    //Headphones quantity increased to 2. T-Shirt remains at 1. Mug removed. Checkout enabled. Total = $189.99.
    //Failure Reason BugID:6 -Incorrect Default Total
    @Test(priority = 6)
    public void TShirt1_Headphones2_Checkout() {
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        cartPage.updateQuantity("Kid’s T-shirt", 1);
        cartPage.updateQuantity("Bluetooth Headphones", 2);

        Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout should be enabled");
        Assert.assertEquals(cartPage.getTotalAmount(), "189.99", "Total should be $189.99");
    }

    //T-Shirt quantity is 2. Headphones quantity is 1. Mug is removed. Checkout is enabled. Total = $124.98.
    //Failure Reason BugID:6 -Incorrect Default Total
    @Test(priority = 7)
    public void TShirt2_Headphones1_Checkout() {
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        cartPage.updateQuantity("Kid’s T-shirt", 2);
        cartPage.updateQuantity("Bluetooth Headphones", 1);

        Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout should be enabled");
        Assert.assertEquals(cartPage.getTotalAmount(), "124.98", "Total should be $124.98");
    }
}
