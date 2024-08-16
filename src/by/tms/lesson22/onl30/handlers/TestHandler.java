package by.tms.lesson22.onl30.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Answer from TestPage: Hello World!";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

//        String query = exchange.getRequestURI().getQuery(); //name=Test
//
//
//
//        exchange.getResponseBody().write(message.getBytes());
//
//        exchange.getResponseBody().close();
    }
}
