package utility.revision;

import utility.User;

import java.io.Serializable;

public class ItemOrder implements Serializable {
    private User createdBy;
    private int orderId;
    private String date;
    private String orderType;

    public ItemOrder(User createdBy, int orderId, String date, String orderType) {
        this.createdBy = createdBy;
        this.orderId = orderId;
        this.date = date;
        this.orderType = orderType;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
}
