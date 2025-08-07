package com.tests;

import com.base.BaseTest;
import com.constants.Constants;
import com.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class Set2_DefaultCart_RemoveMugAndCancelScenarioTest extends BaseTest {

        CartPage cartPage;

        @BeforeMethod
        public void setUpPage() {
            driver.get(Constants.URL);
            cartPage = new CartPage(driver);
        }


       // Test Case2: Cart initially contains 3 items. Mug is out of stock and excluded from total. Checkout is disabled due to out-of-stock item. Total = $105.99. Also verify timer and discount label
       //Failure Reason BugID:6 -Incorrect Default Total
        @Test(priority = 1)
        public void verifyDefaultCartScenario() throws InterruptedException {
            Assert.assertFalse(cartPage.isCheckoutEnabled(), "Checkout should be disabled due to out-of-stock mug");
            Assert.assertEquals(cartPage.getTotalAmount(), "104.99", "Total should exclude mug and be $104.99");
            Assert.assertTrue(cartPage.isDiscountDisplayed(), "Discount label should be shown");
            Assert.assertTrue(cartPage.isTimerDisplayed(), "Timer should be visible");
        }

        //Test Case3: Out-of-stock mug is removed from cart. T-Shirt and Headphones items are in stock. Checkout is enabled. Total = $105.99.
        //Failure Reason BugID:6 -Incorrect Default Total
        @Test(priority = 2)
        public void verifyMugRemovedScenario() {
            cartPage.removeItemByName("Travel Mug");
            cartPage.confirmRemove();
            Assert.assertTrue(cartPage.isTshirtVisible(), "T-shirt should remain after mug removal");
            Assert.assertTrue(cartPage.isHeadphonesVisible(), "Headphones should remain after mug removal");
            Assert.assertTrue(cartPage.isCheckoutEnabled(), "Checkout should be enabled after mug removal");
            Assert.assertEquals(cartPage.getTotalAmount(), "104.99", "Total should remain $104.99 after mug removal");
        }

        //Test Case4: User clicks 'Remove' on mug but cancels action. Mug remains, Checkout remains disabled. Total = $105.99.
        //Failure Reason BugID:6 -Incorrect Default Total
        @Test(priority = 3)
        public void verifyMugRemovalCancelledScenario() {
            cartPage.removeItemByName("Travel Mug");
            cartPage.cancelRemove();
            Assert.assertTrue(cartPage.isMugRegionEnabled(), "Mug should still be present after cancel");
            Assert.assertFalse(cartPage.isCheckoutEnabled(), "Checkout should remain disabled since mug was not removed");
            Assert.assertEquals(cartPage.getTotalAmount(), "104.99", "Total should still be $104.99 with mug included but excluded from total");
        }
    }



