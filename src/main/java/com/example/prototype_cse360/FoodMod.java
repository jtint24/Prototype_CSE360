package com.example.prototype_cse360;

import javafx.scene.Node;

/**
 * FoodMod
 *
 * represents a modification made to a food item, like a size change,
 * */
public abstract class FoodMod {
    protected String name;
    protected double priceDifference;

    FoodMod(String _name, double _priceDifference) {
        name = _name;
        priceDifference = _priceDifference;
    }

    abstract Node graphic();
}
