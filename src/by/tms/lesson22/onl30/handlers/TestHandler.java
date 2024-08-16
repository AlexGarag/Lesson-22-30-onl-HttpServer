package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static by.tms.lesson22.onl30.other.Constants.CodeResponse.OK;
import static by.tms.lesson22.onl30.other.Constants.ResultTemplate.TEST_TEMPLATE;

public class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Response response = new Response();
        response.setCodeResponse(OK);
        response.setBodyResponse(TEST_TEMPLATE);
        exchangeAll(exchange, response);
    }

    public static void exchangeAll(HttpExchange exchange, Response response) throws IOException {
        exchange.sendResponseHeaders(response.getCodeResponse(), response.getBodyResponse().length());
        exchange.getResponseBody().write(response.getBodyResponse().getBytes());
        exchange.getResponseBody().close();
    }
}
