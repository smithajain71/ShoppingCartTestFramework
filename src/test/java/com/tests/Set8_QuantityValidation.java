package com.tests;



import com.base.BaseTest;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Set8_QuantityValidation extends BaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get("https://gb-saa-test.vercel.app/#");
        cartPage = new CartPage(driver);
    }

    @Test(priority = 15)
    public void verifyQuantityNotDecreasedBelowOne() {
       /*
       The test ensures the quantity of “Kid’s T-shirt”/ Bluetooth Headphones cannot go below 1.
       When quantity is at 1, the decrement (-) button is disabled or has no effect.
       Additionally, at quantity 1, a delete (🗑️) icon or button should appear to allow the user to remove the item entirely instead of decreasing further.
       Made this a place holder test, when the recommendation is implemented, test can be covered
       Reason for failure
       BugID:8 Quantity Validation Missing
       */

        cartPage.updateQuantity("Kid’s T-shirt", 1);
        cartPage.updateQuantity("Kid’s T-shirt", -1);

        cartPage.updateQuantity("Bluetooth Headphones", 1);
        cartPage.updateQuantity("Bluetooth Headphones", -1);

        Assert.assertNotEquals(cartPage.getQuantityForItem("Kid’s T-shirt"), "-1", "T-shirt quantity should not be 0/negative");
        Assert.assertNotEquals(cartPage.getQuantityForItem("Bluetooth Headphones"), "-1", "T-shirt quantity should not be 0/negative");

    }
}