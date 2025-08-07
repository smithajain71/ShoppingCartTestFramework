package com.tests;


import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Set9_SubtotalMiscalculationTest extends BaseTest{
    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get(Constants.URL);
        cartPage = new CartPage(driver);
    }

    /*
    Subtotal Miscalculation After Price Reset and Quantity Change
    Given: Bluetooth Headphones are in the cart
    When: The discount timer expires (price resets to $100) and the user increases quantity to 2
    Then: The subtotal should be 2 × $100 = $200.00; the discounted price should not be used after expiry
    Reason for failure:
    BugID: 5 - Subtotal Miscalculation
     */
    @Test(priority = 16)
    public void verifySubtotalAfterDiscountExpiresAndQuantityChanges() throws InterruptedException {
        // Wait for discount timer to expire (2 minutes)
        Assert.assertTrue(cartPage.isDiscountDisplayed(), "Discount should initially be shown");
        Thread.sleep(120000); // 2 minutes wait for discount to expire

        // Verify discount is gone and timer expired
        Assert.assertFalse(cartPage.isDiscountDisplayed(), "Discount label should be removed after expiry");
        Assert.assertFalse(cartPage.isTimerDisplayed(), "Timer should disappear after expiry");

        // Increase quantity to 2
        cartPage.updateQuantity("Bluetooth Headphones", 2);

        // Verify total is now 2 × $100 = $200.00
        Assert.assertEquals(cartPage.getTotalAmount(), "200.00", "Total should reflect updated full price after discount expiry");
    }
}
