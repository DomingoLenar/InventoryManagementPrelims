package client.common.models.query;

import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HandleLogin {
    /**
     * Handles the login process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return True if login is successful, false otherwise.
     */

    public static String process(String username, String password, ObjectOutputStream oOs, ObjectInputStream oIs) {
        // Create a new User object with provided credentials
        User currentUser = new User(username, password, null, false);

        try {
            String action = "userVerification";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            // Send the User object to the server for login
            oOs.writeObject(currentUser);
            System.out.println(currentUser.getUsername() + " sent to the server for login");

            try {
                User user = (User) oIs.readObject();
                if (user != null) {
                    System.out.println("Authentication response: success");
                    return user.getRole().toLowerCase();
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
