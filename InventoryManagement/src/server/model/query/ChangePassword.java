package server.model.query;

import server.model.XMLProcessing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class for to call for changing user password
 */
public class ChangePassword {
    public static void process(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException {
        String currentUsername = objectInputStream.readUTF();
        String newPassword = objectInputStream.readUTF();
        String oldPassword = objectInputStream.readUTF();
        boolean cPSuccess = XMLProcessing.changePassword(currentUsername,newPassword, oldPassword);
        objectOutputStream.writeBoolean(cPSuccess);
        objectOutputStream.flush();
    }
}
