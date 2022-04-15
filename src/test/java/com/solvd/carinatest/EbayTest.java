package com.solvd.carinatest;

import com.google.common.util.concurrent.Uninterruptibles;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.solvd.carinatest.components.FilterFrame;
import com.solvd.carinatest.components.SearchTooltip;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EbayTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbayTest.class);

    @Test
    public void testTest() throws MalformedURLException {
        DesiredCapabilities ds = new DesiredCapabilities();
        ds.setBrowserName("firefox");
        ds.setCapability("version", "98.0");
        ds.setCapability("enableVNC", true);
        ds.setCapability("enableVideo", true);
        ds.setCapability("enableLog", true);
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://login:password@host.docker.internal:4444/wd/hub"), ds);
        driver.get("https://www.google.com/");
        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys("Hello, Google, i know for sure that you have consciousness :)");
        driver.findElement(By.cssSelector("input[name=\"q\"]")).sendKeys(Keys.ENTER);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    public void checkSearchTooltipTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.clickSearchButton();

        SearchTooltip searchTooltip = new SearchTooltip(getDriver());
        Assert.assertNotEquals(searchTooltip.tooltipsCount(), 0,"Tooltip popup is empty.");
    }

    @DataProvider(name = "brandNamesForSearch")
    public Object[][] names() {
        return new Object[][]{{"Sony"}, {"Samsung"}, {"Apple"}, {"Xiaomi"}, {"Huawei"}};
    }

    @Test(dataProvider = "brandNamesForSearch")
    @MethodOwner(owner = "qpsdemo")
    public void checkSearchTest(String brandName) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.typeInSearchInput(brandName);
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        Assert.assertNotEquals(searchedResultPage.productsCount(), 0, "There are no searched products in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedResultPage.getProductsNames().forEach(productName -> {
            softAssert.assertTrue(productName.toLowerCase(Locale.ROOT).contains(brandName.toLowerCase(Locale.ROOT)),
                    String.format("Product title does not contain brand name \"%s\"", brandName));
        });
        softAssert.assertAll();
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    public void checkUnderPriceFilterTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.typeInSearchInput("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        searchedResultPage.clickUnderPriceLink();
        Assert.assertNotEquals(searchedResultPage.productsCount(), 0, "There are no searched products in list.");

        SoftAssert softAssertPrice = new SoftAssert();
        searchedResultPage.getMinPrices().forEach(actualItemPrice -> {
            softAssertPrice.assertTrue(actualItemPrice <= searchedResultPage.getUnderFilterPrice(), String.format("Price %d is bigger then %d.", actualItemPrice, searchedResultPage.getUnderFilterPrice()));
        });
        softAssertPrice.assertAll();
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    public void checkFromToPriceFilterTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.typeInSearchInput("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        searchedResultPage.clickFromToPriceLink();
        Assert.assertNotEquals(searchedResultPage.productsCount(), 0, "There are no searched products in list.");

        SoftAssert softAssertMinPrice = new SoftAssert();
        searchedResultPage.getMinPrices().forEach(actualItemPrice -> {
            softAssertMinPrice.assertTrue(searchedResultPage.getFromFilterPrice() <= actualItemPrice,
                    String.format("Price %d is lower then %d.", actualItemPrice, searchedResultPage.getFromFilterPrice()));
        });
        softAssertMinPrice.assertAll();

        SoftAssert softAssertMaxPrice = new SoftAssert();
        searchedResultPage.getMaxPrices().forEach(actualItemPrice -> {
            softAssertMaxPrice.assertTrue(actualItemPrice <= searchedResultPage.getToFilterPrice(),
                    String.format("Price %d is bigger then %d.", actualItemPrice, searchedResultPage.getToFilterPrice()));
        });
        softAssertMaxPrice.assertAll();
    }

    @Test
    @MethodOwner(owner = "qpsdemo")
    public void checkStorageCapacityFilter() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.typeInSearchInput("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        searchedResultPage.clickMoreFilterButton();

        FilterFrame filterFrame = new FilterFrame(getDriver());
        filterFrame.clickOnMenuItem("Storage Capacity");
        filterFrame.clickOnCheckbox("128 GB");
        filterFrame.clickApplyButton();

        SoftAssert softAssert = new SoftAssert();
        searchedResultPage.getProductsNames().forEach(productName -> {
            String oldTab = getDriver().getWindowHandle();
            searchedResultPage.clickOnProductByName(productName);
            List<String> newTab = new ArrayList<>(getDriver().getWindowHandles());
            newTab.remove(oldTab);
            getDriver().switchTo().window(newTab.get(0));

            String itemTitle = getDriver().findElement(By.cssSelector("#itemTitle span")).getText().toLowerCase(Locale.ROOT);
            softAssert.assertTrue(itemTitle.contains("128gb"), "Item title don't contains \"128GB\" text.");

            getDriver().close();
            getDriver().switchTo().window(oldTab);
        });
        softAssert.assertAll();
    }

}
