package utility;

public class ItemOrder {
    private Item item;
    private String date;
    private float purPrice;
    private String status;

    /**
     * Default constructor for ItemOrder.
     * Initializes item to null, date to an empty string, and purPrice to 0.
     */
    public ItemOrder(){
        item = null;
        date = "";
        purPrice = 0;
        status = "";
    }

    /**
     * Constructor for ItemOrder with specified parameters.
     *
     * @param item The item associated with the order.
     * @param date The date when the order was made.
     * @param purPrice The purchase price of the item in the order.
     */
    public ItemOrder(Item item, String date, float purPrice, String status){
        this.item = item;
        this.date = date;
        this.purPrice = purPrice;
        this.status = status;
    }

    /**
     * Gets the item associated with the order.
     *
     * @return The item associated with the order.
     */
    public Item getItem() {
        return item;
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
     * Sets the item associated with the order.
     *
     * @param item The item to be set.
     */
    public void setItem(Item item) {
        this.item = item;
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
}
