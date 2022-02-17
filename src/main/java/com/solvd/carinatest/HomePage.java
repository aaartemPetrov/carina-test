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

    public void typeInSearchInput(String text) {
        AbstractFunctional.sendKeys(this.driver, this.searchInput, text);
    }

    public void clickSearchButton() {
        AbstractFunctional.click(this.driver, this.searchButton);
    }

}
