package server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static ServerSocket waitForConnection(int port){
        try{
           ServerSocket serverSocket = new ServerSocket(port);
           return serverSocket;
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
        return null;
    }
}
