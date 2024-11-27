package ru.otus.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private static final Logger LOGGER = LogManager.getLogger(HttpServer.class.getName());

    private final int port;
    private final ExecutorService pool;
    private final Dispatcher dispatcher;

    public HttpServer(int port, int poolSize) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.pool = Executors.newFixedThreadPool(poolSize);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("Server started on port {}", port);
            while (true) {
                Socket socket = serverSocket.accept();
                LOGGER.info("Socket accepted");
                pool.execute(() -> processSocket(socket));
            }
        } catch (IOException e) {
            LOGGER.fatal("Fatal error", e);
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }

    private void processSocket(Socket socket) {
        try (socket;
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int n = inputStream.read(buffer);
            if (n < 1) {
                return;
            }
            String rawRequest = new String(buffer, 0, n);
            HttpRequest request = new HttpRequest(rawRequest);
            request.info();
            dispatcher.execute(request, outputStream);
        } catch (IOException e) {
            LOGGER.error("An exception occurred.", e);
        }
    }
}
