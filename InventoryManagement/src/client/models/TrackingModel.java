package client.models;
import java.util.ArrayList;
import utility.Item;
import utility.ItemOrder;
import java.util.List;

public class TrackingModel {
    private List<ItemOrder> buyList;



    public TrackingModel (){
        buyList = new ArrayList<>();
    }
    public TrackingModel(ItemOrder itemOrder) {
        this();
        addItemOrder(itemOrder);
    }



    public void updateTrackingStatus(int itemId, String newStatus) {
        try {
            int index = findItemOrderIndexById(itemId);

            if (index != -1) {
                ItemOrder itemOrder = buyList.get(index);
                itemOrder.setStatus(newStatus);
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("Item with ID " + itemId + " not found.");
            }

        } catch (Exception e) {
            System.err.println("Failed to update the status of the product " + e.getMessage());
        }
    }


    private void addItemOrder(ItemOrder itemOrder) {
        buyList.add(itemOrder);
    }

    private int findItemOrderIndexById(int itemId) {
        for (int i = 0; i < buyList.size(); i++) {
            ItemOrder itemOrder = buyList.get(i);
            Item item = itemOrder.getItem();
            if (item != null && item.getId() == itemId) {
                return i;
            }
        }
        return -1;
    }
    /**
     * TODO:
     * Modify the methods into accessing an manipulating a database
     */
}
