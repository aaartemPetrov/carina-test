package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class Tooltip extends AbstractUIObject {

    private ExtendedWebElement tooltipLink;

    public Tooltip(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getTooltipLink() {
        return this.tooltipLink;
    }

    public void setTooltipLink(ExtendedWebElement tooltipLink) {
        this.tooltipLink = tooltipLink;
    }

}
