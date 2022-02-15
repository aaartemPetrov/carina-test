package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductBlock extends AbstractUIObject {

    @FindBy(css = "#s0-14-11-6-3-query_answer1-answer-2-1-0-container")
    private PriceFilterBlock priceFilterBlock;
    @FindBy(css = "li.s-item")
    private List<Product> products;

    public ProductBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public PriceFilterBlock getPriceFilterBlock() {
        return this.priceFilterBlock;
    }

    public void setPriceFilterBlock(PriceFilterBlock priceFilterBlock) {
        this.priceFilterBlock = priceFilterBlock;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
