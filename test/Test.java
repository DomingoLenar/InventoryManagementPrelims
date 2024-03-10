import client.api.ClientApi;
import org.junit.jupiter.api.Assertions;
import server.model.XMLProcessing;
import utility.User;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Stack;

public class Test {
    @org.junit.jupiter.api.Test
    public void testCreateUser(){
        User user = new User("lou", "lou", "purchase", true);
        boolean valid = XMLProcessing.createUser(user).isActive();
        Assertions.assertEquals(true, valid);
    }

    @org.junit.jupiter.api.Test
    public void testFindUser(){
        User user = XMLProcessing.findUser("testsales");
        assert user != null;
//        Assertions.assertEquals("testsales", user.getUsername());
        Assertions.assertEquals("salestest", user.getPassword());
    }

    @org.junit.jupiter.api.Test
    public void testAuthenticate(){
        User user = new User("testname", "testpass", "purchase", false);
        User userType = XMLProcessing.authenticate(user);
//        Assertions.assertEquals(true, valid);
    }

    @org.junit.jupiter.api.Test
    public void testContinuousOperations() {
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectOutputStream oos  = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cSocket.getInputStream());

            oos.writeUTF("fetchListOfUsers");
            oos.flush();
            ArrayList<User> userList = (ArrayList<User>) ois.readObject();

            oos.writeUTF("fetchListOfUsers");
            oos.flush();
            ArrayList<User> userList2 = (ArrayList<User>) ois.readObject();

            System.out.println(userList2.size());


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    public void testFetchItems(){
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectOutputStream oos  = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(cSocket.getInputStream());

            ClientApi.sendAction("fetchItems", oos);

            try {
                Stack<Item> itemStack = (Stack<Item>) ois.readObject();
                Item item = null;
                for (int i=0; i< itemStack.size(); i++) {
                    item = itemStack.get(i);
                    System.out.println("Item " + i + item.getName());
                    System.out.println(item.getStock(0).toString());

                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException ioException) {
            throw new RuntimeException("Error Fetching Items", ioException);
        }

    }
}
