package com.tests;

import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Set1_DefaultPageTestScenarioTest extends BaseTest {

    /*
    Test Case1: Page title should be 'Shopping Cart'. 'Home', 'Product', and 'Profile' are placeholders.
    Verify presence and layout only; link functionality is out of scope for Phase 1
    Verify the presence of products
    */
    @Test
    public void verifyCartPageDefaultElements() {
        driver.get(Constants.URL);
        CartPage cartPage = new CartPage(driver);

        // Header & Navigation
        Assert.assertTrue(cartPage.isHeaderVisible(), "Header 'Your Shopping Cart' is not visible");
        Assert.assertTrue(cartPage.isNavHomeVisible(), "Home link is not visible");
        Assert.assertTrue(cartPage.isNavProductsVisible(), "Products link is not visible");
        Assert.assertTrue(cartPage.isNavCartVisible(), "Cart link is not visible");
        Assert.assertTrue(cartPage.isNavProfileVisible(), "Profile link is not visible");

        // Items
        Assert.assertTrue(cartPage.isTshirtVisible(), "Kid’s T-shirt – Size M is not visible");
        Assert.assertTrue(cartPage.isHeadphonesVisible(), "Bluetooth Headphones is not visible");
        Assert.assertTrue(cartPage.isMugRegionEnabled(), "Travel Mug region is not enabled");
    }


}
