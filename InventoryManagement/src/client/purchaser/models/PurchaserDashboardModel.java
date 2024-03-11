package client.purchaser.models;

import client.api.ClientApi;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PurchaserDashboardModel {
    ArrayList<Item> lowStockItems;
    int[] unitsSold;
    public PurchaserDashboardModel(){}

    public void fetchDashboard(ObjectOutputStream oOs, ObjectInputStream oIs) {
        try{
            ClientApi.sendAction("requestPurchaseDashboard", oOs);
            try {
                lowStockItems = (ArrayList<Item>) oIs.readObject();
                unitsSold = (int[]) oIs.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Error fetching dashboard datum", ioException);
        }
    }
}
