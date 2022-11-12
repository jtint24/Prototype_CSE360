package com.example.prototype_cse360;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.Serializable;

public class FoodItem implements Serializable {
     final String name;
     final Image image;
     final double price;
     final Category category;
     FoodMod[] availableModifiers = new FoodMod[0];

    FoodItem(String _name, double _price, Category _category, Image _image) {
        name = _name;
        price = _price;
        category = _category;
        image = _image;
    }
    FoodItem(String _name, double _price, Category _category, Image _image, FoodMod... _availableModifiers) {
        name = _name;
        price = _price;
        category = _category;
        image = _image;
        availableModifiers = _availableModifiers;
    }

    /**
     * graphic
     *
     * @return The node shown for each menu item in the menu page, with an image of the food item and its
     *         title
     * */

    public Node graphic() {
        VBox retBox = new VBox();

        ImageView foodImageView = new ImageView(image);
        foodImageView.setFitHeight(100);
        foodImageView.setFitWidth(100);

        retBox.getChildren().addAll(foodImageView, new Label(name+"   "+price));
        retBox.setStyle("-fx-background-color: #850E35;");
        // changed color to moroon , original :FFFFFF
        retBox.setPadding(new Insets(10));
        retBox.setSpacing(10);

        return retBox;
    }

    /**
     * graphicButton
     *
     * @return The node from graphic() inside a pressable button that adds the item to the shopping cart
     * */

    public Button graphicButton(ShoppingCart mainShoppingCart) {
        Button retButton = new Button("",graphic());

        retButton.setOnAction(actionEvent -> {
            mainShoppingCart.addItem(new OrderedItem(this));
        });

        return retButton;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name+" "+price+"\n";
    }

    /**
     * Category
     *
     * Simple enum containing all the categories of food items
     * */

    public enum Category {PIZZA, SALAD, WINGS, DRINKS, DESSERT}
}
