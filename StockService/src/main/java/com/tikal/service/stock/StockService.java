package com.tikal.service.stock;

import com.tikal.dtos.model.Stock;

/**
 * Created Pniel Abramovich
 */
public interface StockService {

    Stock getStockBySymbol(String symbol);
}
