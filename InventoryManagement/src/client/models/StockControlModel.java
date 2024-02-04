package client.models;

import utility.Item;

public class StockControlModel {

    private Item item;

    public StockControlModel() {
        item = null;
    }
    public StockControlModel(Item item) {
        this.item = item;
    }

    public void addItem(String name, int qty, String type, int id, int price){
        try {
            Item newItem = new Item(name, qty,type,id,price);
            this.item = newItem;
            System.out.println("Successfully entered "+newItem);
        } catch (Exception e){
            System.err.println("Error adding item "+e.getMessage());
        }
    }

    public void removeItem(int id){
        this.item = null;
    }

    /**
     * TODO:
     * Modify the methods into accessing an manipulating a database
     */
}
