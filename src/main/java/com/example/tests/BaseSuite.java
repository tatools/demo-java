package com.example.tests;

import com.example.pageobjects.BasePage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.IOException;

public abstract class BaseSuite implements IHookable{
    private WebDriver driver;
    @BeforeMethod
    @Parameters({ "browser", "baseURL" })
    public void setup(String browser, String baseURL){
        initWebDriver(browser);
        BasePage.setDriver(driver);
        BasePage.setBaseURL(baseURL);
    }

    @AfterMethod
    public void cleanup(){
        driver.quit();
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initWebDriver(String browser){
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
            case "ff":
                driver = new FirefoxDriver();
                break;
            case "ie":
            case "internetexplorer":
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
    }

    @Attachment(value = "Failure in method {methodName}", type = "image/png")
    private byte[] takeScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
