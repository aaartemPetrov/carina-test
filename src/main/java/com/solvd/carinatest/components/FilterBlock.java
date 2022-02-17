package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;

public class FilterBlock extends AbstractUIObject {

    @FindBy(css = "div.x-refine__select__svg")
    private List<FilterItem> filterItems;

    public FilterBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnCheckbox(String item) {
        new WebDriverWait(getDriver(), 10)
                .until(notUsed -> !this.filterItems.isEmpty());

        this.filterItems.stream()
                .filter(filterItem -> item.toLowerCase(Locale.ROOT).equals(
                        StringUtils.substringBefore(filterItem/*.getLabel().getElement()*/.labelText().toLowerCase(Locale.ROOT), "\n")))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no \"%s\" filter item.", item)))
                .clickCheckbox();
    }

}
