package com.example.prototype_cse360;

import java.util.ArrayList;

public class TempSupportClass {
    OrderListHelper oLHelper = new OrderListHelper();
   
   
    public ArrayList<OrderListHelper> ListOfOrders(){
        ArrayList<OrderListHelper> orders = new ArrayList<>();
        for(int i=0; i<5; i++){
            orders.add(new OrderListHelper());
        }
        return orders;
    }
    
}
