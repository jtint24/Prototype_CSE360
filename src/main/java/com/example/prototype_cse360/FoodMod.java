package com.example.prototype_cse360;

import javafx.scene.Node;

/**
 * FoodMod
 *
 * represents a modification made to a food item, like a size change,
 * */
public abstract class FoodMod {

    abstract Node graphic();

    abstract boolean isInEffect();

    abstract double getPriceDifference();

    abstract String getName();
}
