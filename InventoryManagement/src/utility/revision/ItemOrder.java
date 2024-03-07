package utility.revision;

import utility.User;

public class ItemOrder {
    private User createdBy;
    private String date;
    private String orderType;
    private int itemId;
    private String batchNo;
    private String supplier;
    private float price;
    private int qty;

    public ItemOrder() {
        this.createdBy = null;
        this.date = null;
        this.orderType = null;
        this.itemId = -1;
        this.batchNo = null;
        this.supplier = null;
        this.price = -1;
        this.qty = -1;
    }

    public ItemOrder(User createdBy, String date, String orderType, int itemId, String batchNo, String supplier, float price, int qty) {
        this.createdBy = createdBy;
        this.date = date;
        this.orderType = orderType;
        this.itemId = itemId;
        this.batchNo = batchNo;
        this.supplier = supplier;
        this.price = price;
        this.qty = qty;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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
}
