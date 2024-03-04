package client.common.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SessionTimeout {

    /**
     * Sends a session timeout request to the server for the specified username.
     *
     * @param username The username for which the session timeout request is made.
     * @param oOs      The ObjectOutputStream for sending data to the server.
     * @param oIs      The ObjectInputStream for receiving data from the server.
     * @throws RuntimeException If an IOException occurs during the process.
     */

    public static void process(String username, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            String action = "sessionTimeout";
            oOs.writeUTF(action);
            oOs.flush();
            System.out.println(action + "sent to the server");

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
