package com.tikal.api.vertx.springmanagment.springloadbalancer.workers.v2;

import com.tikal.api.vertx.MessageHandler;
import com.tikal.api.vertx.springmanagment.stockservice.StockService;
import com.tikal.configuration.VertxConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import org.jacpfx.vertx.spring.SpringVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by pniel abramovich
 */
@Profile("vertx")
@Component(value = "tradeVerticalWorkerV2")
@Scope(value = "prototype")
@SpringVerticle(springConfig = VertxConfiguration.class)
public class TradeVerticalWorker extends AbstractVerticle implements MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(TradeVerticalWorker.class);

    @Autowired
    private StockService stockService;

    private String id = UUID.randomUUID().toString();

    @Override
    public void start() throws Exception {
        super.start();
        logger.info("register v2 trade vertical to event bus, vertical id: {}", id);
        vertx.eventBus().consumer("com.tikal.api.vertx.workers.v2.TradeVerticalWorker").handler(this::handle);
    }

    @Override
    public void handle(Message<Object> msg) {
        logger.info("start handle message trade vertical worker v2 with id: {}", id);
        String method = msg.headers().get("method");
        try {
            switch (method) {
                case "getStockBySymbol":
                    stockService.getStockBySymbol(msg);
                    break;
                default:
                    logger.error("method do not exist: {}", method);
                    msg.fail(404, "method do not exist: " + method);
                    break;

            }
        } catch (Exception e) {
            logger.error("unexpected error: {}", e.getMessage(), e);
            msg.fail(500, String.format("unable to execute method: %s, error: %s", method, e.getLocalizedMessage()));
        }
        logger.info("finish handle message trade vertical worker v2 with id: {}", id);
    }
}
