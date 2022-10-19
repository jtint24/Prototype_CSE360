package com.example.prototype_cse360;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProtoApplication extends Application {

    private Stage primaryStage;

    private FoodItem[] foodItems = {
            new FoodItem("Pepperoni Pizza", 5.99, FoodItem.Category.PIZZA, new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsfaA0ZWjkuzaSgO1BiLemMVp58QxGbBxzew&usqp=CAU")),
            new FoodItem("Cheese Pizza", 4.99, FoodItem.Category.PIZZA, new Image("https://images.contentstack.io/v3/assets/bltbb619fd5c667ba2d/blt2d4e43bcebe1548e/60ca60fa1e0505677a881227/Cheese_Pizza.jpg")),
            new FoodItem("Pineapple Pizza", 5.49, FoodItem.Category.PIZZA, new Image("https://www.kayscleaneats.com/wp-content/uploads/2020/07/unadjustednonraw_thumb_a8b0.jpg")),
    };

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Scene mainScene = new Scene(mainPicker());
        primaryStage.setTitle("CSE 360 Prototype");
        primaryStage.setScene(mainScene);
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
        HashMap<FoodItem.Category,HBox> categories = new HashMap<>();
        VBox retBox = new VBox();

        for (FoodItem.Category category : FoodItem.Category.values()) {
            categories.put(category, new HBox());
        }
        for (FoodItem foodItem : foodItems) {
            categories.get(foodItem.getCategory()).getChildren().add(foodItem.graphicButton());
        }
        for (Map.Entry<FoodItem.Category, HBox> categoryHBoxEntry : categories.entrySet()) {
            HBox hbox = categoryHBoxEntry.getValue();
            hbox.setSpacing(30);
            hbox.setPadding(new Insets(30));

            retBox.getChildren().addAll(new Label(categoryHBoxEntry.getKey().toString()), hbox);
        }

        return new Scene(retBox);
    }
    private Scene orderManagerScene() {return new Scene(new Label("orderManagerScene"));}

}