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

        ImageView foodImageView = new ImageView(item.image);
        foodImageView.setFitHeight(20);
        foodImageView.setFitWidth(20);
        foodImageView.setStyle("-fx-background-color: #850E35");

        Label itemName = new Label(item.name);
        itemName.setStyle("-fx-background-color: #850E35");
        itemName.setTextFill(Color.web("#FFFFFF"));

        Label itemPrice;
        if(item.name == "PEPPERONI PIZZA"){
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t $"+item.price);
        }
        else if(item.name == "CHEESE PIZZA") {
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t\t$" + item.price);
        }
        else if(item.name == "CAESAR SALAD" || item.name == "GARDEN SALAD"){
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t     $"+item.price);
        } else if(item.name == "HAWAIIAN PIZZA"){
            itemPrice = new Label("\t\t\t\t\t\t\t\t          $"+item.price);

        } else if(item.name == "BUFFALO WINGS"){
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t  $"+item.price);
        }
        else if(item.name == "Coca-Cola"){
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t\t\t $"+item.price);
        }
        else if(item.name == "DR. PEPPER"){
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t\t      $"+item.price);
        }
        else{
            itemPrice = new Label("\t\t\t\t\t\t\t\t\t\t\t      $"+item.price);
        }

        itemPrice.setStyle("-fx-background-color: #850E35");
        itemPrice.setTextFill(Color.web("#FFFFFF"));

        header.getChildren().addAll(foodImageView, Utils.spacer(), itemName, Utils.spacer(), itemPrice);
        header.setStyle("-fx-background-color: #850E35");

        retBox.getChildren().add(header);

        for (FoodMod mod : mods) {
            retBox.getChildren().add(mod.graphic());
        }
        retBox.setStyle("-fx-background-color: #FFFFFF");
        retBox.setSpacing(10);

        return retBox;
    }

    /**
     * recieptGraphic
     *
     * @return The graphic shown for each item on the receipt page, with a delete button
     * */

    public Node receiptGraphic() {
        VBox retBox = new VBox();

        Image trashImage = new Image("https://cdn-icons-png.flaticon.com/512/1843/1843344.png");
        ImageView trashView = new ImageView(trashImage);
        trashView.setFitWidth(20);
        trashView.setFitHeight(20);

        Button deleteButton = new Button("",trashView);

        deleteButton.setOnAction(actionEvent -> {ShoppingCart.removeItem(this);});

        Label headerLabel = new Label(item.name);

        HBox header = new HBox(deleteButton, headerLabel, Utils.spacer(), new Label(item.price+""));

        retBox.getChildren().add(header);

        for (FoodMod mod : mods) {
            if (mod.isInEffect()) {
                HBox modEntry = new HBox(Utils.spacer(), Utils.spacer(), new Label(mod.getName()), Utils.spacer(), new Label(""+mod.getPriceDifference()));
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
