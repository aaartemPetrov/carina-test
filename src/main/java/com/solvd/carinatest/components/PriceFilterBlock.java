package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PriceFilterBlock extends AbstractUIObject {

    @FindBy(css = "li:nth-child(1)")
    private ExtendedWebElement underPriceLink;
    @FindBy(css = "li:nth-child(2)")
    private ExtendedWebElement fromToPriceLink;
    @FindBy(css = "li:nth-child(3)")
    private ExtendedWebElement overPriceLink;

    public PriceFilterBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getUnderPriceLink() {
        return underPriceLink;
    }

    public void setUnderPriceLink(ExtendedWebElement underPriceLink) {
        this.underPriceLink = underPriceLink;
    }

    public ExtendedWebElement getFromToPriceLink() {
        return fromToPriceLink;
    }

    public void setFromToPriceLink(ExtendedWebElement fromToPriceLink) {
        this.fromToPriceLink = fromToPriceLink;
    }

    public ExtendedWebElement getOverPriceLink() {
        return overPriceLink;
    }

    public void setOverPriceLink(ExtendedWebElement overPriceLink) {
        this.overPriceLink = overPriceLink;
    }

}
