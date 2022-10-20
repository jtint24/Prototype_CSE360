package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class OptionalMod extends FoodMod {
    boolean inEffect = false;

    OptionalMod(String _name, double _priceDifference) {
        super(_name, _priceDifference);
    }

    public Node graphic() {
        HBox retBox = new HBox();
        CheckBox activeBox = new CheckBox();
        activeBox.setOnAction(actionEvent -> {inEffect = !inEffect;});

        retBox.getChildren().addAll(Utils.Spacer(), activeBox, new Label(super.name), Utils.Spacer(), new Label(""+priceDifference));
        return retBox;
    }

}
