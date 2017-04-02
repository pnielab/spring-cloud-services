package com.tikal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pniel abramovich
 */
@RestController
@RequestMapping(value = "/trade")
public class TradeController {


    @Autowired
    private RestTemplate loadBalancer;

    @RequestMapping(value = "/stock/{symbol}", method = RequestMethod.GET)
    public String getStockBySymbol(@PathVariable(value = "symbol") String symbol) {
        String path = String.format("http://%s%s", Paths.STOCK_SERVICE_RIBBON_NAME, Paths.STOCK_BY_SYMBOL_PATH);
        return loadBalancer.getForObject(path, String.class, symbol);
    }


}
