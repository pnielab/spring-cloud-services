package com.tikal.service.stock;

import com.tikal.model.Stock;

/**
 * Created Pniel Abramovich
 */
public interface StockService {

    Stock getStockBySymbol(String symbol);
}
