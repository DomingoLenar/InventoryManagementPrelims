package client.common.models;

import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class FetchListOfUsers {

    /**
     * Sends a request to the server to fetch a list of users.
     *
     * @param oOs The ObjectOutputStream for sending data to the server.
     * @param oIs The ObjectInputStream for receiving data from the server.
     * @return A stack containing the list of users fetched from the server.
     * @throws RuntimeException If an IOException or ClassNotFoundException occurs during the process.
     */
    public static Stack<User> process (ObjectOutputStream oOs, ObjectInputStream oIs){
        try {
            String action = "fetchListOfUsers";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");


            try  {
                Stack<User> listOfUsers = (Stack<User>) oIs.readObject();
                System.out.println("List of users have been fetched.");
                return listOfUsers;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
