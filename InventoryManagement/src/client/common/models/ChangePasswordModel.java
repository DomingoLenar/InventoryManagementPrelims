package client.common.models;

import client.api.ClientApi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChangePasswordModel {
    public ChangePasswordModel(){}
    public boolean changePassword(String userName, String newPassword, String oldPassword, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            ClientApi.sendAction("changePassword", oOs);

            oOs.writeUTF(userName);
            oOs.writeUTF(newPassword);
            oOs.writeUTF(oldPassword);
            oOs.flush();

            System.out.println("Password change request has been sent to the server...");

            try {
                boolean changeSuccess = oIs.readBoolean();
                System.out.println("Server response: " + changeSuccess);
                return changeSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error changing password", e);
        }
    }
}
