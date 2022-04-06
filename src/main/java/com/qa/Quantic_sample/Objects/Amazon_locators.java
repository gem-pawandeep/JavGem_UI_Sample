package com.qa.Quantic_sample.Objects;

import org.openqa.selenium.By;

public class Amazon_locators {
    public static By sign_in_button = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
    public static By user_name_box = By.xpath("//input[@type='email']");
    public static By Continue_button = By.xpath("//input[@type='submit']");
    public static By user_password_box = By.xpath("//input[@type='password']");
    public static By cart_icon = By.xpath("//span[@class='nav-cart-icon nav-sprite']");
    public static By check_cart_empty = By.xpath("//*[@id='sc-active-cart']//h2");
    public static By search_Box = By.xpath("//input[@id='twotabsearchtextbox']");
    public static By search_Button = By.xpath("//*[@class='nav-search-submit nav-sprite']");
    public static By first_result = By.xpath("//*[@data-image-index='1']");
    public static By second_result = By.xpath("//*[@data-image-index='2']");
    public static By price = By.xpath("//span[@class='a-price-whole']");
    public static By price1 = By.xpath("//*[@class='a-price a-text-price a-size-medium']");
    public static By tittle = By.xpath("//span[@id='productTitle']");
    public static By error_msg = By.xpath("//span[@class='a-list-item']");
    public static By pricedrpdwn = By.xpath("//span[@class='a-button a-button-dropdown a-button-small']");
    public static By low_high = By.xpath("//*[@id='s-result-sort-select_1']");
    public static By high_low = By.xpath("//*[@id='s-result-sort-select_2']");
    public static By fb_link = By.xpath("//*[@id='navFooter']//div[3]//li[1]//a");
    public static By insta_link = By.xpath("//*[@id='navFooter']//div[3]//li[3]//a");
    public static By twitter_link = By.xpath("//*[@id='navFooter']//div[3]//li[2]//a");
    public static By lang_button = By.xpath("//span[@class='icp-nav-link-inner']");
    public static By English = By.xpath("//*[@id='icp-language-settings']/div[2]");
    public static By hindi = By.xpath("//*[@id='icp-language-settings']/div[3]");
    public static By tamil = By.xpath("//*[@id='icp-language-settings']/div[4]");
    public static By telgu = By.xpath("//*[@id='icp-language-settings']/div[5]");
    public static By Kannda = By.xpath("//*[@id='icp-language-settings']/div[6]");
    public static By malyalam = By.xpath("//*[@id='icp-language-settings']/div[7]");
    public static By bangla = By.xpath("//*[@id='icp-language-settings']/div[8]");
    public static By marathi = By.xpath("//*[@id='icp-language-settings']/div[9]");
    public static By lang_submit = By.xpath("//input[@aria-labelledby='icp-save-button-announce']");
}
