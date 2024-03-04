package client.api;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientApi {
    public static void sendAction(String action, ObjectOutputStream oos) throws IOException {
        try {
            oos.writeUTF(action);
            oos.flush();
            System.out.println(action + " sent to server");
        } catch (IOException e) {
            throw new RuntimeException("Error sending action to server", e);
        }
    }
}
