package by.tms.lesson22.onl30;

import by.tms.lesson22.onl30.handlers.CalculatorHandler;
import by.tms.lesson22.onl30.handlers.HistoryHandler;
import by.tms.lesson22.onl30.handlers.TestHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static by.tms.lesson22.onl30.other.Constants.Paths.*;
import static by.tms.lesson22.onl30.other.Constants.Ports.JAVA_PORT;

public class Launcher {

    public static void main(String[] args) throws IOException {
        HttpServer localServer = HttpServer.create(new InetSocketAddress(JAVA_PORT), 0);
        localServer.createContext(TEST, new TestHandler());
        localServer.createContext(CALCULATOR, new CalculatorHandler());
        localServer.createContext(HISTORY, new HistoryHandler());
        localServer.start();
    }
}