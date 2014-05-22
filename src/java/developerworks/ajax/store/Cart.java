package developerworks.ajax.store;

import java.math.BigDecimal;
import java.util.*;

/**
 * A very simple shopping Cart
 *
 * @author Celine Patag
 */
public class Cart {

    private HashMap<Item, Integer> contents;

    /**
     * Creates a new Cart instance
     */
    public Cart() {
        contents = new HashMap<Item, Integer>();
    }

    /**
     * Adds the selected item to the cart
     *
     * @param itemCode The name of the item to add to the cart
     */
    public void addItem(String itemCode) {

        Catalog catalog = new Catalog();

        if (catalog.containsItem(itemCode)) {
            Item item = catalog.getItem(itemCode);

            int newQuantity = 1;
            if (contents.containsKey(item)) {
                Integer currentQuantity = contents.get(item);
                newQuantity += currentQuantity.intValue();
            }

            contents.put(item, new Integer(newQuantity));
        }
    }

    /**
     * Removes the named item from the cart
     *
     * @param itemCode Name of item to remove
     */
    public void removeItem(String itemCode) {
        Item item = new Catalog().getItem(itemCode);
        Integer quantity = contents.get(item);
        if (quantity > 1) {
            Integer newQuantity = --quantity;
            contents.put(item, newQuantity);
            System.out.println("new quantity: " + newQuantity);
        } else {
            contents.remove(item);
        }

    }

    /**
     * Writes the cart to a string buffer in xml format
     *
     * @return XML representation of cart contents
     */
    public String toXml() {
        StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\"?>\n");
        xml.append("<cart generated=\"" + System.currentTimeMillis() + "\" total=\"" + getCartTotal() + "\">\n");

        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();

            xml.append("<item code=\"" + item.getCode() + "\">\n");
            xml.append("<name>");
            xml.append(item.getName());
            xml.append("</name>\n");
            xml.append("<quantity>");
            xml.append(itemQuantity);
            xml.append("</quantity>\n");

            xml.append("<price>\n");
            xml.append(item.getFormattedPriceTimesQuantity(itemQuantity));
            xml.append("</price>");

            xml.append("</item>\n");
        }

        xml.append("</cart>\n");
        return xml.toString();
    }

    /**
     * Calculates the total of the items in the shopping cart.
     *
     * @return the String representation of the total.
     */
    private String getCartTotal() {
        int total = 0;

        for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
            Item item = I.next();
            int itemQuantity = contents.get(item).intValue();

            total += (item.getPrice() * itemQuantity);
        }

        return "$" + new BigDecimal(total).movePointLeft(2);
    }
}
