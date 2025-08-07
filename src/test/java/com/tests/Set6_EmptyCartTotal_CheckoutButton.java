package com.tests;

import com.base.BaseTest;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Set6_EmptyCartTotal_CheckoutButton extends BaseTest{
    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get("https://gb-saa-test.vercel.app/#");
        cartPage = new CartPage(driver);
    }

    /*
    Bug-Cart Total Not Reset After Item Removal
    Given: All items, including the out-of-stock mug, are removed from the cart
    When: The cart is completely empty
    Then: The cart total should reset to $0.00
    Reason for failures
    BugID: 1 Cart Total Not Reset After Item Removal
    BugID: 2 Checkout Button Still Visible with Empty Cart
     */
    @Test(priority = 12)
    public void cartTotalShouldResetAfterItemRemoval() {
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Bluetooth Headphones");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Kid’s T-shirt");
        cartPage.confirmRemove();

        Assert.assertEquals(cartPage.getTotalAmount(), "0.00", "Cart total should reset to $0.00 after all items removed");
    }

    /*
    Bug-Checkout Button Still Visible with Empty Cart
    Given: The cart contains no purchasable items (empty or only out-of-stock items)
    When: The user navigates to the cart
    Then: The Checkout button should be disabled or hidden
     */
    @Test(priority = 13)
    public void checkoutShouldBeDisabledOrHiddenWhenCartIsEmpty() {
        cartPage.removeItemByName("Travel Mug");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Bluetooth Headphones");
        cartPage.confirmRemove();

        cartPage.removeItemByName("Kid’s T-shirt");
        cartPage.confirmRemove();

        Assert.assertFalse(cartPage.isCheckoutEnabled(), "Checkout button should be disabled or hidden when cart is empty");
    }
}
