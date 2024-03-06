package client.common.models;

import utility.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class FetchItemsByUserType {

    /**
     * Sends a request to the server to fetch items based on user type.
     *
     * @param userType The type of user for whom the items are to be fetched.
     * @param oOs      The ObjectOutputStream for sending data to the server.
     * @param oIs      The ObjectInputStream for receiving data from the server.
     * @return A stack containing the list of items fetched from the server.
     * @throws RuntimeException If an IOException or ClassNotFoundException occurs during the process.
     */

    public static Stack<Item> process(String userType, ObjectOutputStream oOs, ObjectInputStream oIs){
        try {
            String action = "fetchItems";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");


            try  {
                Stack<Item> listOfItems = (Stack<Item>) oIs.readObject();
                System.out.println("List of items have been fetched.");
                return listOfItems;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
