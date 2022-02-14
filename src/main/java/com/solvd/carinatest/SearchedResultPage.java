package com.solvd.carinatest;

import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.carinatest.components.ProductBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchedResultPage extends AbstractPage {

    @FindBy(css = "#srp-river-results")
    private ProductBlock productBlock;

    public SearchedResultPage(WebDriver driver) {
        super(driver);
    }

    public ProductBlock getProductBlock() {
        return productBlock;
    }

    public void setProductBlock(ProductBlock productBlock) {
        this.productBlock = productBlock;
    }

}
