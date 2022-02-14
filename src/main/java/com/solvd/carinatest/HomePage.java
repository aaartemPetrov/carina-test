package com.solvd.carinatest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "#gh-f input[type=text]")
    private ExtendedWebElement searchInput;
    @FindBy(css = "#gh-f input[type=submit]")
    private ExtendedWebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(ExtendedWebElement searchInput) {
        this.searchInput = searchInput;
    }

    public ExtendedWebElement getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(ExtendedWebElement searchButton) {
        this.searchButton = searchButton;
    }

}
