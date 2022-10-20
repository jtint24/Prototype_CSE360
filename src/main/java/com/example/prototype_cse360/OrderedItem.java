package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * OrderedItems represent items that have been ordered, including modifications
 * */
public class OrderedItem {
    FoodItem item;
    FoodMod[] mods;

    OrderedItem(FoodItem _item) {
        item = _item;
        mods = _item.availableModifiers.clone();
    }

    public Node modifiersGraphic() {
        VBox retBox = new VBox();

        HBox header = new HBox();
        ImageView foodImageView = new ImageView(item.image);
        foodImageView.setFitHeight(20);
        foodImageView.setFitWidth(20);

        header.getChildren().addAll(foodImageView, Utils.Spacer(), new Label(item.name), Utils.Spacer(), new Label(""+item.price));

        retBox.getChildren().add(header);

        for (FoodMod mod : mods) {
            retBox.getChildren().add(mod.graphic());
        }

        return retBox;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
