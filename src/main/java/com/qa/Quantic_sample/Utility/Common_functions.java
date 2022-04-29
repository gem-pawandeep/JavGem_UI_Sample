package com.qa.Quantic_sample.Utility;

import com.gemini.automation.generic.DriverAction;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.openqa.selenium.By;

import java.io.IOException;


public class Common_functions {
    public static void search(String item) throws IOException {
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        GemTestReporter.addTestStep("Action","Click on Search Box",STATUS.PASS,DriverAction.takeSnapShot());
        DriverAction.typeText(Amazon_locators.search_Box, item, "Element to be search");
        DriverAction.click(Amazon_locators.search_Button, "Search");
        GemTestReporter.addTestStep("Status", "Search Successful", STATUS.PASS);
    }

    public static void signInAmazon(String email, String pass) throws IOException {
        DriverAction.setImplicitTimeOut(5);
        DriverAction.setScriptTimeOut(5);
        DriverAction.setPageLoadTimeOut(5);
        DriverAction.click(Amazon_locators.sign_in_button, "Sign in");
        DriverAction.typeText(Amazon_locators.user_name_box, email, "Email");
        DriverAction.click(Amazon_locators.Continue_button, "Continue");
        GemTestReporter.addTestStep("Typing Password", "********", STATUS.PASS);
        DriverAction.typeText(Amazon_locators.user_password_box, pass);
        DriverAction.click(Amazon_locators.Continue_button, "Sign in");
    }

    public static void hyperLinkValidation(By link, String item) throws IOException {
        DriverAction.click(link, item);
        DriverAction.waitSec(2);
        String verify = DriverAction.getCurrentURL();
        if (verify.contains(item)) {
            GemTestReporter.addTestStep("Validation Successful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.PASS,DriverAction.takeSnapShot());
        } else {
            GemTestReporter.addTestStep("Validation Unsuccessful", "Current page : " + item + "<br>Current URL:" + DriverAction.getCurrentURL(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
}
