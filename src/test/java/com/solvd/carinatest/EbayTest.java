package com.solvd.carinatest;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.solvd.carinatest.components.FilterFrame;
import com.solvd.carinatest.components.SearchTooltip;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EbayTest extends AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbayTest.class);

    @Test
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
    public void checkUnderPriceFilterTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.typeInSearchInput("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        searchedResultPage.clickUnderPriceLink();
        Assert.assertNotEquals(searchedResultPage.productsCount(), 0, "There are no searched products in list.");

        int underPrice = searchedResultPage.getUnderFilterPrice();
        SoftAssert softAssertPrice = new SoftAssert();
        searchedResultPage.getProductsPrices().forEach(price -> {
            price = StringUtils.replaceChars(price, ",", ".");
            int actualPrice = Integer.parseInt(StringUtils.substringBetween(price, "$", "."));
            softAssertPrice.assertTrue(actualPrice <= underPrice,
                    String.format("Price %d is bigger then %d", actualPrice, underPrice));
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void checkFromToPriceFilterTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.typeInSearchInput("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        searchedResultPage.clickFromToPriceLink();
        Assert.assertNotEquals(searchedResultPage.productsCount(), 0, "There are no searched products in list.");

        int fromPrice = searchedResultPage.getFromFilterPrice();
        int toPrice = searchedResultPage.getToFilterPrice();
        SoftAssert softAssertPrice = new SoftAssert();
        searchedResultPage.getProductsPrices().forEach(price -> {
            price = StringUtils.replaceChars(price, ",", ".");
            String[] prices = StringUtils.substringsBetween(price, "$", ".");

            if (prices.length == 1) {
                int actualPrice = Integer.parseInt(prices[0]);
                softAssertPrice.assertTrue(fromPrice <= actualPrice
                                && actualPrice <= toPrice,
                        String.format("Price %d is not in range from %d to %d", actualPrice,
                                fromPrice, toPrice));
            } else {
                int actualMinPrice = Integer.parseInt(prices[0]);
                int actualMaxPrice = Integer.parseInt(prices[1]);
                softAssertPrice.assertTrue(fromPrice <= actualMinPrice
                                && actualMaxPrice <= toPrice,
                        String.format("Price %d-%d is not in range from %d to %d", actualMinPrice, actualMaxPrice,
                                fromPrice, toPrice));
            }
        });
        softAssertPrice.assertAll();
    }

    @Test
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
