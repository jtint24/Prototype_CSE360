package com.example.prototype_cse360;
import java.util.ArrayList;
import java.util.Random;

public class OrderListHelper {
    private static final ArrayList<OrderedItem> orderedItems = new ArrayList<>();
    String[] items;
    int orderNumber;
    int orderState;
    double orderStateOfCooking;
    int chef;

    OrderListHelper(){
        Random rand = new Random();
        orderNumber= rand.nextInt(10);
        //chef = 0;
        orderState= 1+rand.nextInt(2);
        orderStateOfCooking=0.0;
        AutoFill();
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

    public static void removeItem(OrderedItem oi) {
        orderedItems.remove(oi);
    }

    public static void addItem(OrderedItem oi) {
        orderedItems.add(oi);
    }

    @Override
    public String toString(){
        String fullOrder="";
        for(int i=0; i<items.length;i++){
            fullOrder=fullOrder+items[i]+"\n";
        }
        return fullOrder;
    }
}