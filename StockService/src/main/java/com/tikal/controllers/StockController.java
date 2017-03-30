package com.tikal.controllers;

import com.tikal.dtos.StockDTO;
import com.tikal.dtos.model.Stock;
import com.tikal.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pniel Abramovich
 */
@RestController
@RefreshScope
@RequestMapping(value = "/stock-endpoint")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private Converter<Stock, StockDTO> StockToStockDTOConverter;

    @RequestMapping(value = "/stock/{symbol}", method = RequestMethod.GET)
    public StockDTO getStockBySymbole(@PathVariable(value = "symbol") String symbol) {
        Stock stock = stockService.getStockBySymbol(symbol);
        return StockToStockDTOConverter.convert(stock);
    }
}
