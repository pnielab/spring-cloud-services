package com.tikal.service.stock;

import com.tikal.dtos.v1.StockToStockDTOConverter;
import com.tikal.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pniel Abramovich
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockProviderFactory stockProviderFactoryFactory;

    @Autowired
    private StockToStockDTOConverter stockConverter;

    @Override
    public Stock getStockBySymbol(String symbol) {
        return stockProviderFactoryFactory.getStockProvider().getStockBySymbol(symbol);
    }
}
