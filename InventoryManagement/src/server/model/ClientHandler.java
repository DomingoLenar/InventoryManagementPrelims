package server.model;

import utility.Item;
import utility.ItemOrder;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    Socket socket;

    public ClientHandler(Socket clientSocket){
        socket = clientSocket;
    }

    /**
     *
     */
    @Override
    public void run() {
        try(
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
                ){
            String request;
            while((request = oIS.readUTF())!=null) {
                switch (request) {
                    case "userVerification":
                        //Invoke method for user verification
                        User submittedUser = (User) oIS.readObject();
                        userVerification(submittedUser, objectOutputStream);
                        break;
                    case "createUser":
                        //Invoke method for user creation
                        User userToCreate = (User) oIS.readObject();
                        User requestBy = (User) oIS.readObject();
                        createUser(userToCreate, requestBy, objectOutputStream);
                        break;
                    case "additem":
                        //Invoke method for item addition
                        Item submittedItem = (Item) oIS.readObject();
                        itemAddition(submittedItem, objectOutputStream);
                        break;
                    case "removeitem":
                        //Invoke method for item removal
                        int submittedID =  oIS.readInt();
                        itemRemoval(submittedID, objectOutputStream);
                        break;
                    case "fetchItems":
                        ArrayList<Item> items = XMLProcessing.fetchItems();
                        objectOutputStream.writeObject(items);
                        objectOutputStream.flush();
                        break;
                    case "fetchItemOrders":
                        String date = oIS.readUTF();
                        ArrayList<ItemOrder> itemOrderList = XMLProcessing.fetchItemOrders(date);
                        objectOutputStream.writeObject(itemOrderList);
                        objectOutputStream.flush();
                        break;
                    case "addItemOrder":
                        ItemOrder newItemOrder = (ItemOrder) oIS.readObject();
                        boolean success = XMLProcessing.addItemOrder(newItemOrder);
                        objectOutputStream.writeObject(success);
                        objectOutputStream.flush();
                        break;
                    case "changePassword":
                        String currentUsername = oIS.readUTF();
                        String newPassword = oIS.readUTF();
                        boolean cPSuccess = XMLProcessing.changePassword(currentUsername,newPassword);
                        objectOutputStream.writeObject(cPSuccess);
                        objectOutputStream.flush();
                        break;
                    case "Exit":
                        System.out.println("Exit");
                        socket.close();
                        break;
                    default:
                        System.out.println("Invalid request");
                }

            }
        }catch(StreamCorruptedException sCE){
            if(sCE.getMessage().equals("invalid type code: 45")){
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method that handles the authorization of the user this would send the client a boolean value
     *
     * @param userObject        Object of user to be authenticated
     * @param objectOutputStream      Output stream where the status of the auth will be sent
     * @throws IOException
     */
    public void userVerification(User userObject, ObjectOutputStream objectOutputStream) throws IOException {
        boolean auth = XMLProcessing.authenticate(userObject);
        objectOutputStream.writeBoolean(auth);
        //objectOutputStream.writeObject(auth);
        objectOutputStream.flush();
    }

    /**
     * Method that handles the creation of user
     *
     * @param userObject        User object to be created in the server
     * @param requestBy         User that performed the query
     * @param objectOutputStream               Object of ObjectOutputStream
     */
    public synchronized void createUser(User userObject, User requestBy, ObjectOutputStream objectOutputStream){
        try{
           //call XMLProcessing method to update the xml file
            boolean succeed = XMLProcessing.createUser(userObject);
            objectOutputStream.writeBoolean(succeed);
            objectOutputStream.flush();
        }catch(IOException ioException){
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Handles the addition of an item to the server.
     *
     * @param itemObject    The item object to be added.
     * @param objectOutputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    private synchronized void itemAddition(Item itemObject, ObjectOutputStream objectOutputStream) throws IOException {
        try {

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
     * @param objectOutputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    private synchronized void itemRemoval(int id, ObjectOutputStream objectOutputStream) throws IOException {
        try {

            boolean success = XMLProcessing.removeItem(id);

            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
