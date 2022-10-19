package com.example.prototype_cse360;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ProtoApplication extends Application {

    private Stage primaryStage;
    private Scene chefScene() { return new Scene(new Label("chefScene"));}
    private Scene customerScene() {return new Scene(new Label("customerScene"));}
    private Scene orderManagerScene() {return new Scene(new Label("orderManagerScene"));}
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
}