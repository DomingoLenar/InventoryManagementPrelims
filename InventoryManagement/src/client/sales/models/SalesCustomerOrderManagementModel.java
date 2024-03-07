package client.sales.models;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//public class SalesCustomerOrderManagementModel implements OrderManagementInterface
public class SalesCustomerOrderManagementModel {
    ObjectOutputStream oOs;
    ObjectInputStream oIs;
    public SalesCustomerOrderManagementModel(ObjectOutputStream oOs, ObjectInputStream oIs){
        this.oOs = oOs;
        this.oIs = oIs;
    }

    public void OnGenerate(String[] cxOrderDetails) {
    }

    public void OnUpdate(String[] cxOrderDetails) {
    }

    public void OnDelete(String[] cxOrderDetails) {
    }

//    @Override
//    public void OnGenerate(String[] cxOrderDetails) {
//        try {
//            ClientApi.sendAction("generateSalesInvoice", oOs);
//
//            oOs.writeUTF(Arrays.toString(cxOrderDetails));
//            oOs.flush();
//            
//        } catch (IOException e) {
//            throw new RuntimeException("Error generating sales invoice", e);
//        }
//
//    }
//
//    @Override
//    public void OnUpdate(String[] cxOrderDetails) {
//
//    }
//
//    @Override
//    public void OnDelete(String[] cxOrderDetails) {
//
//    }
}
