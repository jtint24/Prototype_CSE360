package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class OptionalMod extends FoodMod implements Serializable {
    boolean inEffect = false;
    private final String name;
    private final double priceDifference;

    OptionalMod(String _name, double _priceDifference) {
        name = _name;
        priceDifference = _priceDifference;
    }

    /**
     * graphic
     *
     * @return The node shown for each individual modifier in the modifiers page, with a toggle letting
     *         the user select or deselect the mod
     * */

    @Override
    public Node graphic() {
        HBox retBox = new HBox();
        CheckBox activeBox = new CheckBox();
        activeBox.setOnAction(actionEvent -> {inEffect = !inEffect;});
        activeBox.setSelected(inEffect);

        Label nameLabel = new Label(name);
        nameLabel.setTextFill(Color.WHITE);
        Label priceLabel = new Label(""+priceDifference);
        priceLabel.setTextFill(Color.WHITE);

        retBox.getChildren().addAll(Utils.spacer(), activeBox, nameLabel, Utils.spacer(), priceLabel);
        return retBox;
    }

    @Override
    public boolean isInEffect() {
        return inEffect;
    }

    @Override
    public double getPriceDifference() {
        return inEffect ? priceDifference : 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public FoodMod clone() {
        return new OptionalMod(name, priceDifference);
    }

}
