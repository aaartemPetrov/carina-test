package com.solvd.carinatest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.carinatest.components.ProductBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchedResultPage extends AbstractPage {

    @FindBy(css = "#srp-river-results")
    private ProductBlock productBlock;
    @FindBy(css = "li.x-refine__main__list--more button")
    private ExtendedWebElement moreFiltersButton;

    public SearchedResultPage(WebDriver driver) {
        super(driver);
    }

    public ProductBlock getProductBlock() {
        return this.productBlock;
    }

    public void setProductBlock(ProductBlock productBlock) {
        this.productBlock = productBlock;
    }

    public ExtendedWebElement getMoreFiltersButton() {
        return this.moreFiltersButton;
    }

    public void setMoreFiltersButton(ExtendedWebElement moreFiltersButton) {
        this.moreFiltersButton = moreFiltersButton;
    }

}
