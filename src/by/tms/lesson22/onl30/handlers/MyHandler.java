package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static by.tms.lesson22.onl30.other.Constants.FormatCsvFile.CSV_TEMPLATE;
import static by.tms.lesson22.onl30.other.Constants.NameFile.CSV_NAME_FILE;

public abstract class MyHandler implements HttpHandler {

    protected void exchangeAll(HttpExchange exchange, Response response) throws IOException {
        exchange.sendResponseHeaders(response.getCodeResponse(), response.getBodyResponse().length());
        exchange.getResponseBody().write(response.getBodyResponse().getBytes());
        exchange.getResponseBody().close();
    }

    protected void writeFile(String firstString, String secondString, String typeOperation) {
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zoneDateTime = dateTime.atZone(ZoneId.of("Europe/Berlin"));
        String lineFileCsv = String.format(CSV_TEMPLATE, zoneDateTime.toInstant().toEpochMilli(),
                firstString, secondString, typeOperation);
        try {
            Files.write(Paths.get(CSV_NAME_FILE), lineFileCsv.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }
}
