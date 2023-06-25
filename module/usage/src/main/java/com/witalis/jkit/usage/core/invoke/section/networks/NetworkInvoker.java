package com.witalis.jkit.usage.core.invoke.section.networks;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.networks.content.datagram.*;
import com.witalis.jkit.usage.core.invoke.section.networks.content.scenario.*;
import com.witalis.jkit.usage.core.invoke.section.networks.content.websocket.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import jakarta.websocket.*;
import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Desc: Networks
 * User: Wellaxis
 * Date: 2021/01/25
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class NetworkInvoker extends Invoker {

    public NetworkInvoker() {
        setTitle("Network chapter.");
    }

    @Override
    public void invoke() {
        // address
        log.info("## Address");
        invokeAddress();
        // tab
        log.info("");
        // socket
        log.info("## Socket");
        invokeSocket();
        // tab
        log.info("");
        // url
        log.info("## URL");
        invokeURL();
        // tab
        log.info("");
        // datagram
        log.info("## Datagram");
        invokeDatagram();
        // tab
        log.info("");
        // client
        log.info("## HTTP Client");
        invokeClient();
        // tab
        log.info("");
        // web
        log.info("## HTTP Web");
        invokeWeb();
        // tab
        log.info("");
        // web socket
        log.info("## Web Socket");
        invokeWebSocket();
        // tab
        log.info("");
        // scenario
        log.info("## Scenario");
        invokeScenarios();
    }

    /**
     * Address functionality
     */
    private void invokeAddress() {
        log.info("---- Inet Address");
        try {
            // loopback
            InetAddress address = InetAddress.getLoopbackAddress();
            log.info(" * loopback: " + address);
            // localhost
            address = InetAddress.getLocalHost();
            log.info(" * localhost: " + address);
            // by name
            address = InetAddress.getByName("www.google.com");
            log.info(" * by name (\"www.google.com\"): " + address);
            // all by name
            var addresses = InetAddress.getAllByName("microsoft.com");
            log.info(" * all by name (\"microsoft.com\"): " + addresses.length);
            var count = 0;
            for (InetAddress item: addresses) {
                log.info("   - item[" + count++ + "]: " + item);
            }
            // ip 4
            var ip4 = Inet4Address.getLocalHost();
            log.info(" * IP4 host name: " + ip4.getHostName());
            // ip 6
            var ip6 = Inet6Address.getLocalHost();
            log.info(" * IP6 host address: " + ip6.getHostAddress());
        } catch (IOException e) {
            log.error("Inet address errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * Socket functionality
     */
    private void invokeSocket() {
        log.info("---- Inet Socket");
        var address = "sysdate.com\n";
        // socket
        try (Socket socket = new Socket("whois.internic.net", 43)) {
            if (socket.isConnected()) {
                var in = socket.getInputStream();
                var out = socket.getOutputStream();
                // request
                log.info(" * request: " + address);
                var request = address.getBytes();
                out.write(request);
                // response
                int c;
                var content = new StringBuilder("\n");
                while ((c = in.read()) != -1) {
                    content.append((char) c);
                }
                log.info(" * response: " + '\n');
                log.info(content.toString());
            }
        } catch (IOException e) {
            log.error("Inet socket errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * URL functionality
     */
    private void invokeURL() {
        log.info("---- Inet URL");
        try {
            URL hp = new URL("http://www.sysdate.com/info.htm");
            log.info(" * form: " + hp.toExternalForm());
            log.info(" * path: " + hp.getPath());
            log.info(" * user: " + hp.getUserInfo());
            log.info(" * authority: " + hp.getAuthority());
            log.info(" * protocol: " + hp.getProtocol());
            log.info(" * host: " + hp.getHost());
            log.info(" * port: " + hp.getPort());
            log.info(" * file: " + hp.getFile());
        } catch (IOException e) {
            log.error("Inet resource errors: " + e.getLocalizedMessage());
        }

        log.info("---- Inet URL Connection");
        try {
            URL hp = new URL("http://www.HerbSchildt.com/Articles");
            // connection
            log.info(" * connection:");
            URLConnection connection = hp.openConnection();
            var date = connection.getDate();
            if (date != 0)
                log.info("   - date: {}", new Date(date));
            var expiration = connection.getExpiration();
            if (expiration != 0)
                log.info("   - expiration: {}", new Date(expiration));
            var lastModified = connection.getLastModified();
            if (lastModified != 0)
                log.info("   - last modified: {}", new Date(lastModified));
            var contentType = connection.getContentType();
            log.info("   - content type: {}", contentType);
            var contentLength = connection.getContentLength();
            log.info("   - content length: {}", contentLength);
            // stream
            if (contentLength > 0) {
                log.info("   - content:");
                int c;
                var content = new StringBuilder("\n");
                var in = connection.getInputStream();
                while ((c = in.read()) != -1) {
                    content.append((char) c);
                }
                log.info(content.toString());
                in.close();
            }
        } catch (IOException e) {
            log.error("Inet resource errors: " + e.getLocalizedMessage());
        }

        log.info("---- Inet URL HTTP Connection");
        try {
            URL hp = new URL("https://www.google.com");
            // http connection
            log.info(" * http connection:");
            HttpURLConnection httpConnection = (HttpURLConnection) hp.openConnection();
            var requestMethod = httpConnection.getRequestMethod();
            log.info("   - request method: {}", requestMethod);
            var responseCode = httpConnection.getResponseCode();
            log.info("   - response code: {}", responseCode);
            var responseMessage = httpConnection.getResponseMessage();
            log.info("   - response message: {}", responseMessage);
            // headers
            log.info("   - headers:");
            var headers = httpConnection.getHeaderFields();
            for (String header: headers.keySet()) {
                log.info("     > " + header + ": " + headers.get(header));
            }
        } catch (IOException e) {
            log.error("Inet resource errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * Datagram functionality
     */
    private void invokeDatagram() {
        log.info("---- Inet Datagram");
        try {
            // server
            var serverName = "JKit-UDP-Server";
            var serverHost = InetAddress.getLocalHost().getHostAddress();
            var serverPort = 987;
            DatagramServer server = new DatagramServer(
                serverName,
                serverHost,
                serverPort
            );
            server.start();
            // client
            var clientName = "JKit-UDP-Client";
            var clientHost = InetAddress.getLocalHost().getHostAddress();
            var clientPort = 988;
            DatagramClient client = new DatagramClient(
                clientName,
                clientHost,
                clientPort,
                serverHost,
                serverPort
            );
            client.start();
            // process
            Thread.sleep(1000);
            // stop
            server.interrupt();
            client.interrupt();
        } catch (IOException | InterruptedException e) {
            log.error("Inet datagram errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * HTTP client functionality
     */
    private void invokeClient() {
        // since JDK-11

        log.info("---- Inet HTTP Client");
        try {
            // client
            HttpClient client = HttpClient.newHttpClient();
            // request
            HttpRequest request = HttpRequest.newBuilder(
                new URI("http://www.google.com")
            ).build();
            // response
            HttpResponse<InputStream> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofInputStream()
            );
            log.info(" * http client:");
            // attributes
            var method = request.method();
            log.info("   - request method: {}", method);
            var status = response.statusCode();
            log.info("   - response status: {}", status);
            // headers
            log.info("   - headers:");
            var headers = response.headers().map();
            for (String header: headers.keySet()) {
                log.info("     > " + header + ": " + headers.get(header));
            }
            // body
            int c;
            var content = new StringBuilder("\n");
            var in = response.body();
            while ((c = in.read()) != -1) {
                content.append((char) c);
            }
            log.info(content.toString());
            in.close();
        } catch (IOException | URISyntaxException | InterruptedException e) {
            log.error("Inet http client errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * Web socket functionality
     */
    private void invokeWeb() {
        // since JDK-11

        log.info("---- Inet Web Socket");

        final int threads = 5;
        try {
            ExecutorService executor = Executors.newFixedThreadPool(
                threads,
                r -> {
                    Thread thread = new Thread(r);
                    if (log.isDebugEnabled()) {
                        var daemon = thread.isDaemon() ? "daemon" : "";
                        var group = thread.getThreadGroup();
                        log.debug("Created ws executor " + daemon + "thread with group: " + group);
                    }
                    return thread;
                }
            );
            var httpClient = HttpClient.newBuilder().executor(executor).build();
            var builder = httpClient.newWebSocketBuilder();
            // build
            var socket = builder.buildAsync(
                URI.create("wss://echo.websocket.events"),
                new EchoWebSocketListener(executor)
            ).join();
            log.info(" * Web socket has been opened");
            // close
            Thread.sleep(1000);
            socket.sendClose(
                WebSocket.NORMAL_CLOSURE,
                "ok"
            ).thenRun(
                () -> log.info(" * Web socket has been closed")
            ).join();
            Thread.sleep(500);
        } catch (Exception e) {
            log.error("Inet http client errors: " + e.getLocalizedMessage());
        }
    }

    /**
     * Web Socket functionality
     */
    private void invokeWebSocket() {
        // WebSocket - computer communications protocol,
        // providing full-duplex communication channels over a single TCP connection

        log.info("---- Inet Web Socket");

        try {
            CountDownLatch messageLatch = new CountDownLatch(1);
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = "ws://echo.websocket.events:80/";
            log.info("WebSocket [uri]: {}", uri);
            Session session = container.connectToServer(
                new InfoWebSocketEndpoint(messageLatch),
                URI.create(uri)
            );
            var id = session.getId();
            log.info("WebSocket [session]: {}", id);
            boolean await = messageLatch.await(100, TimeUnit.SECONDS);
            session.close();
        } catch (DeploymentException | InterruptedException | IOException e) {
            log.error("WebSocket [error]: unable to process connection - {}", e.getCause().getMessage());
        }
    }

    /**
     * Frequent network scenarios
     */
    private void invokeScenarios() {
        // Without the use of additional libraries, the basis
        // of low-level networking in Java is built on two classes:
        // Socket and ServerSocket.

        log.info("---- Socket Scenarios");

        // single client/server scenario
        {
            log.info("---- Simple Client/Server Scenario");

            SimpleServer simpleServer = new SimpleServer(7777);
            SimpleClient simpleClient = new SimpleClient("localhost", 7777);

            Thread server = new Thread(simpleServer, "Server");
            Thread client = new Thread(simpleClient, "Client");

            server.start();
            client.start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.error("Simple client/server [error]: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        log.info("");

        // multiple client/server scenario
        {
            log.info("---- Complex Client/Server Scenario");

            // connection
            final var host = "localhost";
            final var port = 8888;
            final var name = "SERVER $$";
            final var timeout = 2_000;

            List<Runnable> tasks = new ArrayList<>();

            // server - it limits by period of time via timer
            ComplexServer complexServer = new ComplexServer(name, port, timeout);

            // clients - it limits by number of instances
            final int counter = 5;
            for (int i = counter; i > 0; i--) {
                tasks.add(
                    new ComplexClient("CLIENT #" + i, name, host, port)
                );
            }

            ExecutorService executor = null;
            Future<?> future = null;
            try {
                executor = Executors.newCachedThreadPool();
                future = executor.submit(complexServer);
                tasks.forEach(executor::submit);

                future.get(timeout, TimeUnit.MILLISECONDS);
                TimeUnit.MILLISECONDS.sleep(timeout);
                if (executor.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (TimeoutException e) {
                future.cancel(true);
                executor.shutdownNow();
            } catch (Exception e) {
                log.error("    Execution errors: " + e.getClass().getName());
                Thread.currentThread().interrupt();
            } finally {
                assert executor != null;
                try {
                    executor.shutdown();
                } finally {
                    executor.shutdownNow();
                }
            }
        }
    }
}
