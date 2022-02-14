package com.solvd.carinatest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(AbstractPage.class);
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void click(WebDriver driver, ExtendedWebElement element) {
        String elementName = element.getName();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element.getElement())).click();
        LOGGER.info("\"" + elementName + "\"" + " was clicked.");
    }

    public static void sendKeys(WebDriver driver, ExtendedWebElement element, String string) {
        String elementName = element.getName();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element.getElement())).sendKeys(string);
        LOGGER.info("\"" + string + "\"" + " was wrote in to a " + "\"" + elementName + "\".");
    }

}
