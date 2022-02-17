package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.carinatest.AbstractFunctional;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class PriceFilterBlock extends AbstractUIObject {

    @FindBy(css = "li:nth-child(1)")
    private ExtendedWebElement underPriceLink;
    private int underFilterPrice;
    @FindBy(css = "li:nth-child(2)")
    private ExtendedWebElement fromToPriceLink;
    private int fromFilterPrice;
    private int toFilterPrice;

    public PriceFilterBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
        this.setUnderPrice();
        this.setFromToPrices();
    }

    public int getUnderFilterPrice() {
        return this.underFilterPrice;
    }

    public int getFromFilterPrice() {
        return this.fromFilterPrice;
    }

    public int getToFilterPrice() {
        return this.toFilterPrice;
    }

    public String underPriceLinkText() {
       return this.fromToPriceLink.getText();
    }

    public String fromToPriceLinkText() {
        return this.fromToPriceLink.getText();
    }

    public void clickUnderPriceLink() {
        AbstractFunctional.click(this.driver, this.underPriceLink);
    }

    public void clickFromToPriceLink() {
        AbstractFunctional.click(this.driver, this.fromToPriceLink);
    }

    private void setUnderPrice() {
        String underFilterPriceString = this.underPriceLinkText();
        underFilterPriceString = StringUtils.replaceChars(underFilterPriceString, ",", ".");
        this.underFilterPrice =Integer.parseInt(StringUtils.substringBetween(underFilterPriceString, "$", "."));
    }

    private void setFromToPrices() {
        String fromToFilterPriceString = this.fromToPriceLinkText();
        fromToFilterPriceString = StringUtils.replaceChars(fromToFilterPriceString, ",", ".");
        String[] fromToPrices = StringUtils.substringsBetween(fromToFilterPriceString, "$", ".");
        this.fromFilterPrice = Integer.parseInt(fromToPrices[0]);
        this.toFilterPrice = Integer.parseInt(fromToPrices[1]);
    }

}
