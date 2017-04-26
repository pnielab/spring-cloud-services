package com.tikal.controllers.vertx;

import com.tikal.configuration.VertxConfiguration;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.apache.commons.lang.StringUtils;
import org.jacpfx.vertx.spring.SpringVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Pniel abramovich
 */
@Profile("vertx")
@Component
@SpringVerticle
public class TradeServiceVerticalBootstrap implements ApplicationContextAware {


    private static final Logger logger = LoggerFactory.getLogger(TradeServiceVerticalBootstrap.class);

    private ApplicationContext applicationContext;

    @Autowired
    @Qualifier(value = "getVertxInstance")
    private Vertx vertx;

    @Autowired
    private VertxConfiguration vertxConfiguration;


    private String activeVersion = "v2";

    @PostConstruct
    public void start() {

        logger.info("successfully loaded trade service vertical");
        // deploy the worker vertical and set it to have 4 instances
        DeploymentOptions tradeServiceDeploymentOptions = new DeploymentOptions();
        for (int i = 0; i < 4; i++) {
            try {
                initVertical("tradeVerticalWorkerV1");
                initVertical("tradeVerticalWorkerV2");
                // deploy(tradeVerticalWorkerV1Provider.get(), tradeServiceDeploymentOptions, "TradeVerticalWorker v1");
                // deploy(tradeVerticalWorkerV2Provider.get(), tradeServiceDeploymentOptions, "TradeVerticalWorker v2");
            } catch (Exception e) {
                logger.error("unable to init vertical: {}", e.getMessage(), e);
            }
        }
        startHttpServer();
    }

    /**
     * initialize and start the vertical, we handle vertical initialization insted of vertx
     */
    private void initVertical(String verticalName) throws Exception {
        AbstractVerticle vertical = (AbstractVerticle) applicationContext.getBean(verticalName);
        vertical.init(vertx, vertx.getOrCreateContext());
        vertical.start();
    }

    private void deploy(AbstractVerticle vertical, DeploymentOptions tradeServiceDeploymentOptions, String desc) {
        vertx.deployVerticle(vertical, tradeServiceDeploymentOptions, res -> {
            logger.info("start deployment of vertical: {}", desc);
            if (res.succeeded()) {
                logger.info("successfully deployed vertical: {}", desc);
            } else {
                logger.error("failure deployment of vertical: {}, result: {}", desc, res.result(), res.cause());
            }
        });
    }

    /**
     * create http server (event loop) and declare the routing according to api
     */
    private void startHttpServer() {
        //create Router and HttpServer
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        DeliveryOptions deliveryOptions = new DeliveryOptions();
        deliveryOptions.setSendTimeout(2000);

        router.get("/trade/stock/:symbol").produces("application/json")
                .handler(rc -> {
                    deliveryOptions.addHeader("method", "getStockBySymbol")
                            .addHeader("symbol", rc.request().getParam("symbol"));
                    String address = String.format("com.tikal.controllers.vertx.workers.%s.TradeVerticalWorker", getVersion(rc));
                    vertx.eventBus().send(address, null, deliveryOptions, reply -> handleReplay(reply, rc));
                });
        vertx.createHttpServer().requestHandler(router::accept).listen(vertxConfiguration.getHttpPort());
    }

    private void handleReplay(AsyncResult<Message<Object>> reply, RoutingContext rc) {
        if (reply.succeeded()) {
            Message<Object> msg = reply.result();
            rc.response().setStatusMessage("200").setStatusMessage("OK")
                    .putHeader("Content-Type", "application/json")
                    .end(String.valueOf(msg.body()));
        } else {
            ReplyException e = (ReplyException) reply.cause();
            rc.response().setStatusCode((e.failureCode())).setStatusMessage(e.getMessage()).end(e.getLocalizedMessage());
        }
    }

    private String getVersion(RoutingContext rc) {
        String version = rc.request().getHeader("version");
        return StringUtils.isEmpty(version) ? activeVersion : version;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
