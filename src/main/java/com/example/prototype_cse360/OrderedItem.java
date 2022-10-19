package com.example.prototype_cse360;

import java.util.ArrayList;

/**
 * OrderedItems represent items that have been ordered, including modifications
 * */
public class OrderedItem {
    FoodItem item;
    ArrayList<FoodMod> mods;

    OrderedItem(FoodItem _item) {
        item = _item;
    }
}
