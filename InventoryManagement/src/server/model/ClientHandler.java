package server.model;

import server.views.TerminalView;
import utility.User;

import java.io.*;
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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pWriter = new PrintWriter(outputStream, true);

            pWriter.println("You are connected to the server");
//            System.out.println(bufferedReader..());
            while(true) {
                pWriter.print("Operation:");
                switch (bufferedReader.readLine()) {
                    //To be populated with actual logic
                    case "userVerification":
                        //Invoke method for user verification
                        User submittedUser = (User) oIS.readObject();
                        userVerification(submittedUser, outputStream);
                        break;
                    case "createUser":
                        //Invoke method for user creation
                        User userToCreate = (User) oIS.readObject();
                        createUser(userToCreate, outputStream);
                        break;
                    case "Exit":
                        socket.close();
                        break;
                    default:
                        System.out.println("Invalid request");
                }


            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void userVerification(User userObject, OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        boolean auth = XMLProcessing.authenticate(userObject);
//        objectOutputStream.writeBoolean(auth);
        objectOutputStream.writeObject(auth);
    }

    public void createUser(User userObject, OutputStream outputStream){
        try{
           ObjectOutputStream oOS = new ObjectOutputStream(outputStream);
           //call XMLProcessing method to update the xml file
            boolean succeed = XMLProcessing.createUser(userObject);
            oOS.writeBoolean(succeed);
        }catch(IOException ioException){
            throw new RuntimeException(ioException);
        }
    }

}
