package server.controller;

import server.model.ClientHandler;
import server.model.Server;
import server.views.TerminalView;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TerminalController {
    TerminalView terminal;
    ExecutorService executorService;

    public TerminalController(){
        terminal = new TerminalView();
        executorService = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        TerminalController program = new TerminalController();
        program.run();
    }

    public void run(){
        try {
            ServerSocket serverSocket = Server.waitForConnection(2018);
            while (true) {
                terminal.printString("Waiting for connection...\n");
                Socket socket = serverSocket.accept();
                terminal.printString("Connection from " + socket.getInetAddress() + "\n");
                executorService.submit(new ClientHandler(socket));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
