package by.tms.lesson22.onl30.handlers;

import by.tms.lesson22.onl30.other.ResponseCalculator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static by.tms.lesson22.onl30.other.Constants.DateTimeFormat.DD_MM_YYYY_SLAG_HH_MM_SS_COLON;
import static by.tms.lesson22.onl30.other.Constants.NameFile.CSV_NAME_FILE;

public final class HistoryHandler extends MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String historyString =  new String(Files.readAllBytes(Paths.get(CSV_NAME_FILE)), StandardCharsets.UTF_8);
        String[] historyArray = historyString.split("\n");
        ResponseCalculator response = new ResponseCalculator();
        StringBuilder resultString =  new StringBuilder();
        for (String line : historyArray) {
            String[] lineArray = line.split(";");
            resultString.append(getDateTimeString(Long.valueOf(lineArray[0]))).append("\t---\t\t");
            double firstOperand = Double.valueOf(lineArray[1]);
            double secondOperand = Double.valueOf(lineArray[2]);
            switch (lineArray[3]) {
                case "sum" -> resultString.append(myDoubleString(firstOperand)).append("+").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand + secondOperand)).append("\n");
                case "diff" -> resultString.append(myDoubleString(firstOperand)).append("-").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand - secondOperand)).append("\n");
                case "mul" -> resultString.append(myDoubleString(firstOperand)).append("+").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand * secondOperand)).append("\n");
                case "div" -> resultString.append(myDoubleString(firstOperand)).append("+").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand / secondOperand)).append("\n");
                case "prc" -> resultString.append(myDoubleString(firstOperand)).append("+").append(myDoubleString(secondOperand)).append("=")
                        .append(myDoubleString(firstOperand * secondOperand / 100)).append("\n");
            };
 int r = 0;
        }
        response.setBodyResponse(resultString.toString());
        exchangeAll(exchange, response);
    }

    private String getDateTimeString(long ldate){
        Date dateLong = new Date(ldate);
        Instant instant = dateLong.toInstant();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DD_MM_YYYY_SLAG_HH_MM_SS_COLON);
        return localDateTime.format(dateTimeFormatter);
    }

    private String myDoubleString(double d){
        return String.format("%.2f",d);
    }
}
