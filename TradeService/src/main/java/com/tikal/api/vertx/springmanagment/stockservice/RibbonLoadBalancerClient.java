package com.tikal.api.vertx.springmanagment.stockservice;

import com.tikal.api.Paths;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Pniel Abramovich
 */
@Service
@Profile("vertx-springLoadBalancer")
public class RibbonLoadBalancerClient implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(RibbonLoadBalancerClient.class);

    @Autowired
    private RestTemplate loadBalancer;

    @Autowired
    private Paths paths;

    @Override
    public void getStockBySymbol(Message<Object> msg) {
        String path = String.format("http://%s/%s", Paths.V1_STOCK_SERVICE_RIBBON_NAME, paths.getStockBySymbolV1Path());
        String symbol = msg.headers().get("symbol");
        String retVal = loadBalancer.getForObject(path, String.class, symbol);
        msg.reply(retVal);
    }
}
