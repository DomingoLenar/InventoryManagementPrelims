package client.common.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChangePassword {

    /**
     * Method for changing a password of a user
     *
     * @param userName    The username of the user whose password is to be changed.
     * @param newPassword The new password to set for the user.
     * @return True if the password change was successful, false otherwise.
     * @throws RuntimeException If an error occurs while changing the password.
     */
    public static void process(String userName, String newPassword, String oldPassword, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            String action = "changePassword";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

            oOs.writeUTF(userName);
            oOs.writeUTF(newPassword);
            oOs.writeUTF(oldPassword);
            oOs.flush();

            System.out.println("Password change request has been sent to the server...");


        } catch (IOException e) {
            throw new RuntimeException("Error changing password", e);
        }
    }
}
