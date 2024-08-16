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
import java.util.HashMap;
import java.util.Map;

import static by.tms.lesson22.onl30.handlers.TestHandler.exchangeAll;
import static by.tms.lesson22.onl30.other.Constants.CodeResponse.NOT_FOUND;
import static by.tms.lesson22.onl30.other.Constants.CodeResponse.OK;
import static by.tms.lesson22.onl30.other.Constants.FormatCsvFile.CSV_TEMPLATE;
import static by.tms.lesson22.onl30.other.Constants.NameFile.CSV_NAME_FILE;
import static by.tms.lesson22.onl30.other.Constants.ResultTemplate.ERROR_BODY_TEMPLATE;

public class CalculatorHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Response response = new Response();
        String request = exchange.getRequestURI().getQuery(); //http://localhost:8080/calculator?num1=7&num2=5&type=sum
        Map<String, String> parametersRequest = parseRequest(request);
        String firstString = parametersRequest.get("num1");
        String secondString = parametersRequest.get("num2");
        String typeOperation = parametersRequest.get("type");
        if (isNotNumber(firstString) || isNotNumber(secondString)) {
            response.setCodeResponse(NOT_FOUND);
            response.setBodyResponse("one of the operands is not a number");
        } else {
            response = calculate(Integer.valueOf(firstString),
                    Integer.valueOf(secondString), typeOperation);
        }
        exchangeAll(exchange, response);
        if (response.getCodeResponse() == OK) {
            writeFile(firstString, secondString, typeOperation);
        }
    }

    private Response calculate(double num1, double num2, String typeOperation) {
        Response response = new Response();
        switch (typeOperation) {
            case "sum" -> response.setBodyResponse(String.valueOf(num1 + num2));
            case "diff" -> response.setBodyResponse(String.valueOf(num1 - num2));
            case "mul" -> response.setBodyResponse(String.valueOf(num1 * num2));
            case "div" -> response.setBodyResponse(String.valueOf(num1 / num2));
            case "prc" -> response.setBodyResponse(String.valueOf(num1 * num2 / 100));
            default -> {
                response.setCodeResponse(NOT_FOUND);
                response.setBodyResponse(String.format(ERROR_BODY_TEMPLATE, typeOperation));
            }
        }
        return response;
    }

    private static Map<String, String> parseRequest(String request) {
        String[] params = request.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    private static boolean isNotNumber(String stringAsNumber) {
        try {
            Integer.valueOf(stringAsNumber);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private static void writeFile(String firstString, String secondString, String typeOperation) {
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
