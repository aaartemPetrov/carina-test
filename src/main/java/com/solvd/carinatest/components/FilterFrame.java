package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterFrame extends AbstractUIObject {

    private final By rootElement = By.cssSelector("#x-overlay__form");

    @FindBy(css = "#x-overlay__form div.x-overlay__wrapper--left")
    private FilterMenu filterMenu;
    @FindBy(css = "#x-overlay__form div.x-overlay__wrapper--right")
    private FilterBlock filterBlock;
    @FindBy(css = "#x-overlay__form div.x-overlay-footer__apply button")
    private ExtendedWebElement applyButton;

    public FilterFrame(WebDriver driver) {
        super(driver);
        switchAndWait(10);
    }

    public void switchAndWait(int seconds) {
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOfElementLocated(this.rootElement));
        setRootElement(getDriver().findElement(this.rootElement));
        getDriver().switchTo().activeElement();
        Wait<WebDriver> webDriverWait = new WebDriverWait(getDriver(), seconds);
        webDriverWait.until(notUsed -> this.filterMenu != null && this.filterBlock != null);
    }

    public FilterMenu getFilterMenu() {
        return this.filterMenu;
    }

    public void setFilterMenu(FilterMenu filterMenu) {
        this.filterMenu = filterMenu;
    }

    public FilterBlock getFilterBlock() {
        return this.filterBlock;
    }

    public void setFilterBlock(FilterBlock filterBlock) {
        this.filterBlock = filterBlock;
    }

    public ExtendedWebElement getApplyButton() {
        return this.applyButton;
    }

    public void setApplyButton(ExtendedWebElement applyButton) {
        this.applyButton = applyButton;
    }

}
