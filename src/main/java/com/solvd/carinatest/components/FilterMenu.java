package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class FilterMenu extends AbstractUIObject {

    @FindBy(css = "div")
    private List<FilterMenuItem> filterMenuItems;

    public FilterMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnMenuItem(String menuItem) {
        this.filterMenuItems.stream()
                .filter(filterMenuItem -> menuItem.toLowerCase(Locale.ROOT).equals(filterMenuItem.getlabelLinkText().toLowerCase(Locale.ROOT)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no \"%s\" menu item.", menuItem)))
                .clickFilterMenuItem();
    }

}
