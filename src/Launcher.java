import by.tms.lesson22.onl30.handlers.CalculatorHandler;
import by.tms.lesson22.onl30.handlers.HistoryHandler;
import by.tms.lesson22.onl30.handlers.TestHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
    public static void main(String[] args) throws IOException {
        HttpServer localServer = HttpServer.create(new InetSocketAddress(8080), 0);
        localServer.createContext("/test", new TestHandler());
        localServer.createContext("/calculator", new CalculatorHandler());
        localServer.createContext("/history", new HistoryHandler());
        localServer.start();
    }
}