package com.tikal.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by pniel abramovich
 */
@RefreshScope
@Component
public class Paths {

    //ribbon-for-stockService

    public static final String STOCK_SERVICE_RIBBON_NAME = "ribbon-for-stockService";

    @Value("${stockBySymbolPath}")
    private String stockBySymbolPath;


    public String getStockBySymbolPath() {
        return stockBySymbolPath;
    }
}
