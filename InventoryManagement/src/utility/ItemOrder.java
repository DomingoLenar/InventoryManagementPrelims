package utility;

public class ItemOrder {
    private int id;
    private String date;
    private float purPrice;
    private String status;
    private int itemId;
    private String username;
    private int amount;

    /**
     * Default constructor for ItemOrder.
     * Initializes item to null, date to an empty string, and purPrice to 0.
     */
    public ItemOrder(){
        id = 0;
        date = "";
        purPrice = 0;
        status = "";
        itemId = 0;
        amount = 0;
    }

    /**
     * Constructor for ItemOrder with specified parameters.
     *
     * @param id The item associated with the order.
     * @param date The date when the order was made.
     * @param purPrice The purchase price of the item in the order.
     */
    public ItemOrder(int id, String date, float purPrice, String status, int itemId,int amount, String username){
        this.id = id;
        this.date = date;
        this.purPrice = purPrice;
        this.status = status;
        this.itemId = itemId;
        this.username = username;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the id associated with the order.
     *
     * @return The id associated with the order.
     */
    public int getItem() {
        return id;
    }

    /**
     * Gets the date when the order was made.
     *
     * @return The date of the order.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the purchase price of the item in the order.
     *
     * @return The purchase price of the item.
     */
    public float getPurPrice() {
        return purPrice;
    }

    /**
     * Sets the id associated with the order.
     *
     * @param id The item to be set.
     */
    public void setItem(int id) {
        this.id = id;
    }

    /**
     * Sets the date of the order.
     *
     * @param date The date to be set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the purchase price of the item in the order.
     *
     * @param purPrice The purchase price to be set.
     */
    public void setPurPrice(float purPrice) {
        this.purPrice = purPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
