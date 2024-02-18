import client.common.models.ProfileManagementModel;
import org.junit.jupiter.api.Assertions;
import server.model.XMLProcessing;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
    public void testClientServerConnection() {
        try {
            Socket cSocket = new Socket("localhost", 2018);
            String userType = ProfileManagementModel.handleLogin("testsales", "salestest", cSocket);

            if (userType != null) {
                System.out.println("login success");
            } else {
                System.out.println("failed");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    public void testContinuousOperations() {
        try {
            Socket cSocket = new Socket("localhost", 2018);
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
}
