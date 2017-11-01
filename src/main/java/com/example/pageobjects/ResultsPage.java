package com.example.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ResultsPage extends BasePage{
    private static String url = ""; //TODO is there any url?
    private static String firstResultLinkCSS = "div.srg div.g h3.r:first-child a";

    @Override
    public ResultsPage navigate(){
        driver.get(url);
        return this;
    }

    @Override
    public ResultsPage waitUntilReady(){
        waitToBeClickableByCSS(firstResultLinkCSS, MEDIUM_WAIT);
        return this;
    }

    @Step("Get title of the top result on the page")
    public String getTopResultTitle(){
        return driver
                .findElement(By.cssSelector(firstResultLinkCSS))
                .getText();
    }
}
