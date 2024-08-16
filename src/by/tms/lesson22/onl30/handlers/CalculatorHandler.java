package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.tms.lesson22.onl30.other.Constants.CodeResponse.NOT_FOUND;

public class CalculatorHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Response response = new Response();
        String request = exchange.getRequestURI().getQuery(); //num1=2&num2=2&type=sum
        Map<String, String> parametersRequest = parseRequest(request);
// todo сформировать ответ, если не число в параметре
        String firstString = parametersRequest.get("num1");
        String secondString = parametersRequest.get("num2");
        if (isNotNumber(firstString) || isNotNumber(secondString)) {
            response.setCodeResponse(NOT_FOUND);
            response.setBodyResponse("one of the operands is not a number");
        } else {
            response = calculate(Integer.valueOf(firstString), Integer.valueOf(secondString), parametersRequest.get("type"));
        }
        int i = 0;
// todo сформировать ответ
//        exchange.sendResponseHeaders(codeResponse, bodyResponse.length());
//        OutputStream os = exchange.getResponseBody();
//        os.write(bodyResponse.getBytes());
//        os.close();


// todo сформировать запись в файл

// todo сформировать ответ из всей совокупности операций
    }

    private Response calculate(double num1, double num2, String type) {
        Response response = new Response();
        switch (type) {
            case "sum" -> response.setBodyResponse(String.valueOf(num1 + num2));
            case "diff" -> response.setBodyResponse(String.valueOf(num1 - num2));
            case "mul" -> response.setBodyResponse(String.valueOf(num1 * num2));
            case "div" -> response.setBodyResponse(String.valueOf(num1 / num2));
            case "prc" -> response.setBodyResponse(String.valueOf(num1 * num2 / 100));
            default -> {
                response.setCodeResponse(NOT_FOUND);
                response.setBodyResponse("invalid operation type specified - " + type);
            }
        };
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
}
