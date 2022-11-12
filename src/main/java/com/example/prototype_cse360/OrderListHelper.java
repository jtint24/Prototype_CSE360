package com.example.prototype_cse360;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class OrderListHelper {
    private static final ArrayList<ShoppingCart> orderedItems = new ArrayList<>();
    String[] items;
    int orderNumber;//Currently being filled by a random number going forwad it will be a real number
    int orderState;//This is used by the code to distinguish which lists it should appear on
    String orderType;
    double orderStateOfCooking;//This is what is used to fill the progress bar 
    int chef;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    

    OrderListHelper(){
        /*Random rand = new Random();
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

        AutoFill();  */
    }

    OrderListHelper(ArrayList<OrderedItem> orderedItems){


    }

    public void AutoFill(){
        Random rand = new Random();
        int upperBound= rand.nextInt(4);
        upperBound=upperBound+2;
        items= new String[upperBound];
        for(int i=0;i<upperBound; i++ ){
            //int upperBoundMods= rand.nextInt(2);
            int choice= rand.nextInt(4);
            switch(choice){
                case 1:
                    items[i]="Pizza";
                    break;
                case 2:
                    items[i]="Wings";
                    break;
                case 3:
                    items[i]="Salad";
                    break;
                case 4:
                    items[i]="Soda";
                    break;
                default:
                    items[i]="Cookie";
                    break;
            }
            
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
        String fullOrder="Order #"+orderNumber+"\n  • Time to be ready: "+dtf.format(now)+"\n  • Order Type: "+orderType+"\n  • Estimated time to Prepare: "+"\n  • Items:"+"\n";
        
        for(int i=0; i<items.length;i++){
            fullOrder=fullOrder+"       • "+items[i]+"\n";
        }
        return fullOrder;
    }
}