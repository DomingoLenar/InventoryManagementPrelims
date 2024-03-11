package client.sales.models;

import client.api.ClientApi;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;

public class SalesDashboardModel {
    ArrayList<String> yearlyRevenueCogs;
    float[] revenueAndCogs;
    Stack<Item> recentlyAddedItems;
    public SalesDashboardModel() {}

    public void fetchDashboard(ObjectOutputStream oOs, ObjectInputStream oIs) {
        try{
            ClientApi.sendAction("requestSalesDashboard", oOs);
            try {
                yearlyRevenueCogs = (ArrayList<String>) oIs.readObject();
                revenueAndCogs = (float[]) oIs.readObject();
                recentlyAddedItems = (Stack<Item>) oIs.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Error fetching dashboard datum", ioException);
        }
    }

    public Stack<Item> getRecentlyAddedItems() {
        return recentlyAddedItems;
    }

    public ArrayList<String> getYearlyRevenueCogs() {
        return yearlyRevenueCogs;
    }

    public float[] getRevenueAndCogs() {
        return revenueAndCogs;
    }
}
