package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ShoppingCart {
    private static final ArrayList<OrderedItem> orderedItems = new ArrayList<>();

    public static void addItem(OrderedItem oi) {
        orderedItems.add(oi);
        // System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

    public static void removeItem(OrderedItem oi) {
        orderedItems.remove(oi);
    }

    public static ArrayList<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public static Node receiptGraphic() {
        VBox receipt = new VBox();
        for (OrderedItem orderedItem : orderedItems) {
            receipt.getChildren().add(orderedItem.receiptGraphic());
        }
        return receipt;
    }

}
