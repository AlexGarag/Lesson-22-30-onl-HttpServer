package by.tms.lesson22.onl30.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static by.tms.lesson22.onl30.handlers.CodeResponse.NOT_FOUND;
import static by.tms.lesson22.onl30.handlers.CodeResponse.OK;

public class CalculatorHandler implements HttpHandler {
    private static final String RESULT_TEMPLATE = "result: %s";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = exchange.getRequestURI().getQuery(); //num1=2&num2=2&type=sum
        Map<String, String> parametersRequest = parseRequest(request);
// todo сформировать ответ, если не число в параметре
        double parameterFirst = Integer.valueOf(parametersRequest.get("num1"));
        double parameterSecond = Integer.valueOf(parametersRequest.get("num2"));
        Map<Integer, String> result = calculate(parameterFirst, parameterSecond, parametersRequest.get("type"));
        String bodyResponse = String
                .format(RESULT_TEMPLATE, result.get(result.size() - 1));

        int i = 0;
// todo сформировать ответ
//        exchange.sendResponseHeaders(codeResponse, bodyResponse.length());
//        OutputStream os = exchange.getResponseBody();
//        os.write(bodyResponse.getBytes());
//        os.close();


// todo сформировать запись в файл

// todo сформировать ответ из всей совокупности операций
    }

    private static Map<Integer, String> calculate(double num1, double num2, String type) {
        Map<Integer, String> resultOperation = new HashMap<>();
        int codeResponse = OK;
        String result = switch (type) {
            case "sum" -> String.valueOf(num1 + num2);
            case "diff" -> String.valueOf(num1 - num2);
            case "mul" -> String.valueOf(num1 * num2);
            case "div" -> String.valueOf(num1 / num2);
            case "prc" -> String.valueOf(num1 * num2 / 100);
            default -> {
                codeResponse = NOT_FOUND;
                yield "invalid operation type specified - " + type;
            }
        };
        resultOperation.put(codeResponse, result);
        return resultOperation;
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
