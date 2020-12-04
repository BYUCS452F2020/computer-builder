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
        System.out.println("ComputerBuilderServer listening on port " + port);
    }
    private void registerHandlers(HttpServer server) {
        server.createContext("/user/register", new RegisterRequestHandler());
        server.createContext("/user/login", new LoginRequestHandler());
        server.createContext("/component/", new ComponentsRequestHandler());
        server.createContext("/one_component/", new SingleComponentRequestHandler());
        server.createContext("/builds/insert", new SaveBuildHandler());
        server.createContext("/build/", new UserBuildsRequestHandler());
    }
}