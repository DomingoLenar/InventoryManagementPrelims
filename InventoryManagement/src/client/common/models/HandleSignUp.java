package client.common.models;

import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HandleSignUp {
    /**
     * Handles the signup process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param role The user's role
     * @return True if account creation is successful, false otherwise.
     */

    public static User process(String username, String password, String role, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            // Create a new User object with provided credentials
            User newUser = new User(username, password,role,false);

            String action = "createUser";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            // Send the User object to the server for sign-up
            oOs.writeObject(newUser);
            oOs.flush();
            System.out.println(newUser.getUsername() + " sent to the server for sign-up");

            try {
                User user = (User) oIs.readObject();
                if (user != null) {
                    System.out.println("Authentication response: success");
                    return user;
                } else {
                    System.out.println("Authentication response: failed");
                    return null;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
