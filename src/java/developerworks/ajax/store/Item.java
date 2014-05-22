package developerworks.ajax.store;

import java.math.BigDecimal;

/**
 * Java bean of the item. A collection of these is found in the catalog and the
 * cart.
 *
 * @author Celine Patag
 */
public class Item {

    private String code;
    private String name;
    private String description;
    private int price;

    /**
     * Creates a new Item instance.
     *
     * @param code
     * @param name
     * @param description
     * @param price
     */
    public Item(String code, String name, String description, int price) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     *
     * @return item code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return price of the item
     */
    public int getPrice() {
        return price;
    }

    public String getFormattedPrice() {
        return "$" + new BigDecimal(price).movePointLeft(2);
    }

    /**
     * Calculates the price * quantity
     *
     * @param quantity
     * @return String representation of price x quantity
     */
    public String getFormattedPriceTimesQuantity(int quantity) {
        int priceQuantity = price * quantity;
        StringBuffer sb = new StringBuffer("$");
        sb.append(new BigDecimal(priceQuantity).movePointLeft(2));
        return sb.toString();
    }

    /**
     * Evaluates if two items are equal.
     *
     * @return true of code is equal, else false
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null) {
            return false;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return ((Item) o).getCode().equals(this.code);
    }
}
