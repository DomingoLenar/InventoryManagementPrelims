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

    public void addStocks(Stock stock){
        this.stocks.addLast(stock);
    }

    public Stock getStock(int index){
        this.stocks.(index);
    }

    public Stock getFirstStock(){
        this.stocks.getFirst();
    }
}
