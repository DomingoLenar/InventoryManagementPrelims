package utility.revision;

public class Stock {
    private String batchNo;
    private float cost;
    private float price;
    private int qty;

    public Stock(){
        this.batchNo = null;
        this.cost = -1;
        this.price = -1;
        this.qty = -1;
    }

    public Stock(String batchNo, float cost, float price, int qty){
        this.batchNo = batchNo;
        this.cost = cost;
        this.price = price;
        this.qty = qty;
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
}
