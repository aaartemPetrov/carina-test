package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.carinatest.AbstractFunctional;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class Product extends AbstractUIObject {

    @FindBy(css = "h3.s-item__title")
    private ExtendedWebElement productTitle;
    @FindBy(css = "div.s-item__detail:first-of-type span.s-item__price")
    private ExtendedWebElement productPrice;
    @FindBy(css = "a.s-item__link")
    private ExtendedWebElement productLink;

    public Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getProductName() {
        return this.productTitle.getText();
    }

    public String getProductPrice() {
        return this.productPrice.getText();
    }

    public void clickOnProduct() {
        AbstractFunctional.click(this.driver, this.productLink);
    }

}
