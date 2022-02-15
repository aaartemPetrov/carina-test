package com.solvd.carinatest;

import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.solvd.carinatest.components.FilterFrame;
import com.solvd.carinatest.components.Product;
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
        AbstractPage.sendKeys(getDriver(), homePage.getSearchInput(), "a");

        SearchTooltip searchTooltip = new SearchTooltip(getDriver());
        Assert.assertFalse(searchTooltip.getTooltips().isEmpty(), "Tooltip popup is empty");
    }

    @DataProvider(name = "brandNamesForSearch")
    public Object[][] names() {
        return new Object[][]{{"Sony"}, {"Samsung"}, {"Apple"}, {"Xiaomi"}, {"Huawei"}};
    }

    @Test(dataProvider = "brandNamesForSearch")
    public void checkSearchTest(String brandName) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        AbstractPage.sendKeys(getDriver(), homePage.getSearchInput(), brandName);
        AbstractPage.click(getDriver(), homePage.getSearchButton());

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        List<Product> searchedProducts = searchedResultPage.getProductBlock().getProducts();
        Assert.assertFalse(searchedProducts.isEmpty(), "There are no searched products in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedProducts.forEach(searchedProduct -> {
            softAssert.assertTrue(searchedProduct.getSearchedItemTitle().getText().toLowerCase(Locale.ROOT).contains(brandName.toLowerCase(Locale.ROOT)),
                    String.format("Product title does not contain brand name \"%s\"", brandName));
            LOGGER.error(searchedProduct.getSearchedItemTitle().getText());
        });
        softAssert.assertAll();
    }

    @Test
    public void checkUnderPriceFilterTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        AbstractPage.sendKeys(getDriver(), homePage.getSearchInput(), "samsung");
        AbstractPage.click(getDriver(), homePage.getSearchButton());

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        String priceString = searchedResultPage.getProductBlock().getPriceFilterBlock().getUnderPriceLink().getText();
        AbstractPage.click(getDriver(), searchedResultPage.getProductBlock().getPriceFilterBlock().getUnderPriceLink());

        List<Product> searchedItems = searchedResultPage.getProductBlock().getProducts();
        Assert.assertFalse(searchedItems.isEmpty(), "There are no searched items in list.");

        priceString = StringUtils.replaceChars(priceString, ",", ".");
        int underPrice = Integer.parseInt(StringUtils.substringBetween(priceString, "$", "."));

        SoftAssert softAssertPrice = new SoftAssert();
        searchedItems.forEach(searchedItem -> {
            String searchedItemPriceString = searchedItem.getSearchedItemPrice().getText();
            searchedItemPriceString = StringUtils.replaceChars(searchedItemPriceString, ",", ".");
            int actualPrice = Integer.parseInt(StringUtils.substringBetween(searchedItemPriceString, "$", "."));
            softAssertPrice.assertTrue(actualPrice <= underPrice,
                    String.format("Price %d is bigger then %d", actualPrice, underPrice));
        });
        softAssertPrice.assertAll();
    }

    @Test
    public void checkFromToPriceFilterTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        AbstractPage.sendKeys(getDriver(), homePage.getSearchInput(), "samsung");
        AbstractPage.click(getDriver(), homePage.getSearchButton());

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        String priceString = searchedResultPage.getProductBlock().getPriceFilterBlock().getFromToPriceLink().getText();
        AbstractPage.click(getDriver(), searchedResultPage.getProductBlock().getPriceFilterBlock().getFromToPriceLink());

        List<Product> searchedItems = searchedResultPage.getProductBlock().getProducts();
        Assert.assertFalse(searchedItems.isEmpty(), "There are no searched items in list.");

        priceString = StringUtils.replaceChars(priceString, ",", ".");
        String[] fromToPrices = StringUtils.substringsBetween(priceString, "$", ".");
        int fromPrice = Integer.parseInt(fromToPrices[0]);
        int toPrice = Integer.parseInt(fromToPrices[1]);

        SoftAssert softAssertPrice = new SoftAssert();
        searchedItems.forEach(searchedItem -> {
            String searchedItemPriceString = searchedItem.getSearchedItemPrice().getText();
            searchedItemPriceString = StringUtils.replaceChars(searchedItemPriceString, ",", ".");
            String[] prices = StringUtils.substringsBetween(searchedItemPriceString, "$", ".");

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
        AbstractPage.sendKeys(getDriver(), homePage.getSearchInput(), "samsung");
        AbstractPage.click(getDriver(), homePage.getSearchButton());

        SearchedResultPage searchedResultPage = new SearchedResultPage(getDriver());
        AbstractPage.click(getDriver(), searchedResultPage.getMoreFiltersButton());

        FilterFrame filterFrame = new FilterFrame(getDriver());
        filterFrame.getFilterMenu().chooseAndClick("Storage Capacity");
        filterFrame.getFilterBlock().chooseAndClickCheckbox("128 GB");
        AbstractPage.click(getDriver(), filterFrame.getApplyButton());

        SoftAssert softAssert = new SoftAssert();
        searchedResultPage.getProductBlock().getProducts().forEach(product -> {
            String oldTab = getDriver().getWindowHandle();
            AbstractPage.click(getDriver(), product.getSearchedItemLink());
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
