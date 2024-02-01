package server.model;

import server.views.TerminalView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ClientHandler implements Runnable{
    Socket socket;
    String operation;

    public ClientHandler(Socket clientSocket){
        socket = clientSocket;
    }

    @Override
    public void run() {
        try{
            //To be replaced by proper logic for the application this will just act as a temporary test
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pWriter = new PrintWriter(outputStream);

            pWriter.println("You are connected to the server");
            pWriter.print("Operation:");
            pWriter.flush();
            switch(bufferedReader.readLine().toLowerCase()){
                case "login":
                    operation = "login";
                    break;
                case "exit":
                    operation = "exit";
                    socket.close();
            }

            System.out.println(bufferedReader.readLine());  //This would be replaced by a callable

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
