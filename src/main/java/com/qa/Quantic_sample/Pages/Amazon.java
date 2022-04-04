package com.qa.Quantic_sample.Pages;

import com.gemini.automation.generic.DriverAction;
import com.gemini.automation.generic.DriverManager;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.Quantic_sample.Utility.Common_functions;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class Amazon {
    public static void ValidatingUrl() {
        String s = "";
        s = DriverAction.getTitle(DriverAction.getCurrentURL());
        if (s.contains("Amazon.in")) {
            GemTestReporter.addTestStep("Validating URL", "Expected: Amazon.in<br>Current: Amazon.in", STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validating URL", "Expected: Amazon.in<br>Current: " + DriverAction.getTitleWithoutReporting(DriverAction.getCurrentURL()), STATUS.FAIL);
        }
    }

    public static void SignIn(String email, String pass) {
        Common_functions.signInAmazon(email, pass);
    }

    public static void firstResultPrice(String item) {
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.first_result, "First result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(1));
        GemTestReporter.addTestStep("Title of first Result", DriverAction.getElementText(Amazon_locators.tittle), STATUS.PASS);
        GemTestReporter.addTestStep("Price of first Result", "Rs " + DriverAction.getElementText(Amazon_locators.price1), STATUS.PASS);
        GemTestReporter.addTestStep("Action", "Closing the Current Tab", STATUS.PASS);
        DriverManager.closeDriver();
        GemTestReporter.addTestStep("Action", "Control back to Previous Tab", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(0));
    }

    public static void lowToHigh(String item) {
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        DriverAction.click(Amazon_locators.low_high, "low to high");
        DriverAction.click(Amazon_locators.first_result, "First result");
        DriverAction.click(Amazon_locators.second_result, "Second result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(2));
        int price2 = Integer.parseInt(DriverAction.getElementText(Amazon_locators.price));
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(1));
        int price1 = Integer.parseInt(DriverAction.getElementText(Amazon_locators.price));
        GemTestReporter.addTestStep("second result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        DriverAction.switchToWindow(newTb.get(0));
        if (price2 < price1) {
            GemTestReporter.addTestStep("Validate low to high", "Successful as " + price2 + " < " + price1, STATUS.PASS);
        } else if (price2 == price1) {
            GemTestReporter.addTestStep("Validate low to high", "Successful as " + price2 + " = " + price1, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validate low to high", "Unsuccessful", STATUS.FAIL);
        }
    }

    public static void highToLow(String item) {
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        DriverAction.click(Amazon_locators.high_low, "low to high");
        DriverAction.click(Amazon_locators.first_result, "First result");
        DriverAction.click(Amazon_locators.second_result, "Second result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(2));
        String temp = DriverAction.getElementText(Amazon_locators.price);
        String price = temp.replace(",", "");
        int price2 = Integer.parseInt(price);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(1));
        String temp1 = DriverAction.getElementText(Amazon_locators.price);
        String pricee = temp1.replace(",", "");
        int price1 = Integer.parseInt(pricee);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        DriverAction.switchToWindow(newTb.get(0));
        if (price2 > price1) {
            GemTestReporter.addTestStep("Validate high to low", "Successful as " + price2 + " > " + price1, STATUS.PASS);
        } else if (price2 == price1) {
            GemTestReporter.addTestStep("Validate high to low", "Successful as " + price2 + " = " + price1, STATUS.PASS);
        } else {
            GemTestReporter.addTestStep("Validate low to high", "Unsuccessful", STATUS.FAIL);
        }
    }

    public static void validateHyperlink(By link, String item) {
        Common_functions.hyperLinkValidation(link, item);
    }
}
