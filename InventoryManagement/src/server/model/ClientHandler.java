package server.model;

import server.model.query.*;

import utility.Item;
import utility.ItemOrder;
import utility.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

public class ClientHandler implements Runnable{
    Socket socket;

    public ClientHandler(Socket clientSocket){
        socket = clientSocket;
    }

    /**
     *
     */

    // TODO:
    @Override
    public void run() {
        try(
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
                ){
            while(true) {
                String request = oIS.readUTF();
                switch (request) {
                    case "userVerification":
                        //Invoke method for user verification
                        UserVerification.process(oIS, objectOutputStream);
                        break;
                    case "createUser":
                        //Invoke method for user creation
                        CreateUser.process(oIS, objectOutputStream);
                        break;
                    case "addItem":
                        //Invoke method for item addition
                        Item submittedItem = (Item) oIS.readObject();
                        itemAddition(submittedItem, objectOutputStream);
                        break;
                    case "removeItem":
                        //Invoke method for item removal
                        itemRemoval(oIS.readInt(), objectOutputStream);
                        break;
                    case "fetchItems":
                        Stack<Item> items = XMLProcessing.fetchItems();
                        objectOutputStream.writeObject(items);
                        objectOutputStream.flush();
                        break;
                    case "fetchItemOrders":
                        ArrayList<ItemOrder> itemOrderList = XMLProcessing.fetchItemOrders(oIS.readUTF());
                        objectOutputStream.writeObject(itemOrderList);
                        objectOutputStream.flush();
                        break;
                    case "addItemOrder":
                        ItemOrder newItemOrder = (ItemOrder) oIS.readObject();
                        boolean success = XMLProcessing.addItemOrder(newItemOrder);
                        objectOutputStream.writeBoolean(success);
                        objectOutputStream.flush();
                        break;
                    case "removeItemOrder":
                        itemOrderRemoval(oIS.readInt(), objectOutputStream);
                        break;
                    case "changePassword":
                        ChangePassword.process(oIS, objectOutputStream);
                        break;
                    case "fetchListOfUsers":
                        Stack<User> listOfUsers = XMLProcessing.fetchListOfUsers();
                        objectOutputStream.writeObject(listOfUsers);
                        objectOutputStream.flush();
                        break;
                    case "sessionTimeout":
                        String username = oIS.readUTF();
                        User currentUser = XMLProcessing.findUser(username);
                        XMLProcessing.setActiveStatus(currentUser, false);
                        objectOutputStream.writeBoolean(currentUser != null);
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
        }catch(EOFException endFile){
            System.out.println(endFile.getMessage());
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

    private void itemOrderRemoval(int itemOrderID, ObjectOutputStream objectOutputStream) {
        try{
            boolean success = XMLProcessing.removeItemOrder(itemOrderID);
            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException ioException){
            throw new RuntimeException("Error removing item order", ioException);
        }
    }

    /**
     * Method that handles the authorization of the user this would send the client a boolean value
     *
     * @param userObject        Object of user to be authenticated
     * @param objectOutputStream      Output stream where the status of the auth will be sent
     * @throws IOException
     */

    @Deprecated
    public void userVerification(User userObject, ObjectOutputStream objectOutputStream) throws IOException {
        User user = XMLProcessing.authenticate(userObject);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
    }

    /**
     * Method that handles the creation of user
     *
     * @param userObject        User object to be created in the server
     * @param objectOutputStream               Object of ObjectOutputStream
     */
    public synchronized void createUser(User userObject, ObjectOutputStream objectOutputStream){
        try{
           //call XMLProcessing method to update the xml file
            User user = XMLProcessing.createUser(userObject);
            objectOutputStream.writeObject(user);
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
