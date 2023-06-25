package com.witalis.jkit.usage.core.invoke.section.networks.content.websocket;

import lombok.extern.slf4j.Slf4j;

import jakarta.websocket.*;
import java.util.concurrent.CountDownLatch;

@Slf4j
@ClientEndpoint
public class InfoWebSocketEndpoint {
    private final String message = "websocket";
    private final CountDownLatch messageLatch;

    public InfoWebSocketEndpoint(CountDownLatch messageLatch) {
        this.messageLatch = messageLatch;
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("Endpoint [open]: {}", session.getBasicRemote());
        try {
            log.info("Endpoint [send]: {}", message);
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("Endpoint [error]: unable to send message - {}", e.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Endpoint [message]: {}", session.getBasicRemote());
        try {
            log.info("Endpoint [receive]: {}", message);
            messageLatch.countDown();
        } catch (Exception e) {
            log.error("Endpoint [error]: unable to receive message - {}", e.getMessage());
        }
    }

    @OnError
    public void onError(Throwable t) {
        log.error("Endpoint [error]: {}", t.getMessage());
    }
}
