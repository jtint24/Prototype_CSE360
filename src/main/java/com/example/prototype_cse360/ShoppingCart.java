package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ShoppingCart {
    private static final ArrayList<OrderedItem> orderedItems = new ArrayList<>();
    private static VBox receiptGraphic = new VBox();

    public static void addItem(OrderedItem oi) {
        orderedItems.add(oi);
        // System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

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

    public static void updateReceiptGraphic() {
        receiptGraphic.getChildren().removeIf(node -> true);
        for (OrderedItem orderedItem : orderedItems) {
            receiptGraphic.getChildren().add(orderedItem.receiptGraphic());
        }
    }

}
