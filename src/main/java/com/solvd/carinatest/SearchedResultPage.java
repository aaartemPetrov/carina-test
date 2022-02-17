package com.solvd.carinatest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.carinatest.components.ProductBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchedResultPage extends AbstractPage {

    @FindBy(css = "#srp-river-results")
    private ProductBlock productBlock;
    @FindBy(css = "li.x-refine__main__list--more button")
    private ExtendedWebElement moreFiltersButton;

    public SearchedResultPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProductByName(String productName) {
        this.productBlock.clickOnProductByName(productName);
    }

    public int productsCount() {
        return this.productBlock.productsCount();
    }

    public List<Integer> getMinPrices() {
        return this.productBlock.getMinPrices();
    }

    public List<Integer> getMaxPrices() {
        return this.productBlock.getMaxPrices();
    }

    public List<String> getProductsNames() {
        return this.productBlock.getProductsNames();
    }

    public void clickMoreFilterButton() {
        this.moreFiltersButton.click();
    }

    public String underPriceLinkText() {
        return this.productBlock.underPriceLinkText();
    }

    public String fromToPriceLinkText() {
        return this.productBlock.fromToPriceLinkText();
    }

    public void clickUnderPriceLink() {
        this.productBlock.clickUnderPriceLink();
    }

    public void clickFromToPriceLink() {
        this.productBlock.clickFromToPriceLink();
    }

    public int getUnderFilterPrice() {
        return this.productBlock.getUnderFilterPrice();
    }

    public int getFromFilterPrice() {
        return this.productBlock.getFromFilterPrice();
    }

    public int getToFilterPrice() {
        return this.productBlock.getToFilterPrice();
    }

}
