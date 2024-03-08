package utility.revision;

import java.util.LinkedList;

public class Item {
    private String name;
    private int id;
    private int totalQty;
    private String type;
    LinkedList<Stock> stocks;

    public Item(String name, int id, String type){
        this.name = name;
        this.id = id;
        this.type = type;
        this.stocks = new LinkedList();
    }

    public Item(String name, int id,int totalQty, String type, LinkedList<Stock> stocks){
        this.name = name;
        this.id = id;
        this.totalQty = totalQty;
        this.type = type;
        this.stocks = stocks;
    }

    public void addStocks(Stock stock){
        this.stocks.addLast(stock);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stock getStock(int index){
        return this.stocks.get(index);
    }

    public Stock getFirstStock(){
        return this.stocks.getFirst();
    }

    public LinkedList<Stock> getAllStocks() {
        return this.stocks;
    }

    public void setStocks(LinkedList<Stock> stocks) {
        this.stocks = stocks;
    }
}
