package com.solvd.carinatest.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductBlock extends AbstractUIObject {

    @FindBy(css = "#s0-14-11-6-3-query_answer1-answer-2-1-0-container")
    private PriceFilterBlock priceFilterBlock;
    @FindBy(css = "li.s-item")
    private List<Product> products;

    public ProductBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickOnProductByName(String productName) {
        this.products.stream()
                .filter(product -> productName.toLowerCase(Locale.ROOT).equals(product.getProductName().toLowerCase(Locale.ROOT)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("There is no \"%s\" in searched product list.")))
                .clickOnProduct();
    }

    public int productsCount() {
        return  this.products.size();
    }

    public List<String> getProductsNames() {
        return this.products.stream()
                .map(product -> product.getProductName())
                .collect(Collectors.toList());
    }

    public List<String> getProductsPrices() {
        return this.products.stream()
                .map(product -> product.getProductPrice())
                .collect(Collectors.toList());
    }

    public String underPriceLinkText() {
        return this.priceFilterBlock.underPriceLinkText();
    }

    public String fromToPriceLinkText() {
        return this.priceFilterBlock.fromToPriceLinkText();
    }

    public void clickUnderPriceLink() {
        this.priceFilterBlock.clickUnderPriceLink();
    }

    public void clickFromToPriceLink() {
        this.priceFilterBlock.clickFromToPriceLink();
    }

    public int getUnderFilterPrice() {
        return this.priceFilterBlock.getUnderFilterPrice();
    }

    public int getFromFilterPrice() {
        return this.priceFilterBlock.getFromFilterPrice();
    }

    public int getToFilterPrice() {
        return this.priceFilterBlock.getToFilterPrice();
    }

}
