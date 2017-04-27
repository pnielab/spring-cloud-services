package com.tikal.api.vertx.springmanagment.stockservice;

import io.vertx.core.eventbus.Message;

/**
 * Created by pniel abramovich
 */
public interface StockService {

    void getStockBySymbol(Message<Object> msg);


}
