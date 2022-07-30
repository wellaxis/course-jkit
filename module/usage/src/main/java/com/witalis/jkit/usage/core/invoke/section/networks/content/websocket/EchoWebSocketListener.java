package com.witalis.jkit.usage.core.invoke.section.networks.content.websocket;

import lombok.extern.slf4j.Slf4j;

import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;

/**
 * Desc: Web socket echo listener
 * User: Wellaxis
 * Date: 4/24/2022
 */
@Slf4j
public class EchoWebSocketListener implements WebSocket.Listener {
    private final String message = "echo";
    private final ExecutorService executor;

    public EchoWebSocketListener(ExecutorService executor) {
        this.executor = executor;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        log.info(" WSListener: open");
        log.info(String.format("WEB-SOCKET> Request: %s", message));
        webSocket.sendText(message, true);
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        log.error(" WSListener: error", error);
        WebSocket.Listener.super.onError(webSocket, error);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        log.info(" WSListener: receive");
        log.info(String.format("WEB-SOCKET> Response: %s", data));
        if (!webSocket.isOutputClosed()) {
            webSocket.sendText("back", true);
        }
        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    @Override
    public CompletionStage<?>onClose(WebSocket webSocket, int statusCode, String reason) {
        log.info(" WSListener: close");
        log.info(String.format("WEB-SOCKET> Response closed with status %d, and reason: %s", statusCode, reason));
        executor.shutdown();
        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
    }
}
