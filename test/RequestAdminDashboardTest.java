import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestAdminDashboardTest {
    @Test
    public void testProcessMethod(){
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectInputStream oIs = new ObjectInputStream(cSocket.getInputStream());
            ObjectOutputStream oOs = new ObjectOutputStream(cSocket.getOutputStream());

            oOs.writeUTF("requestAdminDashboard");
            oOs.flush();

            ArrayList<String> users = (ArrayList<String>) oIs.readObject();
            int[] unitsSold = (int[]) oIs.readObject();
            ArrayList<String> yearlyRevenueCogs = (ArrayList<String>) oIs.readObject();

            assertNotNull(users);
            assertNotNull(unitsSold);
            assertNotNull(yearlyRevenueCogs);

            System.out.println("Users: "+ users);
            System.out.println("Units sold: "+ unitsSold[0] + unitsSold[1]);
            System.out.println("Yearly revenue and costs: " + yearlyRevenueCogs);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
