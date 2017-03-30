package com.tikal.service.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pniel Abramovich
 */
@Service
public class StockProviderFactory {

    @Autowired
    private StockProvider yahoo;

    public StockProvider getStockProvider(){
        return yahoo;
    }

}
