package com.example.prototype_cse360;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ShoppingCart implements Serializable {
    private final ArrayList<OrderedItem> orderedItems = new ArrayList<>();
    private String ordererName;
    private String creditCardNumber;
    private int creditCardSecurityCode;
    private String creditCardExpDate ;
    private String asuEmail;
    private String asuID;
    private OrderState orderState;
    private String uuid;

    ShoppingCart() {
        orderState = OrderState.ORDERING;

        ordererName = "";
        asuID = "";
        asuEmail = "";
        creditCardExpDate = "";
        creditCardSecurityCode = 0;
        creditCardNumber = "";
        ordererName = "";
        SecureRandom rng = new SecureRandom();
        uuid = LocalDateTime.now().hashCode()+"-"+rng.nextInt();
    }

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
            orderState = OrderState.RECEIVED;
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
     * deleteFile
     *
     * Deletes the associated file of the shopping cart. Fails if no such file exists.
     * */
    public void deleteFile() {
        try {
            File fileToDelete = new File(fileName());
            fileToDelete.delete();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public OrderState getOrderState() {
        return this.orderState;

    }

    public void setOrderState(OrderState newState){
        this.orderState= newState;
    }

    /**
     * fileName
     *
     * @return The name of the file that this order would be stored in
     * */
    private String fileName() {
        return "order-"+ordererName+"-"+uuid+".order"; // Hash is used to ensure unique file name
    }

    public void setPaymentInformation(String _creditCardNumber, int _creditCardSecurityCode, String _creditCardExpDate, String _asuEmail, String _asuID) {
        creditCardNumber = _creditCardNumber;
        creditCardSecurityCode = _creditCardSecurityCode;
        creditCardExpDate = _creditCardExpDate;
        asuEmail = _asuEmail;
        asuID = _asuID;
    }

    public enum OrderState {
        ORDERING, RECEIVED, ASSIGNED_TO_CHEF, COOKING, PREPARED, DELIVERED;

        /**
         * chefCanAssign
         *
         * @return whether a chef can assign an order to this state
         * */
        public boolean chefCanAssign() {
            return switch (this) {
                case ASSIGNED_TO_CHEF,COOKING,PREPARED -> true;
                default -> false;
            };
        }

        /**
         * opCanAssign
         *
         * @return whether an order processor can assign an order to this state
         * */
        public boolean opCanAssign() {
            return switch (this) {
                case ORDERING, COOKING -> false;
                default -> true;
            };
        }

        /**
         * getProgress
         *
         * @return The percent completion that the state represents, from 0.00 to 1.00
         * */

        public double getProgress() {
            return ((double)this.ordinal()) / ((double)OrderState.values().length);
        }

        public String toString() {
            return switch (this) {
                case ORDERING -> "Ordering";
                case COOKING -> "Cooking";
                case ASSIGNED_TO_CHEF -> "Assigned To Chef";
                case PREPARED -> "Prepared";
                case DELIVERED -> "Delivered";
                case RECEIVED -> "Received";
            };
        }

    }

    @Override
    public String toString() {
        String retString = "Order for "+ordererName+"\n\n";
        for (OrderedItem orderedItem : orderedItems) {
            retString += "\t"+orderedItem.toString() + "\n";
        }
        return retString;
    }

}
