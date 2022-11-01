package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private static final ArrayList<OrderedItem> orderedItems = new ArrayList<>();
    private static VBox receiptGraphic = new VBox();

    /**
     * addItem
     *
     * adds item to the shopping cart
     * */

    public static void addItem(OrderedItem oi) {
        orderedItems.add(oi);
        // System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

    /**
     * removeItem
     *
     * removes item from the shopping cart
     * */

    public static void removeItem(OrderedItem oi) {
        orderedItems.remove(oi);
        updateReceiptGraphic();
        //System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

    public static ArrayList<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public static Node receiptGraphic() {
        return receiptGraphic;
    }

    /**
     * updateReceiptGraphic
     *
     * Updates the interactive receipt graphic with the graphics from the ordered items
     * */

    public static void updateReceiptGraphic() {
        receiptGraphic.getChildren().removeIf(node -> true);
        for (OrderedItem orderedItem : orderedItems) {
            receiptGraphic.getChildren().add(orderedItem.receiptGraphic());
        }
    }

}
