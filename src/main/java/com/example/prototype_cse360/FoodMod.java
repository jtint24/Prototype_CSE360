package com.example.prototype_cse360;

import javafx.scene.Node;

import java.io.Serializable;

/**
 * FoodMod
 *
 * represents a modification made to a food item, like a size change, or an extra sauce request
 * */
public abstract class FoodMod implements Cloneable, Serializable {

    abstract Node graphic();

    abstract boolean isInEffect();

    abstract double getPriceDifference();

    abstract String getName();

    @Override
    public abstract FoodMod clone();
}
