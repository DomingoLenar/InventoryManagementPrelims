import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import server.model.query.RequestSalesDashboard;
import utility.revision.Item;
import utility.revision.ItemOrder;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class RequestSalesDashboardTest {

    @Test
    public void testProcess() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            RequestSalesDashboard.process(objectOutputStream);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            float[] revenueAndCost = (float[]) objectInputStream.readObject();
            ArrayList<Item> recentlyAddedItems = (ArrayList<Item>) objectInputStream.readObject();
            ArrayList<float[]> yearlyRevenueCogs = (ArrayList<float[]>) objectInputStream.readObject();

            Assertions.assertNotNull(revenueAndCost);
            Assertions.assertNotNull(recentlyAddedItems);
            Assertions.assertNotNull(yearlyRevenueCogs);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Assertions.fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGetYearlyRevenueNCosts() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getYearlyRevenueNCosts");
        method.setAccessible(true);
        ArrayList<float[]> result = (ArrayList<float[]>) method.invoke(null);
        System.out.println("Yearly Revenue and Costs:");
        for (float[] data : result) {
            System.out.println("Revenue: " + data[0] + ", Cost: " + data[1]);
        }
        Assertions.assertNotNull(result);

    }

    @Test
    public void testGetRevenueCogsByMonth() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getRevenueCogsByMonth", String[].class, ArrayList.class);
        method.setAccessible(true);
        String[] yearAndMonth = {"2024", "03"};
        ArrayList<ItemOrder> allSalesOrder = new ArrayList<>();
        float[] result = (float[]) method.invoke(null, yearAndMonth, allSalesOrder);
        System.out.println("Revenue and Cost by Month for 2024-03:");
        System.out.println("Revenue: " + result[0] + ", Cost: " + result[1]);
        Assertions.assertNotNull(result);

    }

    @Test
    public void testGetRecentlyAddedItems() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getRecentlyAddedItems");
        method.setAccessible(true);
        ArrayList<Item> result = (ArrayList<Item>) method.invoke(null);
        System.out.println("Recently Added Items:");
        for (Item item : result) {
            System.out.println("Item ID: " + item.getId());
        }
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }


    @Test
    public void testGetCurrentDate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getCurrentDate");
        method.setAccessible(true);
        String result = (String) method.invoke(null);
        System.out.println("Current Date: " + result);
        Assertions.assertNotNull(result);

    }

    @Test
    public void testGetSalesToday() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException  {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getSalesToday");
        method.setAccessible(true);
        ArrayList<ItemOrder> result = (ArrayList<ItemOrder>) method.invoke(null);
        System.out.println("Sales Today:");
        for (ItemOrder order : result) {
            System.out.println(order);
        }
        Assertions.assertNotNull(result);

    }

    @Test
    public void testGetRevenueNCostToday() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getRevenueNCostToday");
        method.setAccessible(true);
        float[] result = (float[]) method.invoke(null);
        System.out.println("Revenue and Cost Today:");
        System.out.println("Revenue: " + result[0] + ", Cost: " + result[1]);
        Assertions.assertNotNull(result);

    }

    @Test
    public void testGetCostOfGoodsSoldToday() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getCostOfGoodsSoldToday");
        method.setAccessible(true);
        float result = (float) method.invoke(null);
        System.out.println("Cost of Goods Sold Today: " + result);

    }

    @Test
    public void testGetRevenueToday() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException , ClassNotFoundException{
        Class<?> clazz = Class.forName("server.model.query.RequestSalesDashboard");
        Method method = clazz.getDeclaredMethod("getRevenueToday");
        method.setAccessible(true);
        float result = (float) method.invoke(null);
        System.out.println("Revenue Today: " + result);

    }


}
