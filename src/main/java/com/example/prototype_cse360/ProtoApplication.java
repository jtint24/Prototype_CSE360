package com.example.prototype_cse360;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class ProtoApplication extends Application {

    private Stage primaryStage;

    private FoodItem[] foodItems = {
            new FoodItem("Pepperoni Pizza", 5.99, FoodItem.Category.PIZZA, new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsfaA0ZWjkuzaSgO1BiLemMVp58QxGbBxzew&usqp=CAU"),
                    new OptionalMod("Extra Sauce", 1.00),
                    new OptionalMod("Extra Cheese", 1.00),
                    new RadioMods(new String[]{"Small", "Medium", "Large"}, new double[] {0, 1.00, 2.00}, 1)
            ),
            new FoodItem("Cheese Pizza", 4.99, FoodItem.Category.PIZZA, new Image("https://images.contentstack.io/v3/assets/bltbb619fd5c667ba2d/blt2d4e43bcebe1548e/60ca60fa1e0505677a881227/Cheese_Pizza.jpg")),
            new FoodItem("Pineapple Pizza", 5.49, FoodItem.Category.PIZZA, new Image("https://www.kayscleaneats.com/wp-content/uploads/2020/07/unadjustednonraw_thumb_a8b0.jpg")),
    };

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Scene mainScene = new Scene(mainPicker());
        primaryStage.setTitle("CSE 360 Prototype");
        primaryStage.setScene(mainScene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public VBox mainPicker() {
        VBox retBox = new VBox();
        Button chefButton = new Button("Chef");
        Button orderManagerButton = new Button("Order Manager");
        Button customerButton = new Button("Customer");

        chefButton.setOnAction(event -> {primaryStage.setScene(chefScene());});
        orderManagerButton.setOnAction(event -> {primaryStage.setScene(orderManagerScene());});
        customerButton.setOnAction(event -> {primaryStage.setScene(customerScene());});

        retBox.getChildren().addAll(chefButton,orderManagerButton,customerButton);

        retBox.setPadding(new Insets(30));
        retBox.setSpacing(10);
        return retBox;
    }

    private Scene chefScene() { return new Scene(new Label("chefScene"));}
    private Scene customerScene() {
        HashMap<FoodItem.Category,HBox> sortedHBoxes = new HashMap<>();
        VBox retBox = new VBox();

        for (FoodItem.Category category : FoodItem.Category.values()) {
            sortedHBoxes.put(category, new HBox());
        }
        for (FoodItem foodItem : foodItems) {
            sortedHBoxes.get(foodItem.getCategory()).getChildren().add(foodItem.graphicButton());
        }
        for (FoodItem.Category category : FoodItem.Category.values()) {
            HBox hbox = sortedHBoxes.get(category);
            hbox.setSpacing(30);
            hbox.setPadding(new Insets(30));

            retBox.getChildren().addAll(new Label(category.toString()), hbox);
        }

        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(modifierScene());});

        bottomNavigation.getChildren().add(nextButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        retBox.getChildren().add(bottomNavigation);


        return new Scene(retBox);
    }
    private Scene modifierScene() {
        VBox availBox = new VBox();

        Label headerLabel = new Label("Make Customizations!");
        headerLabel.setTextAlignment(TextAlignment.CENTER);

        availBox.getChildren().add(headerLabel);

        for (OrderedItem orderedItem : ShoppingCart.getOrderedItems() ) {
            availBox.getChildren().add(orderedItem.modifiersGraphic());
        }

        availBox.setSpacing(20);

        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(cartScene());});
        Button prevButton = new Button("Back");
        prevButton.setOnAction(event -> {primaryStage.setScene(customerScene());});

        bottomNavigation.getChildren().addAll(prevButton, Utils.Spacer(), Utils.Spacer(), nextButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        availBox.getChildren().add(bottomNavigation);

        return new Scene(availBox);
    }
    private Scene cartScene() {
        VBox mainBox = new VBox();
        mainBox.getChildren().add(ShoppingCart.receiptGraphic());
        return new Scene(mainBox);
    }
    private Scene paymentScene() {
        VBox mainBox = new VBox();
        mainBox.getChildren().add(ShoppingCart.receiptGraphic());
        return new Scene(mainBox);
    }
    private Scene okScene() {
        return new Scene(new Label(""));
    }

    private Scene orderManagerScene() {return new Scene(new Label("orderManagerScene"));}

}