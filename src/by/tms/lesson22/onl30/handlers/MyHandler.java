package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static by.tms.lesson22.onl30.other.Constants.CodeResponse.NOT_FOUND;
import static by.tms.lesson22.onl30.other.Constants.DateTimeFormat.DD_MM_YYYY_SLAG_HH_MM_SS_COLON;
import static by.tms.lesson22.onl30.other.Constants.FormatCsvFile.CSV_FORMAT_TEMPLATE;
import static by.tms.lesson22.onl30.other.Constants.NameFile.CSV_NAME_FILE;
import static by.tms.lesson22.onl30.other.Constants.NumericTemplate.DOUBLE_2_STRING;
import static by.tms.lesson22.onl30.other.Constants.ResultTemplate.ERROR_BODY_TEMPLATE;

public abstract class MyHandler implements HttpHandler {

    protected void exchangeAll(HttpExchange exchange, Response response) throws IOException {
        exchange.sendResponseHeaders(response.getCodeResponse(), response.getBodyResponse().length());
        exchange.getResponseBody().write(response.getBodyResponse().getBytes());
        exchange.getResponseBody().close();
    }

    Response prepareResponseCalculator(double num1, double num2, String typeOperation) {
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

    Response prepareResponseHistory(String[] historyArray) {
        Response response = new Response();
        StringBuilder resultString =  new StringBuilder();
        for (String line : historyArray) {
            String[] lineArray = line.split(";");
            resultString.append(convertSecondsDateTimeString(Long.valueOf(lineArray[0]))).append("\t---\t");
            double firstOperand = Double.valueOf(lineArray[1]);
            double secondOperand = Double.valueOf(lineArray[2]);
            switch (lineArray[3]) {
                case "sum" -> resultString.append(myDoubleString(firstOperand)).append("+").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand + secondOperand)).append("\n");
                case "diff" -> resultString.append(myDoubleString(firstOperand)).append("-").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand - secondOperand)).append("\n");
                case "mul" -> resultString.append(myDoubleString(firstOperand)).append("*").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand * secondOperand)).append("\n");
                case "div" -> resultString.append(myDoubleString(firstOperand)).append("/").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand / secondOperand)).append("\n");
                case "prc" -> resultString.append(myDoubleString(firstOperand)).append("%").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand * secondOperand / 100)).append("\n");
            };
        }
        response.setBodyResponse(resultString.toString());
        return response;
    }

    String convertSecondsDateTimeString(long quantitySeconds){
        Date dateLong = new Date(quantitySeconds);
        Instant instant = dateLong.toInstant();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DD_MM_YYYY_SLAG_HH_MM_SS_COLON);
        return localDateTime.format(dateTimeFormatter);
    }

    protected void writeFile(String firstString, String secondString, String typeOperation) {
        ZonedDateTime zoneDateTime = LocalDateTime.now().atZone(ZoneId.of("Europe/Minsk"));
        String lineFileCsv = String.format(CSV_FORMAT_TEMPLATE, zoneDateTime.toInstant().toEpochMilli(),
                firstString, secondString, typeOperation);
        try {
            Files.write(Paths.get(CSV_NAME_FILE), lineFileCsv.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }

    static boolean isNotNumber(String stringAsNumber) {
        try {
            Integer.valueOf(stringAsNumber);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    String myDoubleString(double d){
        return String.format(DOUBLE_2_STRING,d);
    }
}