package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.ResponseCalculator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static by.tms.lesson22.onl30.other.Constants.ResultTemplate.TEST_TEMPLATE;

public final class TestHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        ResponseCalculator response = new ResponseCalculator();
        response.setBodyResponse(TEST_TEMPLATE);
        exchangeAll(exchange, response);
    }
}
