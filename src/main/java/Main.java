import server.ComputerBuilderServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, Exception {
        ComputerBuilderServer cbs = new ComputerBuilderServer();
        cbs.main(8080);
    }
}