package server.model.query;

import server.model.XMLProcessing;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CreateUser{
    public static void process(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        User userToCreate = (User) objectInputStream.readObject();
        User createdUser = XMLProcessing.createUser(userToCreate);
        objectOutputStream.writeObject(createdUser);
        objectOutputStream.flush();
    }
}
