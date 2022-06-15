package com.qa.Quantic_sample.Tests;

import com.gemini.dataProvider.QuanticDataProvider;
import com.gemini.generic.DriverAction;
import com.gemini.generic.DriverManager;
import com.gemini.generic.QuanticUIBase;
import com.gemini.quartzReporting.GemTestReporter;
import com.gemini.quartzReporting.STATUS;
import com.google.gson.JsonObject;
import com.qa.Quantic_sample.Objects.Amazon_locators;
import com.qa.Quantic_sample.Pages.Amazon;
import com.qa.Quantic_sample.Utility.Common_functions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class amazonTestCases extends QuanticUIBase {
    /*
     @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
        public void Test1(JsonObject inputData) {

        }
        */

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void seeMoreFunctionality(JsonObject inputData) {
        GemTestReporter.addTestStep("There had to be a better way. That's all Nancy could think as she sat at her desk staring at her computer screen. She'd already spent five years of her life in this little cubicle staring at her computer doing \"work\" that didn't seem to matter to anyone including her own boss. There had to be more to her life than this and there had to be a better way to make a living. That's what she was thinking when the earthquake struck","There had to be a better way. That's all Nancy could think as she sat at her desk staring at her computer screen. She'd already spent five years of her life in this little cubicle staring at her computer doing \"work\" that didn't seem to matter to anyone including her own boss. There had to be more to her life than this and there had to be a better way to make a living. That's what she was thinking when the earthquake struck.\n" +
                "There were about twenty people on the dam. Most of them were simply walking and getting exercise. There were a few who were fishing. There was a family who had laid down a blanket and they were having a picnic. It was like this most days and nothing seemed out of the ordinary. The problem was that nobody noticed the water leaking through the dam wall.\n" +
                "There was a reason for her shyness. Everyone assumed it had always been there but she knew better. She knew the exact moment that the shyness began. It had been that fateful moment at the lake. There are just some events that do that to you.\n" +
                "They needed to find a place to eat. The kids were beginning to get grumpy in the back seat and if they didn't find them food soon, it was just a matter of time before they were faced with a complete meltdown. Even knowing this, the solution wasn't easy. Everyone in the car had a different opinion on where the best place to eat would be with nobody agreeing with the suggestions of the others. It seemed to be an impossible no-win situation where not everyone would be happy no matter where they decided to eat which in itself would lead to a meltdown. Yet a decision needed to be made and it needed to be made quickly.\n" +
                "It all started with the computer. Had he known what was to follow, he would have never logged on that day. But the truth was there was no way to know what was about to happen. So Dave pressed the start button, the computer booted up, the screen came alive, and everything Dave knew to be true no longer was.\n" +
                "Brenda never wanted to be famous. While most of her friends dreamed about being famous, she could see the negative aspects that those who wanted to be famous seemed to ignore. The fact that you could never do anything in public without being mobbed and the complete lack of privacy was something that she never wanted to experience. She also had no desire to have strangers speculating about every aspect of her life and what each thing she did was supposed to mean. Brenda was perfectly happy with her anonymous life where she could do exactly as she wanted without anyone else giving a damn. Thus, her overnight Internet celebrity was not something she was thrilled about as her friends told her how lucky she was.\n" +
                "MaryLou wore the tiara with pride. There was something that made doing anything she didn't really want to do a bit easier when she wore it. She really didn't care what those staring through the window were thinking as she vacuumed her apartment.\n" +
                "He looked at the sand. Picking up a handful, he wondered how many grains were in his hand. Hundreds of thousands? \"Not enough,\" the said under his breath. I need more.\n" +
                "He wandered down the stairs and into the basement. The damp, musty smell of unuse hung in the air. A single, small window let in a glimmer of light, but this simply made the shadows in the basement deeper. He inhaled deeply and looked around at a mess that had been accumulating for over 25 years. He was positive that this was the place he wanted to live.\n" +
                "He heard the loud impact before he ever saw the result. It had been so loud that it had actually made him jump back in his seat. As soon as he recovered from the surprise, he saw the crack in the windshield. It seemed to be an analogy of the current condition of his life.",STATUS.PASS);
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void launchedAmazon(JsonObject inputData) throws IOException {
        try {
            Amazon.ValidatingUrl();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL, DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void loginCorrectCredentials(JsonObject inputData) throws IOException {
        try {
            Amazon.SignIn(inputData.get("email").getAsString(), inputData.get("pass").getAsString());
            DriverAction.waitSec(5);
            String url1 = DriverAction.getCurrentURL();
            String title = DriverAction.getTitle(url1);
            if (title.contains("Amazon.in")) {
                GemTestReporter.addTestStep("Validate Login", "Login_successful", STATUS.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validate Login", "Login_Unsuccessful", STATUS.FAIL,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void loginIncorrectCredentials(JsonObject inputData) throws IOException {
        try {
            Amazon.SignIn(inputData.get("email").getAsString(), inputData.get("pass").getAsString());
            GemTestReporter.addTestStep("Login error message", DriverAction.getElementText(Amazon_locators.error_msg), STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateEmptyCartByDefault(JsonObject inputData) throws IOException {
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.click(Amazon_locators.cart_icon, "Cart");
            String s = DriverAction.getElementText(Amazon_locators.check_cart_empty);
            if (s.contains("is empty")) {
                GemTestReporter.addTestStep("Validating Cart", "Cart is Empty By Default", STATUS.PASS,DriverAction.takeSnapShot());
            } else {
                GemTestReporter.addTestStep("Validating Cart", "Cart is Not Empty By Default", STATUS.PASS,DriverAction.takeSnapShot());
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getPriceOfFirstResult(JsonObject inputData) throws IOException {
        try {
            Amazon.firstResultPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateSortByLowToHigh(JsonObject inputData) throws IOException {
        try {
            Amazon.lowToHigh(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateSortByHighToLow(JsonObject inputData) throws IOException {
        try {
            Amazon.highToLow(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateInstagramHyperlink(JsonObject inputData) throws IOException {
        try {
            Amazon.validateHyperlink(Amazon_locators.insta_link, "instagram");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
/*
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateFacebookHyperlink(JsonObject inputData) throws IOException {
        try {
            Amazon.validateHyperlink(Amazon_locators.fb_link, "facebook");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateTwitterHyperlink(JsonObject inputData) throws IOException {
        try {
            Amazon.validateHyperlink(Amazon_locators.twitter_link, "twitter");
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
*/
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void maxPriceSearchItem(JsonObject inputData) throws IOException {
        try {
            Amazon.maxPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void minPriceSearchItem(JsonObject inputData) throws IOException {
        try {
            Amazon.minPrice(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getDiffMaxMinItem(JsonObject inputData) throws IOException {
        try {
            Amazon.diffMaxMin(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeHindi(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Hindi").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

  /*  @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeTamil(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Tamil").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeTelgu(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Telgu").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeKannada(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Kannada").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeMalyalam(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Malyalam").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeBangla(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Bangla").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeMarathi(JsonObject inputData) throws IOException {
        try {
            Amazon.validateLanguage(inputData.get("Marathi").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLangChangeEnglish(JsonObject inputData) throws IOException {
        try {
            String url = "https://www.amazon.in/?language=hi_IN";
            DriverAction.navigateToUrl(url);
            if (url.contains("hi")) {
                GemTestReporter.addTestStep("Validation", "Current page in Hindi Language<br>", STATUS.PASS);
            }
            Amazon.validateLanguage(inputData.get("English").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
*/
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void alexaDotFromLeftTreeMenu(JsonObject inputData) throws IOException {
        try {
            Amazon.alexaDot();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateCartAfterAdding(JsonObject inputData) throws IOException {
        try {
            Amazon.cartAfterAdding(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateLocation(JsonObject inputData) throws IOException {
        try {
            Amazon.locationValidation(inputData.get("pincode").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationAustralia(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Australia, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

  /*  @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationBrazil(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Brazil, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationCanada(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Canada, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationChina(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.china, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationFrance(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.France, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationGermany(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Germany, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
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
    public void validationJapan(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Japan, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationMexico(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Mexico, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationNetherlands(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Netherlands, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationPoland(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Poland, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationSingapore(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Singapore, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationSpain(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Spain, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationTurkey(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.Turkey, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationUAE(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.UAE, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationUK(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.UK, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validationUS(JsonObject inputData) throws IOException {
        try {
            Amazon.countryValidation(inputData.get("Country").getAsString(), Amazon_locators.US, inputData.get("Name").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
*/
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void customPriceFilter(JsonObject inputData) throws IOException {
        try {
            Amazon.priceFilter(inputData.get("item").getAsString(), inputData.get("low").getAsString(), inputData.get("high").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void addRemoveCart(JsonObject inputData) throws IOException {
        try {
            Amazon.addItemRemove(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateCartAfterNavigating(JsonObject inputData) throws IOException {
        try {
            Amazon.cartValidateAfterNavigate(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validatePincodeAfterNavigate(JsonObject inputData) throws IOException {
        try {
            Amazon.picodeValidation(inputData.get("pincode").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void validateCountCartItems(JsonObject inputData) throws IOException {
        try {
            Amazon.validateCount(inputData.get("item").getAsString(), inputData.get("item2").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void addTwoSameItems(JsonObject inputData) throws IOException {
        try {
            Amazon.totalCountSameItem(inputData.get("item").getAsString());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
/*
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void clickBackToTop(JsonObject inputData) throws IOException {
        try {
            Amazon.backTop();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
*/
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void newReleaseNo1(JsonObject inputData) throws IOException {
        try {
            Amazon.newReleaseClick();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void bestSellerNo1(JsonObject inputData) throws IOException {
        try {
            Amazon.bestSellerClick();
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getElementsFunctionality(JsonObject inputData) throws IOException {
        try {
            Common_functions.search(inputData.get("item").getAsString());
            DriverAction.click(Amazon_locators.pricedrpdwn,"dropdown");
            List<WebElement> elements = DriverAction.getElements(Amazon_locators.drpdwn);
            GemTestReporter.addTestStep("Number of elements:",""+elements.size(),STATUS.PASS);
            for (int i=0; i<elements.size();i++){
                GemTestReporter.addTestStep("dropdown buttons text:",DriverAction.getAttributeName(elements.get(i),"aria-labelledby"),STATUS.PASS);
            }
            GemTestReporter.addTestStep("Current Window Handle:",DriverAction.getWindowHandle(),STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getElementsTextFunctionality(JsonObject inputData) throws IOException {
        try {
            Common_functions.search(inputData.get("item").getAsString());
            DriverAction.click(Amazon_locators.pricedrpdwn,"dropdown");
            List<String>elements=DriverAction.getElementsText(Amazon_locators.drpdwn);
            for (int i=0;i<elements.size();i++){
                GemTestReporter.addTestStep("Dropdowns:",elements.get(i),STATUS.PASS);
            }
            GemTestReporter.addTestStep("Current Window Handle:",DriverAction.getWindowHandle(),STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void amazonPageSource(JsonObject inputData) throws IOException {
        try {
            //GemTestReporter.addTestStep("Page Source",DriverAction.getPageSource(),STATUS.PASS);
            String g=DriverAction.getPageSource();
            System.out.println(DriverAction.getPageSource());
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void getCSSValueFunctionality(JsonObject inputData) throws IOException {
        try {
            DriverAction.setImplicitTimeOut(5);
            String color= DriverAction.getCSSValue(Amazon_locators.icon,"background-color");
            GemTestReporter.addTestStep("Color is :",color,STATUS.PASS);
            String font= DriverAction.getCSSValue(Amazon_locators.icon,"font-size");
            GemTestReporter.addTestStep("Font is : ",font,STATUS.PASS);
          //  GemTestReporter.addTestStep("ROLE ",DriverAction.getArialRole(Amazon_locators.icon),STATUS.PASS);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void iframeFunctionalities(JsonObject inputData) throws IOException {
        try {
            DriverAction.setImplicitTimeOut(50);
            DriverAction.setPageLoadTimeOut(50);
            DriverAction.setScriptTimeOut(50);
            DriverAction.waitSec(2);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.waitSec(2);
            int size = DriverAction.getElements(By.tagName("iframe")).size();
            GemTestReporter.addTestStep("total iframe",""+size,STATUS.PASS);
            DriverAction.switchToFrame(1);
            for(int i=0; i<size; i++){
                DriverAction.switchToFrame(i);
                GemTestReporter.addTestStep("iframe",""+(i+1),STATUS.PASS);
                DriverAction.switchToDefaultContent();
            }
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void alertFunctionality(JsonObject inputData) throws IOException {
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.navigateToUrl(inputData.get("url").getAsString());

            DriverAction.click(Amazon_locators.alert);
            DriverAction.waitSec(2);
            String s=DriverManager.getWebDriver().switchTo().alert().getText();
            DriverAction.switchToAlert(false);
            DriverAction.waitSec(3);
            GemTestReporter.addTestStep("Alert Text",""+s,STATUS.PASS);
            DriverAction.AcceptAlert(true);

            DriverAction.click(Amazon_locators.alert);
            DriverAction.waitSec(2);
            DriverAction.AcceptAlert(true);

            DriverAction.click(Amazon_locators.confirm);
            DriverAction.waitSec(2);
            DriverAction.DismissAlert(true);

            DriverAction.click(Amazon_locators.confirm);
            DriverAction.waitSec(2);
            DriverAction.AcceptAlert(true);

            DriverAction.click(Amazon_locators.prompt);
            DriverAction.waitSec(2);
          //  DriverAction.AlertInput(inputData.get("name").getAsString());
            DriverManager.getWebDriver().switchTo().alert().sendKeys(inputData.get("name").toString());
            GemTestReporter.addTestStep("SendKeys To Alert", "SendKeys To Alert Successful <br> input ~ "+inputData.get("name").getAsString(), STATUS.PASS);
            DriverAction.waitSec(3);
            DriverAction.AcceptAlert(true);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void switchToActiveElement(JsonObject inputData) throws IOException {
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.setScriptTimeOut(5);
            DriverAction.setPageLoadTimeOut(10);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.waitSec(2);
            DriverAction.typeText(Amazon_locators.usrNme,inputData.get("username").getAsString()+(Keys.TAB),"username");;
            DriverAction.waitSec(2);
          //  DriverAction.typeText(DriverAction.switchToActiveElement(),inputData.get("password").getAsString()+(Keys.TAB),"password");
            DriverAction.waitSec(2);
            //DriverAction.click(DriverAction.switchToActiveElement(),"sign in");
            DriverAction.waitSec(2);
        } catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void doubleClickFunctionality(JsonObject inputData){
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.setScriptTimeOut(5);
            DriverAction.setPageLoadTimeOut(10);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.waitSec(2);
            DriverAction.doubleClick(Amazon_locators.dclick,"Double Click");
        }catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }
    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void rightClickFunctionality(JsonObject inputData){
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.setScriptTimeOut(5);
            DriverAction.setPageLoadTimeOut(10);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.waitSec(2);
            DriverAction.rightClick(Amazon_locators.rclick,"Right Click");
        }catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void dragAndDropFunctionality(JsonObject inputData){
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.setScriptTimeOut(5);
            DriverAction.setPageLoadTimeOut(10);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.waitSec(2);
            DriverAction.dragAndDrop(Amazon_locators.from,Amazon_locators.to,true);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void dropDownFunctionality(JsonObject inputData){
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.setScriptTimeOut(5);
            DriverAction.setPageLoadTimeOut(10);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.dropDown(Amazon_locators.dropdown,inputData.get("name").getAsString());
        }catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

    @Test(dataProvider = "QuanticDataProvider", dataProviderClass = QuanticDataProvider.class)
    public void fileUploadFunctionality(JsonObject inputData){
        try {
            DriverAction.setImplicitTimeOut(5);
            DriverAction.setScriptTimeOut(5);
            DriverAction.setPageLoadTimeOut(10);
            DriverAction.navigateToUrl(inputData.get("url").getAsString(),true);
            DriverAction.fileUpload(Amazon_locators.fileupload,inputData.get("path").getAsString());
            DriverAction.waitSec(5);
        }catch (Exception e) {
            GemTestReporter.addTestStep("Some Error Occurred", e.toString(), STATUS.FAIL,DriverAction.takeSnapShot());
        }
    }

}
