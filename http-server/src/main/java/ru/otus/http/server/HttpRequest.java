package ru.otus.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private static final Logger LOGGER = LogManager.getLogger(HttpRequest.class.getName());

    private String rawRequest;
    private HttpMethod method;
    private String uri;
    private Map<String, String> parameters;
    private Map<String, String> headers;
    private String body;
    private Exception exception;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.parse();
    }

    public String getUri() {
        return uri;
    }

    public String getRoutingKey() {
        return method + " " + uri;
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public String getBody() {
        return body;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean containsParameter(String key) {
        return parameters.containsKey(key);
    }

    private void parse() {
        int startIndex = rawRequest.indexOf(' ');
        int endIndex = rawRequest.indexOf(' ', startIndex + 1);
        uri = rawRequest.substring(startIndex + 1, endIndex);
        method = HttpMethod.valueOf(rawRequest.substring(0, startIndex));

        parameters = new HashMap<>();
        if (uri.contains("?")) {
            String[] elements = uri.split("[?]");
            uri = elements[0];
            String[] keysValues = elements[1].split("[&]");
            for (String o : keysValues) {
                String[] keyValue = o.split("=");
                parameters.put(keyValue[0], keyValue[1]);
            }
        }

        headers = new HashMap<>();
        startIndex = rawRequest.indexOf("\r\n") + 2;
        endIndex = rawRequest.indexOf("\r\n\r\n");
        String[] headersWithValues = rawRequest.substring(startIndex, endIndex).split("\r\n");
        for (String o : headersWithValues) {
            String[] headerValue = o.split(": ");
            headers.put(headerValue[0], headerValue[1]);
        }

        if (method == HttpMethod.POST) {
            body = rawRequest.substring(rawRequest.indexOf("\r\n\r\n") + 4);
        }
    }

    public void info() {
        LOGGER.debug("RAW REQUEST:\n{}",rawRequest);
        LOGGER.info("Method: {}",method);
        LOGGER.info("URI: {}",uri);
        LOGGER.info("Parameters: {}",parameters);
        LOGGER.info("Headers: {}",headers);
        LOGGER.info("Body: {}",body);
    }
}
