package utility.revision;

public class Stock {
    private String batchNo;
    private float cost;
    private float price;
    private int qty;

    public Stock(){
        this.batchNo = null;
        this.costs = -1;
        this.price = -1;
        this.qty = -1;
    }

    public Stock(String batchNo, float cost, float price, int qty){
        this.batchNo = batchNo;
        this.cost = cost;
        this.price = price;
        this.qty = qty;
    }
}
