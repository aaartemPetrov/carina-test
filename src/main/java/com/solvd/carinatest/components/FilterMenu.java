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

    public void chooseAndClick(String menuItem) {
        this.filterMenuItems.stream()
                .filter(filterMenuItem -> menuItem.toLowerCase(Locale.ROOT).equals(filterMenuItem.getLabelLink().getText().toLowerCase(Locale.ROOT)))
                .findFirst()
                .map(filterMenuItem -> filterMenuItem.getLabelLink())
                .orElseThrow(() -> new RuntimeException(String.format("There is no \"%s\" menu item.", menuItem)))
                .click();
    }

    public List<FilterMenuItem> getFilterMenuItems() {
        return this.filterMenuItems;
    }

    public void setFilterMenuItems(List<FilterMenuItem> filterMenuItems) {
        this.filterMenuItems = filterMenuItems;
    }

}
