package com.example.prototype_cse360;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
public class OrderListHelper {
    private static final ArrayList<ShoppingCart> orderedItems = new ArrayList<>();
    int orderNumber;
    int orderState; // This is used by the code to distinguish which lists it should appear on
    String orderType;
    double orderStateOfCooking; // This is what is used to fill the progress bar
    int chefID;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    

    OrderListHelper() {
        Random rand = new Random();
        orderNumber= rand.nextInt(10);
        //chef = 0;
        orderState= 1+rand.nextInt(2);
        orderStateOfCooking=0.0;

        if(1+rand.nextInt(2)==2){
            orderType="Delivery";
        }
        else{
            orderType="Pick-Up";
        }
        fillFromFiles(System.getProperty("user.dir"));
    }

    OrderListHelper(ArrayList<OrderedItem> orderedItems) {}

    public void fillFromFiles(String directory){
        try {
            File dir = new File(directory);
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    if (child.isFile()) {
                        if (child.getName().endsWith(".object")) {
                            FileInputStream file = new FileInputStream(child);
                            ObjectInputStream in = new ObjectInputStream(file);

                            ShoppingCart newCart = (ShoppingCart) in.readObject();
                            orderedItems.add(newCart);

                            in.close();
                            file.close();
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void removeItem(ShoppingCart oi) {
        orderedItems.remove(oi);
    }

    public static void addItem(ShoppingCart oi) {
        orderedItems.add(oi);
    }

    public void readFromFiles(String directory) throws ClassNotFoundException, IOException {
        File dir = new File(directory);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                FileInputStream file = new FileInputStream(child);
                ObjectInputStream in = new ObjectInputStream(file);


                ShoppingCart object = (ShoppingCart)in.readObject();
                orderedItems.add(object);

                in.close();
                file.close();

                //System.out.println("Object has been deserialized\n"+ "Data after Deserialization.");
            }
        }
        else {

        }
    }


    @Override
    public String toString(){
        String fullOrder = "Order #"+orderNumber+"\n  • Time to be ready: "+dtf.format(now)+"\n  • Order Type: "+orderType+"\n  • Estimated time to Prepare: "+"\n  • Items:"+"\n";
        
        for(int i=0; i<orderedItems.size(); i++){
            fullOrder += "       • "+orderedItems.get(i).toString()+"\n";
        }
        return fullOrder;
    }
}
*/