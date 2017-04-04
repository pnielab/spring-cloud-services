package com.tikal.service.stock;

import com.tikal.model.Stock;

/**
 * Created by pniel abramovich
 */
public interface StockProvider {
    String getStockProviderUrlBySymbol(String symbol);

    Stock getStockBySymbol(String symbol);

}
