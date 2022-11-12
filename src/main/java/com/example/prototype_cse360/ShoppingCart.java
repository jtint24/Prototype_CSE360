package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private final ArrayList<OrderedItem> orderedItems = new ArrayList<>();
    private VBox receiptGraphic = new VBox();
    private String ordererName = "";

    /**
     * addItem
     *
     * adds item to the shopping cart
     * */

    public void addItem(OrderedItem oi) {
        orderedItems.add(oi);
        // System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

    /**
     * removeItem
     *
     * removes item from the shopping cart
     * */

    public void removeItem(OrderedItem oi) {
        orderedItems.remove(oi);
        updateReceiptGraphic();
        //System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

    public ArrayList<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public Node receiptGraphic() {
        return receiptGraphic;
    }

    /**
     * updateReceiptGraphic
     *
     * Updates the interactive receipt graphic with the graphics from the ordered items
     * */

    public void updateReceiptGraphic() {
        receiptGraphic.getChildren().removeIf(node -> true);
        for (OrderedItem orderedItem : orderedItems) {
            receiptGraphic.getChildren().add(orderedItem.receiptGraphic(this));
        }
    }

    /*
    public void writeToFile() {
        try {
            FileOutputStream f = new FileOutputStream(new File(fileName()));
        } catch (FileNotFoundException e) {

        }
    }

    private String fileName() {
        return "order-"+ordererName+"-"+hashCode()+".order";
    }
     */

}
