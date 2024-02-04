package utility;


public class Item {
    private String name;
    private int qty;
    private String type;
    private int id;
    private int price;

    /**
     * Default constructor for Item.
     * Initializes name to an empty string, qty to 0, type to an empty string, id to 0, and price to 0.
     */
    public Item(){
        name = "";
        qty = 0;
        type = "";
        id = 0;
        price = 0;
    }

    /**
     * Constructor for Item with specified parameters.
     *
     * @param name The name of the item.
     * @param qty The quantity of the item.
     * @param type The type of the item.
     * @param id The ID of the item.
     * @param price The price of the item.
     */
    public Item(String name, int qty, String type, int id, int price ){
        this.name = name;
        this.qty = qty;
        this.type = type;
        this.id = id;
        this.price = price;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */
    public int getQty() {
        return qty;
    }

    /**
     * Gets the type of the item.
     *
     * @return The type of the item.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the ID of the item.
     *
     * @return The ID of the item.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the name of the item.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param qty The quantity to be set.
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * Sets the type of the item.
     *
     * @param type The type to be set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the ID of the item.
     *
     * @param id The ID to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The price to be set.
     */
    public void setPrice(int price) {
        this.price = price;
    }
}
