package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static by.tms.lesson22.onl30.other.Constants.NameFile.CSV_NAME_FILE;

public final class HistoryHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String historyString = Files.readString(Paths.get(CSV_NAME_FILE));
        String[] historyArray = historyString.split("\n");
        Response response = prepareResponseHistory(historyArray);
        exchangeAll(exchange, response);
    }
}