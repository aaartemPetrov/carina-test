package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchTooltip extends AbstractUIObject {

    private final By rootElement = By.cssSelector("#gAC");
    @FindBy(css = "li a.ghAC_sugg")
    private List<Tooltip> tooltips;

    public SearchTooltip(WebDriver driver) {
        super(driver);
        setRootElement(driver.findElement(this.rootElement));
    }

    public int tooltipsCount() {
        return tooltips.size();
    }

}
