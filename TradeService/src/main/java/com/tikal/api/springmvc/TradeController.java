package com.tikal.api.springmvc;

import com.tikal.api.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pniel abramovich
 */
@RestController
@RefreshScope
@RequestMapping(value = "/trade")
@Profile("servlet-container")
public class TradeController {


    @Autowired
    private RestTemplate loadBalancer;


    @Autowired
    private Paths paths;

    @RequestMapping(value = "/v1/stock/{symbol}", method = RequestMethod.GET)
    public String getStockBySymbolV1(@PathVariable(value = "symbol") String symbol) {
        String path = String.format("http://%s/%s", Paths.V1_STOCK_SERVICE_RIBBON_NAME, paths.getStockBySymbolV1Path());
        return loadBalancer.getForObject(path, String.class, symbol);
    }

    @RequestMapping(value = "/v2/stock/{symbol}", method = RequestMethod.GET)
    public String getStockBySymbolV2(@PathVariable(value = "symbol") String symbol) {
        String path = String.format("http://%s/%s", Paths.V2_STOCK_SERVICE_RIBBON_NAME, paths.getStockBySymbolV2Path());
        return loadBalancer.getForObject(path, String.class, symbol);
    }
}
