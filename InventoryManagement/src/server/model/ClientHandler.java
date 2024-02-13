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

    /**
     *
     */
    @Override
    public void run() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pWriter = new PrintWriter(outputStream, true);

            pWriter.println("You are connected to the server");
            while(true) {
                pWriter.print("Operation:");
                switch (bufferedReader.readLine()) {
                    case "userVerification":
                        //Invoke method for user verification
                        User submittedUser = (User) oIS.readObject();
                        userVerification(submittedUser, outputStream);
                        break;
                    case "createUser":
                        //Invoke method for user creation
                        User userToCreate = (User) oIS.readObject();
                        User requestBy = (User) oIS.readObject();
                        createUser(userToCreate, requestBy, outputStream);
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

    /**
     * Method that handles the authorization of the user this would send the client a boolean value
     *
     * @param userObject        Object of user to be authenticated
     * @param outputStream      Output stream where the status of the auth will be sent
     * @throws IOException
     */
    public void userVerification(User userObject, OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        boolean auth = XMLProcessing.authenticate(userObject);
        objectOutputStream.writeBoolean(auth);
        //objectOutputStream.writeObject(auth);
    }

    /**
     * Method that handles the creation of user
     *
     * @param userObject        User object to be created in the server
     * @param requestBy         User that performed the query
     * @param outputStream      Object of outputstream
     */
    public void createUser(User userObject, User requestBy, OutputStream outputStream){
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
