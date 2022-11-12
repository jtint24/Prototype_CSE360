package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private final ArrayList<OrderedItem> orderedItems = new ArrayList<>();
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

    public void removeItem(OrderedItem oi, ProtoApplication mainApp) {
        orderedItems.remove(oi);
        mainApp.updateReceiptGraphic();
        //System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }

    public ArrayList<OrderedItem> getOrderedItems() {
        return orderedItems;
    }



    /**
     * writeToFile
     *
     * Creates a new file in the local directory and writes this order's data to it so it can be read by the
     * order manager and chef
     * */

    public void writeToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName()));
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
            System.out.println("Created Streams!");

            objOutputStream.writeObject(this);
            System.out.println("Wrote order!");


            objOutputStream.close();
            fileOutputStream.close();
            System.out.println("Finished writing order!");

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound Exception when writing shopping cart to file!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * fileName
     *
     * @return The name of the file that this order would be stored in
     * */

    private String fileName() {
        return "order-"+ordererName+"-"+hashCode()+".order"; // Hash is used to ensure unique file name
    }

}
