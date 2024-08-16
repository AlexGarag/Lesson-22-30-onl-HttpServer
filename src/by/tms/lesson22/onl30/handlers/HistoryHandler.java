package by.tms.lesson22.onl30.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static by.tms.lesson22.onl30.other.Constants.NameFile.CSV_NAME_FILE;

public final class HistoryHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

// todo сформировать ответ из всей совокупности операций
        String result =  new String(Files.readAllBytes(Paths.get(CSV_NAME_FILE)), StandardCharsets.UTF_8 );
int i = 0;
    }
}
