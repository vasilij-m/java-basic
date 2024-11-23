package ru.otus.http.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.http.server.app.Item;
import ru.otus.http.server.app.ItemsRepository;
import ru.otus.http.server.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {

    private static final Logger LOGGER = LogManager.getLogger(Dispatcher.class.getName());

    private final Map<String, RequestProcessor> processors;
    private final RequestProcessor defaultNotFoundProcessor;
    private final RequestProcessor defaultInternalServerErrorProcessor;
    private final RequestProcessor defaultBadRequestProcessor;

    private ItemsRepository itemsRepository;

    public Dispatcher() {
        this.itemsRepository = new ItemsRepository();

        this.processors = new HashMap<>();
        this.processors.put("GET /", new HelloWorldProcessor());
        this.processors.put("GET /calculator", new CalculatorProcessor());
        this.processors.put("GET /items", new GetAllItemsProcessor(itemsRepository));
        this.processors.put("POST /items", new CreateNewItemsProcessor(itemsRepository));
        this.defaultNotFoundProcessor = new DefaultNotFoundProcessor();
        this.defaultInternalServerErrorProcessor = new DefaultNotFoundProcessor();
        this.defaultBadRequestProcessor = new DefaultBadRequestProcessor();
    }

    public void execute(HttpRequest request, OutputStream out) throws IOException {
        try {
            if (!processors.containsKey(request.getRoutingKey())) {
                defaultNotFoundProcessor.execute(request, out);
                return;
            }
            processors.get(request.getRoutingKey()).execute(request, out);
        } catch (BadRequestException e) {
            request.setException(e);
            defaultBadRequestProcessor.execute(request, out);
        } catch (Exception e) {
            LOGGER.error("An exception occurred.", e);
            defaultInternalServerErrorProcessor.execute(request, out);
        }
    }
}
