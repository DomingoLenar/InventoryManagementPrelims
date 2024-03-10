import client.api.ClientApi;
import org.junit.jupiter.api.Test;
import utility.User;
import utility.revision.Item;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class Sales {

    @Test
    public void testSalesDashboard() {
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectOutputStream oOS = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream oIS = new ObjectInputStream(cSocket.getInputStream());

            oOS.writeUTF("requestSalesDashboard");
            oOS.flush();

            ArrayList<String> yearlyRevenueCogs = (ArrayList<String>) oIS.readObject();
            float[] revenueAndCost = (float[]) oIS.readObject();
            Stack<Item> recentlyAddedItems = (Stack<Item>) oIS.readObject();

            assertNotNull(revenueAndCost);
            assertNotNull(recentlyAddedItems);
            assertNotNull(yearlyRevenueCogs);

            System.out.println("Revenue and Cost for today: " + revenueAndCost[0] + ", " + revenueAndCost[1]);
            while (!recentlyAddedItems.isEmpty()){
                System.out.println(recentlyAddedItems.pop().getName());
            }
            for (String s : yearlyRevenueCogs) {
                System.out.println(s);
            }

            cSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testOnGenerate(){
        LocalDate currentDate = LocalDate.now();

        // Format the date using a specific pattern (optional)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateCreated = currentDate.format(formatter);
        String createdBy = "testsales";
        String orderType = "sales";
        User user = new User(createdBy);
        Item item = new Item("ChickenThighs", 2, null);
        ItemOrder itemOrder = new ItemOrder(user, 2, dateCreated, orderType);
        OrderDetails orderDetails = new OrderDetails(itemOrder.getOrderId(), item.getId(), 4, null, 120, null);
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectOutputStream oOS = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream oIS = new ObjectInputStream(cSocket.getInputStream());

            ClientApi.sendAction("createSalesInvoice", oOS);

            oOS.writeObject(itemOrder);
            oOS.writeObject(orderDetails);
        } catch (Exception e) {

        }

    }


}
