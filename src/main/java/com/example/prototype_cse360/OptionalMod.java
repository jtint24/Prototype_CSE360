package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class OptionalMod extends FoodMod {
    boolean inEffect = false;
    private final String name;
    private final double priceDifference;

    OptionalMod(String _name, double _priceDifference) {
        name = _name;
        priceDifference = _priceDifference;
    }

    public Node graphic() {
        HBox retBox = new HBox();
        CheckBox activeBox = new CheckBox();
        activeBox.setOnAction(actionEvent -> {inEffect = !inEffect;});

        retBox.getChildren().addAll(Utils.Spacer(), activeBox, new Label(name), Utils.Spacer(), new Label(""+priceDifference));
        return retBox;
    }

    @Override
    public boolean isInEffect() {
        return inEffect;
    }

    public double getPriceDifference() {
        return inEffect ? priceDifference : 0;
    }

    public String getName() {
        return name;
    }

}
