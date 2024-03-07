package utility.revision;

public class OrderDetails {
    private int itemOrderID;
    private int itemID;
    private int units;
    private String batchNo;
    private float unitPrice;
    private String supplier;

    public OrderDetails(int itemOrderID, int itemID, int units, String batchNo, float unitPrice, String supplier) {
        this.itemOrderID = itemOrderID;
        this.itemID = itemID;
        this.units = units;
        this.batchNo = batchNo;
        this.unitPrice = unitPrice;
        this.supplier = supplier;
    }

    public int getItemOrderID() {
        return itemOrderID;
    }

    public void setItemOrderID(int itemOrderID) {
        this.itemOrderID = itemOrderID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
