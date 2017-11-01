package com.example.tests;

import com.example.pageobjects.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Simple Search")
public class SmokeSuite extends BaseSuite{

    @Story("Main application flow")
    @Test
    public void mainFlowTest(){
        String topResultTitle = new MainPage()
                .navigate()
                .waitUntilReady()
                .doSearchFor("fluent interface")
                .getTopResultTitle();

        Assert.assertTrue(
                topResultTitle.startsWith("Fluent interface "),
                "OMG! The top result doesnt start with 'Fluent interface' any more!");
    }

    @Story("Main application flow")
    @Test
    public void alwaysFailingTest(){
        Assert.fail();
    }

    @Story("Main application flow")
    @Test
    public void alwaysPassingTest(){
        Assert.assertFalse(false);
    }
}
