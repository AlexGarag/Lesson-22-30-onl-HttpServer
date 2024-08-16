package by.tms.lesson22.onl30.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CalculatorHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String request = exchange.getRequestURI().getQuery(); //num1=2&num2=2&type=sum
        Map<String, String> parametersRequest = parseRequest(request);
        double parameterFirst = Integer.valueOf(parametersRequest.get("num1"));
        double parameterSecond = Integer.valueOf(parametersRequest.get("num2"));
        double result = 0.0;
        result = switch (parametersRequest.get("type")) {
            case "sum" -> parameterFirst + parameterSecond;
            case "diff" -> parameterFirst - parameterSecond;
            case "mul" -> parameterFirst * parameterSecond;
            case "div" -> parameterFirst / parameterSecond;
            case "prc" -> parameterFirst * parameterSecond / 100;
            default -> throw new IllegalStateException("Unexpected value: " + parametersRequest.get("type"));
        };
        int i = 0;
    }

    public static Map<String, String> parseRequest(String request) {
        String[] params = request.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] keyValue = param.split("=");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}
