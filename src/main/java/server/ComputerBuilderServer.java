package server;

import com.sun.net.httpserver.HttpServer;
import handler.*;

import java.net.InetSocketAddress;

public class ComputerBuilderServer {

    public void main(int port) throws Exception {
        InetSocketAddress serverAddress = new InetSocketAddress(port);
        HttpServer server = HttpServer.create(serverAddress, 10);
        registerHandlers(server);
        server.start();
        System.out.println("FamilyMapServer listening on port " + port);
    }
    private void registerHandlers(HttpServer server) {
        //server.createContext("/user/register", new Register_Request_Handler());
        server.createContext("/user/login", new LoginRequestHandler());
    }
}