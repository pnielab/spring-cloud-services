package com.tikal.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by pniel abramovich
 */
@RefreshScope
@Component
public class Paths {

    //ribbon-for-stockService

    public static final String V1_STOCK_SERVICE_RIBBON_NAME = "ribbon-for-stockService-v1";
    public static final String V2_STOCK_SERVICE_RIBBON_NAME = "ribbon-for-stockService-v2";


    @Value("${stockBySymbolV1Path}")
    private String stockBySymbolV1Path;

    @Value("${stockBySymbolV2Path}")
    private String stockBySymbolV2Path;

    public String getStockBySymbolV1Path() {
        return stockBySymbolV1Path;
    }

    public void setStockBySymbolV1Path(String stockBySymbolV1Path) {
        this.stockBySymbolV1Path = stockBySymbolV1Path;
    }

    public String getStockBySymbolV2Path() {
        return stockBySymbolV2Path;
    }

    public void setStockBySymbolV2Path(String stockBySymbolV2Path) {
        this.stockBySymbolV2Path = stockBySymbolV2Path;
    }
}
