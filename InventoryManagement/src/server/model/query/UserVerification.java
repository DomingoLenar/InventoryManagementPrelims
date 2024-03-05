package server.model.query;

import server.model.XMLProcessing;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserVerification {
    public static void process(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        User userToVerify = (User) objectInputStream.readObject();
        User localUser = XMLProcessing.authenticate(userToVerify);
        objectOutputStream.writeObject(localUser);
        objectOutputStream.flush();
    }
}
