package client.common.models;

import client.api.ClientApi;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginModel {
    private User user;

    public User getUser() {
        return user;
    }

    public LoginModel(){}
    public void handleLogin(String username, String password, ObjectOutputStream oOs, ObjectInputStream oIs) {
        // Create a new User object with provided credentials
        User currentUser = new User(username, password, null, false);

        try {
            ClientApi.sendAction("userVerification", oOs);

            // Send the User object to the server for login
            oOs.writeObject(currentUser);
            try {
                this.user = (User) oIs.readObject();
                if (user != null) {
                    System.out.println("Authentication response: success");
                } else {
                    System.out.println("Authentication response: failed");
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
