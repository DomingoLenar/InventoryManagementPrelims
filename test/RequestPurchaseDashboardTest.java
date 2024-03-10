import org.junit.jupiter.api.Test;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestPurchaseDashboardTest {

    @Test
    public void testProcessMethod() throws RuntimeException {
        try {
            Socket cSocket = new Socket("localhost",2020);
            ObjectOutputStream oOs = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream oIs = new ObjectInputStream(cSocket.getInputStream());

            oOs.writeUTF("requestPurchaseDashboard");
            oOs.flush();

            ArrayList<Item> lowStockItems = (ArrayList<Item>) oIs.readObject();
            int[] unitsSold = (int[]) oIs.readObject();

            assertNotNull(lowStockItems);
            assertNotNull(unitsSold);

            System.out.println("Units sold: "+ unitsSold[0] + unitsSold[1]);
            System.out.println("Low stock items: "+ lowStockItems);

            cSocket.close();


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
