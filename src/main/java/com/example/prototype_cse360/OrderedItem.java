package com.example.prototype_cse360;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.zip.InflaterInputStream;

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

    public Node receiptGraphic() {
        VBox retBox = new VBox();

        Image trashImage = new Image("https://cdn-icons-png.flaticon.com/512/1843/1843344.png");
        ImageView trashView = new ImageView(trashImage);
        trashView.setFitWidth(20);
        trashView.setFitHeight(20);

        Button deleteButton = new Button("",trashView);

        deleteButton.setOnAction(actionEvent -> {ShoppingCart.removeItem(this);});

        Label headerLabel = new Label(item.name);

        HBox header = new HBox(deleteButton, headerLabel, Utils.Spacer(), new Label(item.price+""));

        retBox.getChildren().add(header);

        for (FoodMod mod : mods) {
            if (mod.isInEffect()) {
                HBox modEntry = new HBox(Utils.Spacer(), Utils.Spacer(), new Label(mod.getName()), Utils.Spacer(), new Label(""+mod.getPriceDifference()));
                retBox.getChildren().addAll(modEntry, Utils.Spacer());
            }
        }

        retBox.setSpacing(10);
        retBox.setPadding(new Insets(30));

        return retBox;
    }

    public double totalPrice() {
        double price = item.price;
        for (FoodMod mod : mods) {
            price += mod.getPriceDifference();
        }
        return price;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
