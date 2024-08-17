package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.tms.lesson22.onl30.other.Constants.CodeResponse.NOT_FOUND;
import static by.tms.lesson22.onl30.other.Constants.CodeResponse.OK;
import static by.tms.lesson22.onl30.other.Constants.ResultTemplate.OPERAND_NOT_NUMBER;

public final class CalculatorHandler extends MyHandler implements HttpHandler {

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
            response.setBodyResponse(OPERAND_NOT_NUMBER);
        } else {
            response = prepareResponseCalculator(Integer.valueOf(firstString),
                    Integer.valueOf(secondString), typeOperation);
        }
        exchangeAll(exchange, response);
        if (response.getCodeResponse() == OK) {
            Thread threadWriteFile = new Thread(() -> {
                writeFile(firstString, secondString, typeOperation);
            });
            threadWriteFile.start();
        }
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
}