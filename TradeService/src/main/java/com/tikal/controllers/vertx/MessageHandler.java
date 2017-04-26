package com.tikal.controllers.vertx;

import io.vertx.core.eventbus.Message;

/**
 * Created by pniel abramovich
 */
public interface MessageHandler {


    void handle(Message<Object> msg);
}
