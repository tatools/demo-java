package com.example.pageobjects;

import io.qameta.allure.Step;

public class MainPage extends BasePage{
    private static String url = baseURL;
    private static String searchButtonCSS = "input.lsb" ;
    private static String searchInputCSS = "input[name='q']";

    @Override
    public MainPage navigate(){
        driver.get(url);
        return this;
    }

    @Override
    public MainPage waitUntilReady(){
        waitToBeClickableByCSS(searchInputCSS, MEDIUM_WAIT);
        return this;
    }

    @Step("Do search for '{whatFor}' phrase.")
    public ResultsPage doSearchFor(String whatFor){
        sendKeysByCSS(searchInputCSS, whatFor);
        waitToBeClickableByCSS(searchButtonCSS, MEDIUM_WAIT);
        clickByCSS(searchButtonCSS);
        return new ResultsPage().waitUntilReady();
    }
}
