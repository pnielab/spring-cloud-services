package com.tikal.controllers;

import com.tikal.model.Stock;
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
    private Converter<Stock, com.tikal.dtos.v1.StockDTO> v1StockToStockDTOConverter;

    @Autowired
    private Converter<Stock, com.tikal.dtos.v2.StockDTO> v2StockToStockDTOConverter;


    @RequestMapping(value = "/v1/stock/{symbol}", method = RequestMethod.GET)
    public com.tikal.dtos.v1.StockDTO getV1StockBySymbole(@PathVariable(value = "symbol") String symbol) {
        Stock stock = stockService.getStockBySymbol(symbol);
        return v1StockToStockDTOConverter.convert(stock);
    }


    @RequestMapping(value = "/v2/stock/{symbol}", method = RequestMethod.GET)
    public com.tikal.dtos.v2.StockDTO getV2StockBySymbole(@PathVariable(value = "symbol") String symbol) {
        Stock stock = stockService.getStockBySymbol(symbol);
        return v2StockToStockDTOConverter.convert(stock);
    }
}
