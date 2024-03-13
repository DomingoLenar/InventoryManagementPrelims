package client.sales.models;

import client.api.ClientApi;
import utility.revision.ItemOrder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SalesSalesInvoicesModel {

    public ArrayList<ItemOrder> fetchListOfSalesInvoice(ObjectOutputStream oOs, ObjectInputStream oIs, String userType) {
        try {
            ClientApi.sendAction("fetchItemOrders", oOs);

            oOs.writeUTF(userType);
            oOs.flush();

            try {
                ArrayList<ItemOrder> itemOrderArrayList = (ArrayList<ItemOrder>) oIs.readObject();
                return itemOrderArrayList;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
