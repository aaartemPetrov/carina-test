package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.carinatest.AbstractFunctional;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FilterMenuItem extends AbstractUIObject {

    @FindBy(css = "span")
    private ExtendedWebElement labelLink;

    public FilterMenuItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getlabelLinkText() {
        return this.labelLink.getText();
    }

    public void clickFilterMenuItem() {
        AbstractFunctional.click(this.driver, this.labelLink);
    }

}
