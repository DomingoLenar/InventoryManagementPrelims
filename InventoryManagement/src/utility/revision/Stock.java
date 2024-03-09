package utility.revision;

import java.io.Serializable;

public class Stock implements Serializable {
    private String batchNo;
    private float cost;
    private float price;
    private int qty;
    private String supplier;

    public Stock(){
        this.batchNo = null;
        this.cost = -1;
        this.price = -1;
        this.qty = -1;
        this.supplier = null;
    }

    public Stock(String batchNo, float cost, float price, int qty, String supplier){
        this.batchNo = batchNo;
        this.cost = cost;
        this.price = price;
        this.qty = qty;
        this.supplier = supplier;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
