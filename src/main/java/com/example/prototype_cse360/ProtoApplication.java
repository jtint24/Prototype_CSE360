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

public class ProtoApplication extends Application {

    private Stage primaryStage;

    private FoodItem[] foodItems = {
            new FoodItem("Pepperoni Pizza", new Image("https://modpizza.com/wp-content/uploads/2021/12/Website-Maddy.png"), 5.99),
            new FoodItem("Cheese Pizza", new Image("https://images.contentstack.io/v3/assets/bltbb619fd5c667ba2d/blt2d4e43bcebe1548e/60ca60fa1e0505677a881227/Cheese_Pizza.jpg"), 4.99),
            new FoodItem("Pineapple Pizza", new Image("https://www.kayscleaneats.com/wp-content/uploads/2020/07/unadjustednonraw_thumb_a8b0.jpg"), 5.49),
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
        HBox retBox = new HBox();
        for (FoodItem foodItem : foodItems) {
            retBox.getChildren().add(foodItem.graphic());
        }
        retBox.setSpacing(30);
        retBox.setPadding(new Insets(30));
        return new Scene(retBox);
    }
    private Scene orderManagerScene() {return new Scene(new Label("orderManagerScene"));}

}