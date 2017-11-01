package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected static WebDriver driver;
    protected static String baseURL;
    public static final long SHORT_WAIT = 1; // in seconds
    public static final long MEDIUM_WAIT = 5; // in seconds
    public static final long LONG_WAIT = 15; // in seconds

    abstract public <T extends BasePage> T navigate();
    abstract public <T extends BasePage> T waitUntilReady();

    public BasePage(){
        if(BasePage.driver == null) {
            throw new RuntimeException("WebDriver is null, page was not initialized! Please use setDriver(WebDriver driver) method");
        }
    }

    //init methods
    public static void setDriver(WebDriver driver){
        BasePage.driver = driver;
    }
    public static void setBaseURL(String baseURL){
        BasePage.baseURL = baseURL;
    }
    public static String getBaseURL(){ return baseURL; }

    // shared simple actions
    protected void sendKeysByCSS(String css, String message){
        driver.findElement(By.cssSelector(css))
                .sendKeys(message);
    }
    protected void clickByCSS(String css){
        driver.findElement(By.cssSelector(css))
                .click();
    }
    protected void waitForVisibilityByCSS(String css, Long timeout){
        ExpectedCondition<WebElement> condition =
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(css));
        new WebDriverWait(driver,timeout)
                .until(condition);
    }
    protected void waitForInvisibilityByCSS(String css, Long timeout){
        new WebDriverWait(driver,timeout)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(css)));
    }
    protected void waitToBeClickableByCSS(String css, Long timeout){
        new WebDriverWait(driver,timeout)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
    }
    protected boolean existsByCSS(String css){
        return !driver.findElements(By.cssSelector(css)).isEmpty();
    }
}
