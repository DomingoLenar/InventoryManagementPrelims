package server.model;

import server.views.TerminalView;
import utility.Item;
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
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pWriter = new PrintWriter(outputStream);

            pWriter.println("You are connected to the server");
            while(true) {
                pWriter.print("Operation:");
                pWriter.flush();
                switch (bufferedReader.readLine().toLowerCase()) {
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
                    case "additem":
                        //Invoke method for item addition
                        Item submittedItem = (Item) oIS.readObject();
                        itemAddition(submittedItem, outputStream);
                        break;

                    case "removeitem":
                        //Invoke method for item removal
                        int submittedID = (int) oIS.readInt();
                        itemRemoval(submittedID, outputStream);
                        break;

                    case "Exit":
                        socket.close();
                }


            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void userVerification(User userObject, OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        boolean auth = XMLProcessing.authenticate(userObject);
        objectOutputStream.writeBoolean(auth);
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

    /**
     * Handles the addition of an item to the server.
     *
     * @param itemObject    The item object to be added.
     * @param outputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    private void itemAddition(Item itemObject, OutputStream outputStream) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            boolean success = XMLProcessing.addItem(itemObject);

            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the removal of an item from the server.
     *
     * @param id            The ID of the item to be removed.
     * @param outputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    private void itemRemoval(int id, OutputStream outputStream) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            boolean success = XMLProcessing.removeItem(id);

            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
