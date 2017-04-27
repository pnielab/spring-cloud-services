package com.tikal.api.vertx.springmanagment.stockservice;

import com.tikal.api.Paths;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Pniel Abramovich
 */
@Service
@Profile("vertx-httpClient")
public class VertxHttpClient implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(VertxHttpClient.class);

    @Autowired
    private Paths paths;

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private LoadBalancerClient loadBalancer;


    @Override
    public void getStockBySymbol(Message<Object> msg) {
        String path = getAddress();

        //String.format("http://%s/%s", Paths.V1_STOCK_SERVICE_RIBBON_NAME, paths.getStockBySymbolV1Path());

        String symbol = msg.headers().get("symbol");
        String p = paths.getStockBySymbolV1Path().replace("{symbol}", msg.headers().get("symbol"));
        ServiceInstance instance = loadBalancer.choose(Paths.V1_STOCK_SERVICE_RIBBON_NAME);

        httpClient.getNow(instance.getPort(), instance.getHost(), p, new Handler<HttpClientResponse>() {

            @Override
            public void handle(HttpClientResponse httpClientResponse) {
                httpClientResponse.bodyHandler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer) {
                        msg.reply(buffer.toString());

                    }
                });
            }
        });
    }

    public String getAddress() {
        return "";
    }
}
