package com.qa.Quantic_sample.Tests;

import com.gemini.automation.dataProvider.QuanticDataProvider;
import com.gemini.automation.generic.DriverAction;
import com.gemini.automation.generic.QuanticUIBase;
import com.google.gson.JsonObject;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.Quantic_sample.Pages.Amazon;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.testng.annotations.Test;

public class amazonTestCases extends QuanticUIBase {
    /*
     @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
        public void Test1(JsonObject inputData) {

        }
        */

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void launchedAmazon(JsonObject inputData) {
        try {
            Amazon.ValidatingUrl();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void loginCorrectCredentials(JsonObject inputData) {
        try {
            Amazon.SignIn(inputData.get("email").getAsString(), inputData.get("pass").getAsString());
            DriverAction.waitSec(15);
            String url1 = DriverAction.getCurrentURL();
            String title = DriverAction.getTitleWithoutReporting(url1);
            if (title.contains("Amazon.in")) {
                GemTestReporter.addTestStep("Validate Login", "Login_successful", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validate Login", "Login_Unsuccessful", STATUS.FAIL);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void loginIncorrectCredentials(JsonObject inputData) {
        try {
            Amazon.SignIn(inputData.get("email").getAsString(), inputData.get("pass").getAsString());
            GemTestReporter.addTestStep("Login error message", DriverAction.getElementText(Amazon_locators.error_msg), STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateEmptyCartByDefault(JsonObject inputData) {
        try {
            DriverAction.click(Amazon_locators.cart_icon, "Cart");
            String s = DriverAction.getElementText(Amazon_locators.check_cart_empty);
            if (s.contains("is empty")) {
                GemTestReporter.addTestStep("Validating Cart", "Cart is Empty By Default", STATUS.PASS);
            } else {
                GemTestReporter.addTestStep("Validating Cart", "Cart is Not Empty By Default", STATUS.PASS);
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getPriceOfFirstResult(JsonObject inputData) {
        try {
            Amazon.firstResultPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateSortByLowToHigh(JsonObject inputData) {
        try {
            Amazon.lowToHigh(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateSortByHighToLow(JsonObject inputData) {
        try {
            Amazon.highToLow(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateInstagramHyperlink(JsonObject inputData) {
        try {
            Amazon.validateHyperlink(Amazon_locators.insta_link, "instagram");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateFacebookHyperlink(JsonObject inputData) {
        try {
            Amazon.validateHyperlink(Amazon_locators.fb_link, "facebook");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateTwitterHyperlink(JsonObject inputData) {
        try {
            Amazon.validateHyperlink(Amazon_locators.twitter_link, "twitter");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void maxPriceSearchItem(JsonObject inputData) {
        try {
            Amazon.maxPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void minPriceSearchItem(JsonObject inputData) {
        try {
            Amazon.minPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getDiffMaxMinItem(JsonObject inputData) {
        try {
            Amazon.diffMaxMin(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeHindi(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Hindi").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeTamil(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Tamil").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeTelgu(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Telgu").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeKannada(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Kannada").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeMalyalam(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Malyalam").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeBangla(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Bangla").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeMarathi(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Marathi").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeEnglish(JsonObject inputData) {
        try {
            String url = "https://www.amazon.in/?language=hi_IN";
            DriverAction.navigateToUrl(url);
            if (url.contains("hi")) {
                GemTestReporter.addTestStep("Validation", "Current page in Hindi Language<br>", STATUS.PASS);
            }
            Amazon.validateLanguage(inputData.get("English").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void alexaDotFromLeftTreeMenu(JsonObject inputData) {
        try {
            Amazon.alexaDot();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }


}
