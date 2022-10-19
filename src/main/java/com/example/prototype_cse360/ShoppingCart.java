package com.example.prototype_cse360;

import java.util.ArrayList;

public class ShoppingCart {
    private static final ArrayList<OrderedItem> orderedItems = new ArrayList<>();

    public static void addItem(OrderedItem oi) {
        orderedItems.add(oi);
        System.out.println(orderedItems.stream().map(OrderedItem::toString).reduce("", (a,b)->a+b));
    }
}
