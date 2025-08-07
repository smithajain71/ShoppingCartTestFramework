package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By totalAmount = By.id("totalAmount");
    private final By checkoutButton = By.id("checkoutBtn");
    private final By confirmRemoveButton = By.xpath("//div[@id='remove-confirm-modal']//button[contains(@class, 'confirm-remove')]");
    private final By cancelRemoveButton = By.xpath("//div[@id='remove-confirm-modal']//button[contains(@class, 'cancel-remove')]");
    private final By discountLocator = By.xpath("//div[@class='discount-badge']");
    private final By timerLocator = By.xpath("//div[@class='countdown-timer' and @id='timer']");

    private final By mugRegion = By.xpath("//div[contains(@class, 'cart-item') and .//*[contains(text(), 'Travel Mug')]]");
    private final By tshirtItem = By.xpath("//div[@class='product-name' and contains(text(), 'Kid’s T-shirt – Size M')]");
    private final By headphonesItem = By.xpath("//div[contains(@class, 'cart-item') and .//*[contains(text(), 'Bluetooth Headphones')]]");
    private final By headerTitle = By.xpath("//h1[contains(text(), 'Your Shopping Cart')]");
    private final By navHome = By.xpath("//nav//a[text()='Home']");
    private final By navProducts = By.xpath("//nav//a[text()='Products']");
    private final By navCart = By.xpath("//nav//a[text()='Cart']");
    private final By navProfile = By.xpath("//nav//a[text()='Profile']");

    public String getTotalAmount() {
        return driver.findElement(totalAmount).getText();
    }

    public boolean isCheckoutEnabled() {
        return driver.findElement(checkoutButton).isEnabled();
    }

    public String getCheckoutTooltip() {
        return driver.findElement(checkoutButton).getAttribute("title");
    }

    public void removeItemByName(String itemName) {
        String removeBtnXpath = "//div[contains(@class, 'cart-item') and .//*[contains(text(), '" + itemName + "')]]//button[contains(text(), 'Remove')]";
        WebElement removeBtn = driver.findElement(By.xpath(removeBtnXpath));
        removeBtn.click();
    }

    public void removeItemByIndex(int index) {
        String removeBtnXpath = "(//div[contains(@class, 'cart-item')]//button[contains(text(), 'Remove')])[" + (index + 1) + "]";
        WebElement removeBtn = driver.findElement(By.xpath(removeBtnXpath));
        removeBtn.click();
    }

    public void confirmRemove() {
        driver.findElement(confirmRemoveButton).click();
    }

    public void cancelRemove() {
        driver.findElement(cancelRemoveButton).click();
    }

    public boolean isDiscountDisplayed() {
        return !driver.findElements(discountLocator).isEmpty();
    }

    public boolean isTimerDisplayed() {
        return !driver.findElements(timerLocator).isEmpty();
    }

    public boolean isMugRegionEnabled() {
        List<WebElement> mugElements = driver.findElements(mugRegion);
        return !mugElements.isEmpty() && mugElements.get(0).isEnabled();
    }

    public boolean isTshirtVisible() {
        return !driver.findElements(tshirtItem).isEmpty();
    }

    public boolean isHeadphonesVisible() {
        return !driver.findElements(headphonesItem).isEmpty();
    }

    public boolean isHeaderVisible() {
        return !driver.findElements(headerTitle).isEmpty();
    }

    public boolean isNavHomeVisible() {
        return !driver.findElements(navHome).isEmpty();
    }

    public boolean isNavProductsVisible() {
        return !driver.findElements(navProducts).isEmpty();
    }

    public boolean isNavCartVisible() {
        return !driver.findElements(navCart).isEmpty();
    }

    public boolean isNavProfileVisible() {
        return !driver.findElements(navProfile).isEmpty();
    }

    public void updateQuantity(String itemName, int quantity) {
        String quantityInputXpath = "//div[contains(@class, 'cart-item') and .//*[contains(text(), '" + itemName + "')]]//input[@type='number']";
        WebElement quantityInput = driver.findElement(By.xpath(quantityInputXpath));
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        quantityInput.sendKeys("\n"); // Optional: trigger change event
    }

    public String getQuantityForItem(String itemName) {
        String quantityInputXpath = "//div[contains(@class, 'cart-item') and .//*[contains(text(), '" + itemName + "')]]//input[@type='number']";
        return driver.findElement(By.xpath(quantityInputXpath)).getAttribute("value");
    }


    // NOTE: The following WebElement is for a future improvement.
    // Current version of the cart page does NOT include this feature.
    // Once implemented on the UI, this locator and corresponding methods can be activated.
    public void simulateOutOfStock(String itemName) {
        String xpath = "//div[contains(@class, 'cart-item') and .//*[contains(text(), '" + itemName + "')]]";
        WebElement item = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].remove();", item); // Simulate out-of-stock by removing item
    }

    // NOTE: The following WebElement is for a future improvement.
    // Current version of the cart page does NOT include this feature.
    // Once implemented on the UI, this locator and corresponding methods can be activated.
    public boolean isItemOutOfStock(String itemName) {
        String outOfStockLabelXpath = "//div[contains(@class, 'cart-item') and .//*[contains(text(), '" + itemName + "')]]//*[contains(text(), 'Out of stock')]";
        return driver.findElements(By.xpath(outOfStockLabelXpath)).size() > 0;
    }
}