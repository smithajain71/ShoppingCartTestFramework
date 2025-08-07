
#  ShoppingCartTestFramework

An automated test framework for validating a web-based shopping cart using **Java**, **Selenium WebDriver**, **TestNG**, and **Gradle**. The project follows a Page Object Model and supports cross-browser testing with Chrome and Firefox.

---

##  Project Structure

```
ShoppingCartTestFramework/
├── README.md
├── .gitignore
├── build.gradle
├── testng.xml
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       ├── base/
│       │       │   ├── BaseTest.java
│       │       │   └── DriverFactory.java
│       │       ├── constants/
│       │       │   └── Constants.java
│       │       ├── pages/
│       │       │   └── CartPage.java
│       │       └── tests/
│       │           ├── Set1_DefaultPageTestScenarioTest.java
│       │           ├── Set2_DefaultCart_RemoveMugAndCancelScenarioTest.java
│       │           ├── Set3_Tshirt_Headphones_CheckoutScenarioTest.java
│       │           ├── Set4_ItemRemovalVariationsScenarioTest.java
│       │           ├── Set5_TooltipValidationScenarioTest.java
│       │           ├── Set6_EmptyCartTotal_CheckoutButton.java
│       │           ├── Set7_DiscountExpiryLogic.java
│       │           ├── Set8_QuantityValidation.java
│       │           ├── Set9_SubtotalMiscalculation.java
│       │           └── Set10_TShirtOutOfStock.java
│       └── resources/
│           └── testng.xml
```

---

##  Setup Instructions

###  Prerequisites

- Java 11+
- IntelliJ IDEA (or any Java IDE)
- Chrome & Firefox browsers installed
- Gradle (Optional if using IntelliJ auto-import)

###  WebDriver Setup

I used a custom **ThreadLocal Singleton Driver** — no need for WebDriverManager.

####  Chrome & Firefox Executables

Ensure drivers are downloaded and added to system path:

- ChromeDriver: https://chromedriver.chromium.org/downloads
- GeckoDriver (Firefox): https://github.com/mozilla/geckodriver/releases

You can set the path in your BaseTest or system environment variables:

---

##  How to Run the Tests

###  Run All Tests via Gradle

```bash
./gradlew clean test
```

> This will use `testng.xml` to run all tests in parallel.

###  Run Individual Test Class from Terminal

```bash
./gradlew test --tests "com.tests.Set1_DefaultPageTestScenarioTest"
```

Or in IntelliJ: Right-click on the test class → `Run`

###  Run with TestNG Suite in IntelliJ

1. Open `testng.xml`
2. Right-click → `Run testng.xml`

###  Run with Chrome or Firefox

Pass browser as JVM parameter (`firefox` is default):

```bash
./gradlew test -Dbrowser=chrome
./gradlew test -Dbrowser=firefox
```

---

## 📊 Reporting

<ShoppingCartTestFrameworkProject>/build/reports/tests/test/index.html

---

## 💡 Troubleshooting

- **Browser not launching?** Make sure `chromedriver` or `geckodriver` is in system PATH.

---

## 📬 Contact

Created by [Smitha Jain](https://github.com/smithajain71). For questions, raise an issue or ping via LinkedIn.
