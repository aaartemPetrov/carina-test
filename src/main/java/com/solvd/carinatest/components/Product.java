package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class Product extends AbstractUIObject {

    @FindBy(css = "h3.s-item__title")
    private ExtendedWebElement searchedItemTitle;
    @FindBy(css = "div.s-item__detail:first-of-type span.s-item__price")
    private ExtendedWebElement searchedItemPrice;
    @FindBy(css = "a.s-item__link")
    private ExtendedWebElement searchedItemLink;

    public Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getSearchedItemTitle() {
        return this.searchedItemTitle;
    }

    public void setSearchedItemTitle(ExtendedWebElement searchedItemTitle) {
        this.searchedItemTitle = searchedItemTitle;
    }

    public ExtendedWebElement getSearchedItemPrice() {
        return this.searchedItemPrice;
    }

    public void setSearchedItemPrice(ExtendedWebElement searchedItemPrice) {
        this.searchedItemPrice = searchedItemPrice;
    }

    public ExtendedWebElement getSearchedItemLink() {
        return this.searchedItemLink;
    }

    public void setSearchedItemLink(ExtendedWebElement searchedItemLink) {
        this.searchedItemLink = searchedItemLink;
    }

}
