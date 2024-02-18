package server.model;

import utility.Item;
import utility.ItemOrder;
import utility.User;

import java.io.*;
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
            while(true) {
                String request = oIS.readUTF();
                switch (request) {
                    case "userVerification":
                        //Invoke method for user verification
                        User submittedUser = (User) oIS.readObject();
                        userVerification(submittedUser, objectOutputStream);
                        break;
                    case "createUser":
                        //Invoke method for user creation
                        User userToCreate = (User) oIS.readObject();
//                        User requestBy = (User) oIS.readObject();
                        createUser(userToCreate, objectOutputStream);
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
                    case "changeRole":
                        String currentUsernameCR = oIS.readUTF();
                        String newRole = oIS.readUTF();
                        boolean cRSuccess = XMLProcessing.changeUserRole(currentUsernameCR,newRole);
                        objectOutputStream.writeObject(cRSuccess);
                        objectOutputStream.flush();
                        break;

                    case "fetchListOfUsers":
                        ArrayList<User> listOfUsers = XMLProcessing.fetchListOfUsers();
                        objectOutputStream.writeObject(listOfUsers);
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

    /**
     * Method that handles the authorization of the user this would send the client a boolean value
     *
     * @param userObject        Object of user to be authenticated
     * @param objectOutputStream      Output stream where the status of the auth will be sent
     * @throws IOException
     */
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
