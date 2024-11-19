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
    private Exception exception;

    public HttpRequest(String rawRequest) {
        this.rawRequest = rawRequest;
        this.parse();
    }

    public String getUri() {
        return uri;
    }

    public String getParameter(String key) {
        return parameters.get(key);
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
    }

    public void info(boolean debug) {
        if (debug) {
            LOGGER.debug("RAW REQUEST:\n{}",rawRequest);
        }
    }
}
