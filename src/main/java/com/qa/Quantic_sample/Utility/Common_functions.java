package com.qa.Quantic_sample.Utility;

import com.gemini.automation.generic.DriverAction;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.By;

import static com.gemini.automation.generic.DriverAction.takeSnapShotBase64;

public class Common_functions {
    public static void search(String item) {
        GemTestReporter.addTestStep("Action","Click on Search Box",STATUS.PASS,takeSnapShotBase64());
        DriverAction.typeText(Amazon_locators.search_Box, item, "Element to be search");
        DriverAction.click(Amazon_locators.search_Button, "Search");
        GemTestReporter.addTestStep("Status", "Search Successful", STATUS.PASS);
    }

    public static void signInAmazon(String email, String pass) {
        DriverAction.click(Amazon_locators.sign_in_button, "Sign in");
        DriverAction.typeText(Amazon_locators.user_name_box, email, "Email");
        DriverAction.click(Amazon_locators.Continue_button, "Continue");
        GemTestReporter.addTestStep("Typing Password", "********", STATUS.PASS);
        DriverAction.typeText(Amazon_locators.user_password_box, pass);
        DriverAction.click(Amazon_locators.Continue_button, "Sign in");
    }

    public static void hyperLinkValidation(By link, String item) {
        DriverAction.click(link, item);
        DriverAction.waitSec(2);
        String verify = DriverAction.getCurrentURL();
        if (verify.contains(item)) {
            GemTestReporter.addTestStep("Validation Successful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.PASS,takeSnapShotBase64());
        } else {
            GemTestReporter.addTestStep("Validation Unsuccessful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.FAIL,takeSnapShotBase64());
        }
    }
}
