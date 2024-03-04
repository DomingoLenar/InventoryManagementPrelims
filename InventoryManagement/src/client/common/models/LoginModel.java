package client.common.models;

import client.api.ClientApi;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginModel {
    public LoginModel(){}
    public String handleLogin(String username, String password, ObjectOutputStream oOs, ObjectInputStream oIs) {
        // Create a new User object with provided credentials
        User currentUser = new User(username, password, null, false);

        try {
            ClientApi.sendAction("userVerification", oOs);

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
