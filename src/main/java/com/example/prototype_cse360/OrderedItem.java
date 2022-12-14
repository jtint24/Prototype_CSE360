package com.example.prototype_cse360;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * OrderedItems represent items that have been ordered, including modifications
 * */
public class OrderedItem implements Serializable {
    FoodItem item;
    FoodMod[] mods;

    OrderedItem(FoodItem _item) {
        item = _item;
        mods = new FoodMod[_item.availableModifiers.length];
        for (int i = 0; i<_item.availableModifiers.length; i++) {
            mods[i] = _item.availableModifiers[i].clone();
        }
    }

    /**
     * modifiersGraphic
     *
     * @return the graphic where a modifier can be selected, which shown in the modifiers page
     * */

    public Node modifiersGraphic() {
        VBox retBox = new VBox();

        HBox header = new HBox();
        ImageView foodImageView = new ImageView(item.image.getImage());
        foodImageView.setFitHeight(20);
        foodImageView.setFitWidth(20);

        Label nameLabel = new Label(item.name);
        nameLabel.setTextFill(Color.WHITE);
        Label priceLabel = new Label(""+item.price);
        priceLabel.setTextFill(Color.WHITE);

        header.getChildren().addAll(foodImageView, Utils.spacer(), nameLabel, Utils.spacer(), priceLabel);

        retBox.getChildren().add(header);

        for (FoodMod mod : mods) {
            retBox.getChildren().add(mod.graphic());
        }

        return retBox;
    }

    /**
     * recieptGraphic
     *
     * @return The graphic shown for each item on the receipt page, with a delete button
     * */

    public Node receiptGraphic(ShoppingCart mainShoppingCart, ProtoApplication mainApp) {
        VBox retBox = new VBox();

        Image trashImage = new Image("https://cdn-icons-png.flaticon.com/512/1843/1843344.png");
        ImageView trashView = new ImageView(trashImage);
        trashView.setFitWidth(20);
        trashView.setFitHeight(20);

        Button deleteButton = Utils.makeCleanButton(trashView);

        deleteButton.setOnAction(actionEvent -> {mainShoppingCart.removeItem(this, mainApp);});

        Label headerLabel = new Label(item.name);
        headerLabel.setTextFill(Color.WHITE);
        Label priceLabel = new Label(item.price+"");
        priceLabel.setTextFill(Color.WHITE);

        HBox header = new HBox(deleteButton, headerLabel, Utils.spacer(), priceLabel);

        retBox.getChildren().add(header);

        for (FoodMod mod : mods) {
            if (mod.isInEffect()) {
                Label modLabel = new Label(mod.getName());
                Label modPriceLabel = new Label(mod.getPriceDifference()+"");
                modLabel.setTextFill(Color.WHITE);
                modPriceLabel.setTextFill(Color.WHITE);
                HBox modEntry = new HBox(Utils.spacer(), Utils.spacer(), modLabel, Utils.spacer(), modPriceLabel);
                retBox.getChildren().addAll(modEntry, Utils.spacer());
            }
        }

        retBox.setSpacing(10);
        retBox.setPadding(new Insets(30));

        return retBox;
    }

    /**
     * totalPrice
     *
     * @return Returns the total price of the item with all of its modifiers
     * */

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