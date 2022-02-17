package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.carinatest.AbstractFunctional;
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

    public String labelText() {
        return this.label.getText();
    }

    public void clickCheckbox() {
        AbstractFunctional.click(this.driver, this.checkbox);
    }

}
