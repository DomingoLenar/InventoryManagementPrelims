package client.common.models;

import client.api.ClientApi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserSettingsModel {
    public void sessionTimeout(String username, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            ClientApi.sendAction("sessionTimeout", oOs);

            oOs.writeUTF(username);
            oOs.flush();

            System.out.println(username + "session timeout request has been sent to the server...");

            boolean logout = oIs.readBoolean();
            System.out.println("Server response: " + logout);

        } catch (IOException e) {
            throw new RuntimeException("Error logging out ", e);
        }
    }
}
