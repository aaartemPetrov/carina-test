package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FilterItem extends AbstractUIObject {

    @FindBy(css = "input")
    private ExtendedWebElement checkbox;
    @FindBy(css = "div span.cbx.x-refine__multi-select-cbx")
    private ExtendedWebElement label;

    public FilterItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getCheckbox() {
        return this.checkbox;
    }

    public void setCheckbox(ExtendedWebElement checkbox) {
        this.checkbox = checkbox;
    }

    public ExtendedWebElement getLabel() {
        return this.label;
    }

    public void setLabel(ExtendedWebElement label) {
        this.label = label;
    }

}
