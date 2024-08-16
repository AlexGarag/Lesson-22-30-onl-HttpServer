import by.tms.lesson22.onl30.handlers.CalculatorHandler;
import by.tms.lesson22.onl30.handlers.TestHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Launcher {
    public static void main(String[] args) throws IOException {
        HttpServer localServer = HttpServer.create(new InetSocketAddress(8080), 0);
//        localServer.createContext("/", new TestHandler());
        localServer.createContext("/test", new CalculatorHandler());
        localServer.start();
    }
}