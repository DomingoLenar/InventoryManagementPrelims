package server.model;

import server.model.query.*;

import utility.User;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

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
                    case "createSalesInvoice":
                        CreateSalesInvoice.process((ItemOrder) oIS.readObject(), (OrderDetails) oIS.readObject(), objectOutputStream);
                        break;
                    case"addItem":
                        AddItem.process((ItemOrder) oIS.readObject(),  (OrderDetails) oIS.readObject(), objectOutputStream);
                        break;
                    case "addItemListing":
                        //Invoke method for item addition
                        Item submittedItem = (Item) oIS.readObject();
                        ItemListing.process(submittedItem, objectOutputStream);
                        break;
                    case "removeItem":
                        //Invoke method for item removal
                        ItemRemoval.process(oIS.readInt(), objectOutputStream);
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

                        break;
                    case "removeItemOrder":
                        RemoveItemOrder.process(oIS.readInt(), objectOutputStream);
                        break;
                    case "changePassword":
                        ChangePassword.process(oIS, objectOutputStream);
                        break;
                    case "changeRole":
                        String currentUsernameCR = oIS.readUTF();
                        String newRole = oIS.readUTF();
                        boolean cRSuccess = XMLProcessing.changeUserRole(currentUsernameCR,newRole);
                        objectOutputStream.writeObject(cRSuccess);
                        objectOutputStream.flush();
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
                    case "requestSalesDashboard":
                        RequestSalesDashboard.process(objectOutputStream);
                        break;
                    case "requestPurchaseDashboard":
                        RequestPurchaseDashboard.process(objectOutputStream);
                        break;
                    case "requestAdminDashboard":
                        RequestAdminDashboard.process(objectOutputStream);
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

}
