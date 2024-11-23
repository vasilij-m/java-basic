package ru.otus.http.server.processors;

import ru.otus.http.server.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class DefaultNotFoundProcessor implements RequestProcessor {

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String responce = "" +
                "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body><h1>Page Not Found</h1></body></html>";
        output.write(responce.getBytes(StandardCharsets.UTF_8));
    }
}