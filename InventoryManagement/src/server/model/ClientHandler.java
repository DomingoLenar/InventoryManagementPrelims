package server.model;

import server.views.TerminalView;
import utility.Item;
import utility.ItemOrder;
import utility.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
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
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

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
                    case "additem":
                        //Invoke method for item addition
                        Item submittedItem = (Item) oIS.readObject();
                        itemAddition(submittedItem, outputStream);
                        break;

                    case "removeitem":
                        //Invoke method for item removal
                        int submittedID =  oIS.readInt();
                        itemRemoval(submittedID, outputStream);
                        break;
                    case "fetchItems":
                        ArrayList<Item> items = XMLProcessing.fetchItems();
                        objectOutputStream.writeObject(items);
                        objectOutputStream.flush();
                        break;
                    case "fetchItemOrders":
                        String date = bufferedReader.readLine();
                        ArrayList<ItemOrder> itemOrderList = XMLProcessing.fetchItemOrders(date);
                        objectOutputStream.writeObject(itemOrderList);
                        objectOutputStream.flush();
                        break;
                    case "addItemOrder":
                        ItemOrder newItemOrder = (ItemOrder) oIS.readObject();
                        boolean success = XMLProcessing.addItemOrder(newItemOrder);
                        objectOutputStream.writeObject(success);
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
