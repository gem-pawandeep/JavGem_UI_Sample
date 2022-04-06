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
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.first_result, "First result");
        DriverAction.waitSec(2);
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
        DriverAction.click(Amazon_locators.high_low, "high to low");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.first_result, "First result");
        DriverAction.waitSec(2);
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

    public static void maxPrice(String item) {
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        DriverAction.click(Amazon_locators.high_low, "high to low");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.first_result, "result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.waitSec(2);
        DriverAction.switchToWindow(newTb.get(1));
        GemTestReporter.addTestStep("Result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        GemTestReporter.addTestStep("Control transfer to previous tab", "Successful", STATUS.PASS);
        DriverAction.waitSec(2);
        DriverAction.switchToWindow(newTb.get(0));
    }

    public static void minPrice(String item) {
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        DriverAction.click(Amazon_locators.low_high, "low to high");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.first_result, "result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(1));
        GemTestReporter.addTestStep("Result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        GemTestReporter.addTestStep("Control transfer to previous tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(0));
    }

    public static void diffMaxMin(String item) {
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        DriverAction.click(Amazon_locators.high_low, "high to low");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.first_result, "First result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(1));
        String temp = DriverAction.getElementText(Amazon_locators.price);
        String price = temp.replace(",", "");
        int high = Integer.parseInt(price);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        DriverAction.switchToWindow(newTb.get(0));
        DriverAction.navigateRefresh();
        DriverAction.click(Amazon_locators.pricedrpdwn, "Sort by:");
        DriverAction.click(Amazon_locators.low_high, "low to high");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.first_result, "First result");
        ArrayList<String> newTb1 = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Control transfer to new tab", "Successful", STATUS.PASS);
        DriverAction.switchToWindow(newTb1.get(1));
        String temp1 = DriverAction.getElementText(Amazon_locators.price);
        String price1 = temp1.replace(",", "");
        int low = Integer.parseInt(price1);
        GemTestReporter.addTestStep("first result", "Name:" + DriverAction.getElementText(Amazon_locators.tittle) + "<br>Price:" + DriverAction.getElementText(Amazon_locators.price), STATUS.PASS);
        DriverManager.closeDriver();
        DriverAction.switchToWindow(newTb1.get(0));
        GemTestReporter.addTestStep("Difference", " " + (high - low), STATUS.PASS);

    }

    public static void validateLanguage(String lang) {
        DriverAction.click(Amazon_locators.lang_button, "language");
        DriverAction.waitSec(2);
        if (lang.equals("hi")) {
            DriverAction.click(Amazon_locators.hindi, "Hindi");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String hindi = DriverAction.getCurrentURL();
            if (hindi.contains("hi")) {
                GemTestReporter.addTestStep("Validation", "Current page in Hindi Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Hindi Language", STATUS.FAIL);
            }
        } else if (lang.equals("ta")) {
            DriverAction.click(Amazon_locators.tamil, "Tamil");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("ta")) {
                GemTestReporter.addTestStep("Validation", "Current page in Tamil Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Tamil Language", STATUS.FAIL);
            }
        } else if (lang.equals("te")) {
            DriverAction.click(Amazon_locators.telgu, "Telgu");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("te")) {
                GemTestReporter.addTestStep("Validation", "Current page in Telgu Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Telgu Language", STATUS.FAIL);
            }
        } else if (lang.equals("kn")) {
            DriverAction.click(Amazon_locators.Kannda, "Kannada");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("kn")) {
                GemTestReporter.addTestStep("Validation", "Current page in Kannada Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Kannada Language", STATUS.FAIL);
            }
        } else if (lang.equals("ml")) {
            DriverAction.click(Amazon_locators.malyalam, "Malyalam");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("ml")) {
                GemTestReporter.addTestStep("Validation", "Current page in Malyalam Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Malyalam Language", STATUS.FAIL);
            }
        } else if (lang.equals("bn")) {
            DriverAction.click(Amazon_locators.bangla, "Bangla");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("bn")) {
                GemTestReporter.addTestStep("Validation", "Current page in Bangla Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Bangla Language", STATUS.FAIL);
            }
        } else if (lang.equals("mr")) {
            DriverAction.click(Amazon_locators.marathi, "Marathi");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("mr")) {
                GemTestReporter.addTestStep("Validation", "Current page in Marathi Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in Marathi Language", STATUS.FAIL);
            }
        } else if (lang.equals("en")) {
            DriverAction.click(Amazon_locators.English, "English");
            DriverAction.click(Amazon_locators.lang_submit, "Save changes");
            DriverAction.waitSec(2);
            String eng = DriverAction.getCurrentURL();
            if (eng.contains("en")) {
                GemTestReporter.addTestStep("Validation", "Current page in English Language<br>", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validation", "Current page Not in English Language", STATUS.FAIL);
            }
        }

    }

    public static void alexaDot(){
        DriverAction.click(Amazon_locators.all,"All");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.echodot,"Echo & Alexa");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.item,"Echo Dot");
        DriverAction.waitSec(2);
        GemTestReporter.addTestStep("Title",DriverAction.getElementText(Amazon_locators.tittle),STATUS.PASS);
        GemTestReporter.addTestStep("Price",DriverAction.getElementText(Amazon_locators.price),STATUS.PASS);
    }

    public static void cartAfterAdding(String item){
        Common_functions.search(item);
        DriverAction.click(Amazon_locators.first_result,"first result");
        ArrayList<String> newTb = new ArrayList<>(DriverManager.getWebDriver().getWindowHandles());
        GemTestReporter.addTestStep("Action", "Switching control to new Tab", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(1));
        DriverAction.waitSec(2);
        String temp1=DriverAction.getElementText(Amazon_locators.tittle);
        DriverAction.click(Amazon_locators.addToCart,"Add to cart");
        DriverAction.waitSec(2);
        DriverAction.click(Amazon_locators.cart_icon,"Cart");
        String temp2=DriverAction.getElementText(Amazon_locators.cartTitle);
        if(temp1.equals(temp2)){
            GemTestReporter.addTestStep("Validation",temp2+"added Successfully to Cart",STATUS.PASS);
        }
        else {
            GemTestReporter.addTestStep("Validation","Unsuccessful",STATUS.FAIL);
        }
        DriverManager.closeDriver();
        GemTestReporter.addTestStep("Action", "Control back to Previous Tab", STATUS.PASS);
        DriverAction.switchToWindow(newTb.get(0));
    }

    public static void locationValidation(String pincode){
        DriverAction.click(Amazon_locators.locationButton,"Location Button");
        DriverAction.waitSec(2);
        DriverAction.typeText(Amazon_locators.locationText,pincode,"Location");
        DriverAction.click(Amazon_locators.locationSubmit,"Apply");
        DriverAction.waitSec(2);
        String temp=DriverAction.getElementText(Amazon_locators.locationValidate);
        if(temp.contains(pincode)){
            GemTestReporter.addTestStep("Validate location",temp,STATUS.PASS);
        }
        else {
            GemTestReporter.addTestStep("Validate location",temp,STATUS.PASS);
        }


    }
}
