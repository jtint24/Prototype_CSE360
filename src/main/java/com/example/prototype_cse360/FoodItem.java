package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class FoodItem {
    private final String name;
    private final Image image;
    private final float price;

    FoodItem(String _name, Image _image, float _price) {
        name = _name;
        image = _image;
        price = _price;
    }

    public Node graphic() {
        VBox retBox = new VBox();
        retBox.getChildren().addAll(new ImageView(image), new Label(name+" "+price));
        return retBox;
    }
}
