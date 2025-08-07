package com.tests;

import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Set5_TooltipValidationScenarioTest extends BaseTest {

    CartPage cartPage;

    @BeforeMethod
    public void setUpPage() {
        driver.get(Constants.URL);
        cartPage = new CartPage(driver);
    }

    //TestCase11 : Verify the Checkout  tooltip text
    @Test(priority = 11)
    public void verifyCheckoutTooltipText() {
        String expectedTooltip = "Please remove out-of-stock items to proceed";
        String actualTooltip = cartPage.getCheckoutTooltip();
        Assert.assertEquals(actualTooltip.trim(), expectedTooltip, "Tooltip text on checkout button should match expected message");
    }
}
