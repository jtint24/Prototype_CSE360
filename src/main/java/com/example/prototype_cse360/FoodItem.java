package com.example.prototype_cse360;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FoodItem {
    private final String name;
    private final Image image;
    private final double price;

    FoodItem(String _name, Image _image, double _price) {
        name = _name;
        image = _image;
        price = _price;
    }

    public Node graphic() {
        VBox retBox = new VBox();
        ImageView foodImageView = new ImageView(image);
        foodImageView.setFitHeight(100);
        foodImageView.setFitWidth(100);
        retBox.getChildren().addAll(foodImageView, new Label(name+"   "+price));
        retBox.setStyle("-fx-background-color: #FFFFFF;");
        retBox.setPadding(new Insets(10));
        retBox.setSpacing(10);

        return retBox;
    }
}
