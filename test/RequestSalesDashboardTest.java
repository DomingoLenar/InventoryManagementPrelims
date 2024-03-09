import org.junit.jupiter.api.Test;
import utility.revision.Item;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class RequestSalesDashboardTest {

    @Test
    public void testProcessMethod() {
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectOutputStream oOS = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream oIS = new ObjectInputStream(cSocket.getInputStream());

            oOS.writeUTF("requestSalesDashboard");
            oOS.flush();

            ArrayList<String> yearlyRevenueCogs = (ArrayList<String>) oIS.readObject();
            float[] revenueAndCost = (float[]) oIS.readObject();
            ArrayList<Item> recentlyAddedItems = (ArrayList<Item>) oIS.readObject();
            String data = oIS.readUTF();


            assertNotNull(revenueAndCost);
            assertNotNull(recentlyAddedItems);
            assertNotNull(yearlyRevenueCogs);

            System.out.println("Revenue and Cost for today: " + revenueAndCost[0] + ", " + revenueAndCost[1]);
            System.out.println("Recently added items: " + recentlyAddedItems);
            System.out.println("Yearly revenue and costs: " + yearlyRevenueCogs);

            cSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred: " + e.getMessage());
        }
    }


}
