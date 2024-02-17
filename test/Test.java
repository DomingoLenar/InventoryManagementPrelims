import client.models.ProfileManagementModel;
import org.junit.jupiter.api.Assertions;
import server.model.XMLProcessing;
import utility.User;

import java.io.IOException;
import java.net.Socket;

public class Test {
    @org.junit.jupiter.api.Test
    public void testCreateUser(){
        User user = new User("lou", "lou", "purchase", true);
        boolean valid = XMLProcessing.createUser(user);
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
    public void testAuthenticate(){ // TODO: returns null value
        User user = new User("testname", "testpass", "purchase", false);
        boolean valid = XMLProcessing.authenticate(user);
        Assertions.assertEquals(true, valid);
//        Assertions.assertEquals(true, valid);
    }

    @org.junit.jupiter.api.Test
    public void testClientServerConnection() {
        try {
            Socket sSocket = new Socket("localhost", 2018);
            boolean valid = ProfileManagementModel.handleLogin("testuser", "usertest", sSocket);

            if (valid) {
                System.out.println("login success");
            } else {
                System.out.println("failed");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
