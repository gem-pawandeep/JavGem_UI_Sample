package com.qa.Quantic_sample.Tests;

import com.gemini.automation.dataProvider.QuanticDataProvider;
import com.gemini.automation.generic.DriverAction;
import com.gemini.automation.generic.QuanticUIBase;
import com.google.gson.JsonObject;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.Quantic_sample.Pages.Amazon;
import com.qa.gemini.quartzReporting.GemTestReporter;
import com.qa.gemini.quartzReporting.STATUS;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.gemini.automation.generic.DriverAction.takeSnapShotBase64;

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
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void loginCorrectCredentials(JsonObject inputData) {
        try {
            Amazon.SignIn(inputData.get("email").getAsString(), inputData.get("pass").getAsString());
            DriverAction.waitSec(20);
            String url1 = DriverAction.getCurrentURL();
            String title = DriverAction.getTitle(url1);
            if (title.contains("Amazon.in")) {
                GemTestReporter.addTestStep("Validate Login", "Login_successful", STATUS.PASS,takeSnapShotBase64());
            } else {
                GemTestReporter.addTestStep("Validate Login", "Login_Unsuccessful", STATUS.FAIL,takeSnapShotBase64());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void loginIncorrectCredentials(JsonObject inputData) {
        try {
            Amazon.SignIn(inputData.get("email").getAsString(), inputData.get("pass").getAsString());
            GemTestReporter.addTestStep("Login error message", DriverAction.getElementText(Amazon_locators.error_msg), STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateEmptyCartByDefault(JsonObject inputData) {
        try {
            DriverAction.click(Amazon_locators.cart_icon, "Cart");
            String s = DriverAction.getElementText(Amazon_locators.check_cart_empty);
            if (s.contains("is empty")) {
                GemTestReporter.addTestStep("Validating Cart", "Cart is Empty By Default", STATUS.PASS,takeSnapShotBase64());
            } else {
                GemTestReporter.addTestStep("Validating Cart", "Cart is Not Empty By Default", STATUS.PASS,takeSnapShotBase64());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getPriceOfFirstResult(JsonObject inputData) {
        try {
            Amazon.firstResultPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateSortByLowToHigh(JsonObject inputData) {
        try {
            Amazon.lowToHigh(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateSortByHighToLow(JsonObject inputData) {
        try {
            Amazon.highToLow(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateInstagramHyperlink(JsonObject inputData) {
        try {
            Amazon.validateHyperlink(Amazon_locators.insta_link, "instagram");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateFacebookHyperlink(JsonObject inputData) {
        try {
            Amazon.validateHyperlink(Amazon_locators.fb_link, "facebook");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateTwitterHyperlink(JsonObject inputData) {
        try {
            Amazon.validateHyperlink(Amazon_locators.twitter_link, "twitter");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void maxPriceSearchItem(JsonObject inputData) {
        try {
            Amazon.maxPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void minPriceSearchItem(JsonObject inputData) {
        try {
            Amazon.minPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getDiffMaxMinItem(JsonObject inputData) {
        try {
            Amazon.diffMaxMin(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeHindi(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Hindi").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeTamil(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Tamil").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeTelgu(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Telgu").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeKannada(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Kannada").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeMalyalam(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Malyalam").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeBangla(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Bangla").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeMarathi(JsonObject inputData) {
        try {
            Amazon.validateLanguage(inputData.get("Marathi").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
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
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void alexaDotFromLeftTreeMenu(JsonObject inputData) {
        try {
            Amazon.alexaDot();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateCartAfterAdding(JsonObject inputData) {
        try {
            Amazon.cartAfterAdding(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLocation(JsonObject inputData) {
        try {
            Amazon.locationValidation(inputData.get("pincode").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationAustralia(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Australia, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationBrazil(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Brazil, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationCanada(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Canada, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationChina(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.china, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationFrance(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.France, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationGermany(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Germany, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationItaly(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Itlay, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL);
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationJapan(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Japan, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationMexico(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Mexico, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationNetherlands(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Netherlands, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationPoland(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Poland, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationSingapore(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Singapore, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationSpain(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Spain, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationTurkey(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Turkey, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationUAE(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.UAE, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationUK(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.UK, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationUS(JsonObject inputData) {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.US, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void customPriceFilter(JsonObject inputData) {
        try {
            Amazon.priceFilter(inputData.get("item").getAsString(), inputData.get("low").getAsString(), inputData.get("high").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void addRemoveCart(JsonObject inputData) {
        try {
            Amazon.addItemRemove(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateCartAfterNavigating(JsonObject inputData) {
        try {
            Amazon.cartValidateAfterNavigate(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validatePincodeAfterNavigate(JsonObject inputData) {
        try {
            Amazon.picodeValidation(inputData.get("pincode").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateCountCartItems(JsonObject inputData) {
        try {
            Amazon.validateCount(inputData.get("item").getAsString(), inputData.get("item2").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void viewPayOnDelivery(JsonObject inputData) {
        try {
            Amazon.payOndly(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void clickBackToTop(JsonObject inputData) {
        try {
            Amazon.backTop();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void newReleaseNo1(JsonObject inputData) {
        try {
            Amazon.newReleaseClick();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void bestSellerNo1(JsonObject inputData) {
        try {
            Amazon.bestSellerClick();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,takeSnapShotBase64());
        }
    }

}
