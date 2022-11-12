package com.example.prototype_cse360;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;


import java.io.IOException;
import java.util.HashMap;

public class ProtoApplication extends Application {

    private Stage primaryStage;     // The stage that is shown at any given time

    private final FoodItem[] foodItems = Utils.getFoodItems();

    /**
     * start
     *
     * The method that runs on the application setup. Sets the stage and inserts primaryScene as the main
     * scene
     * */

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Scene mainScene = new Scene(mainPicker());

        primaryStage.setTitle("Sun Devil Pizza");
        primaryStage.setScene(mainScene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * mainPicker
     *
     * @return The picker menu for each of the three different kinds of users
     * */
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

    /**
     * chefScene
     *
     * @return The main scene for the chef
     * */

    private Scene chefScene() { return new Scene(new Label("chefScene"));}

    /**
     * customerScene
     *
     * @return The main scene for the customer, the menu where they can select items
     * */

    private Scene customerScene() {
        HashMap<FoodItem.Category,HBox> sortedHBoxes = new HashMap<>();
        VBox retBox = new VBox();


        for (FoodItem.Category category : FoodItem.Category.values()) {
            sortedHBoxes.put(category, new HBox());
        }
        for (FoodItem foodItem : foodItems) {
            sortedHBoxes.get(foodItem.getCategory()).getChildren().add(foodItem.graphicButton());
        }
        VBox foodItemsBox = new VBox();
        for (FoodItem.Category category : FoodItem.Category.values()) {
            HBox hbox = sortedHBoxes.get(category);
            hbox.setSpacing(30);
            hbox.setPadding(new Insets(30));

            foodItemsBox.getChildren().addAll(new Label(category.toString()), hbox);
            // adjusting header text , add new object
        }


        retBox.getChildren().add(new ScrollPane(foodItemsBox));

        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(modifierScene());});

        bottomNavigation.getChildren().add(nextButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        retBox.getChildren().add(bottomNavigation);


        return new Scene(retBox);
    }

    /**
     * modifierScene
     *
     * @return The scene where modifiers can be added
     * */

    private Scene modifierScene() {
        VBox availBox = new VBox();
        Label headerLabel = new Label("Make Customizations!");
        headerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        headerLabel.setTextFill(Color.web("#000000"));
        headerLabel.setStyle("-fx-background-color: f0a500");
        //headerLabel.setTextAlignment(TextAlignment.CENTER);

        GridPane newgridPane = new GridPane();
        newgridPane.add(headerLabel, 0, 0);
        newgridPane.setPadding(new Insets(10, 0, 0, 130));//bottom/left/top/right

        availBox.getChildren().add(newgridPane);

        for (OrderedItem orderedItem : ShoppingCart.getOrderedItems() ) {
            availBox.getChildren().add(orderedItem.modifiersGraphic());
        }

        availBox.setSpacing(20);


        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        GridPane gridPane = new GridPane();
        gridPane.add(nextButton , 0, 0);
        gridPane.setPadding(new Insets(0, 0, 10, 30));//bottom/left/top/right

        nextButton.setOnAction(event -> {primaryStage.setScene(cartScene());});
        Button prevButton = new Button("Back");
        GridPane gridPane1 = new GridPane();
        gridPane1.add(prevButton , 0, 0);
        gridPane1.setPadding(new Insets(0, 0, 10, 260));//bottom/left/top/right
        prevButton.setOnAction(event -> {primaryStage.setScene(customerScene());});

        bottomNavigation.getChildren().addAll(gridPane1, gridPane);
        //bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        availBox.getChildren().add(bottomNavigation);
        availBox.setStyle("-fx-background-color: #850E35");

        return new Scene(availBox);
    }

    /**
     * cartScene
     *
     * @return The scene where the customer can change or review the shopping cart
     * */

    private Scene cartScene() {
        ShoppingCart.updateReceiptGraphic();

        VBox mainBox = new VBox();

        Label headerLabel = new Label("Here's Your Cart!");
        headerLabel.setTextAlignment(TextAlignment.CENTER);

        mainBox.getChildren().add(headerLabel);

        mainBox.getChildren().add(ShoppingCart.receiptGraphic());

        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(paymentScene());});
        Button prevButton = new Button("Back");
        prevButton.setOnAction(event -> {primaryStage.setScene(modifierScene());});

        bottomNavigation.getChildren().addAll(prevButton, Utils.spacer(), Utils.spacer(), nextButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        mainBox.getChildren().add(bottomNavigation);

        return new Scene(mainBox);
    }

    /**
     * paymentScene
     *
     * @return The main scene to enter payment information
     * */

    private Scene paymentScene() {

        Label message = new Label(" Please Enter Your Payment Info:");
        message.setTextFill(Color.web("#ffffff"));
        message.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        GridPane pane = new GridPane();
        pane.add(message , 0, 0);
        pane.setPadding(new Insets(20, 0, 0, 0));//bottom/left/top/right
        pane.setPrefHeight(50);

        pane.setStyle("-fx-background-color: #850E35");

        VBox mainBox = new VBox(
                pane,
                Utils.labelledTextBox(" Name:"),
                Utils.labelledTextBox(" ASURITE ID:"),
                Utils.labelledTextBox(" ASU Email:"),
                Utils.labelledTextBox(" Credit Card Number:"),
                new HBox(
                    Utils.labelledTextBox(" Security Code:"),
                    Utils.labelledTextBox(" Expiration Date:")
                )
        );

        mainBox.setSpacing(20);

        HBox bottomNavigation = new HBox();

        Button nextButton = new Button("Place Order");
        nextButton.setStyle("-fx-background-color: #f0a500");
        nextButton.setMinWidth(100);
        GridPane gridPane = new GridPane();
        gridPane.add(nextButton, 0, 0);
        gridPane.setPadding(new Insets(20, 0, 0, 260));//bottom/left/top/right

        nextButton.setOnAction(event -> {primaryStage.setScene(okScene());});
        Button prevButton = new Button("Back");
        GridPane gridPane1 = new GridPane();
        gridPane1.add(prevButton , 0, 0);
        gridPane1.setPadding(new Insets(20, 0, 0, 20));//bottom/left/top/right

        prevButton.setOnAction(event -> {primaryStage.setScene(cartScene());});

        bottomNavigation.getChildren().addAll(gridPane1, Utils.spacer(), Utils.spacer(), gridPane);
        //bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);
        bottomNavigation.setStyle("-fx-background-color: #850E35");
        bottomNavigation.setPrefHeight(70);

        mainBox.getChildren().add(bottomNavigation);
        //mainBox.setSpacing(20);
        mainBox.setStyle("-fx-background-color: #850E35");
        mainBox.setStyle("-fx-background-color: #ffffff");

        return new Scene(mainBox);
    }

    /**
     * okScene
     *
     * @return The scene shown to the customer once they've completed their order
     * */

    private Scene okScene() {

        // ready to cook, cooking, ready
        VBox confirmation = new VBox();
        confirmation.setStyle("-fx-background-color: #850E35");

        Label message1 = new Label("Tracker Your Order");
        //message1.setPrefWidth(150);
        message1.setStyle("-fx-background-color: #f0a500");
        GridPane gridPane = new GridPane();
        gridPane.add(message1, 0, 0);
        gridPane.setPadding(new Insets(30, 0, 0, 150));//bottom/left/top/right

        Label message2 = new Label("Order Status:");
        message2.setTextFill(Color.web("#ffffff"));

        GridPane gridPane1 = new GridPane();
        gridPane1.add(message2, 0, 0);
        gridPane1.setPadding(new Insets(50, 0, 0, 165));//bottom/left/top/right

        Label message3 = new Label("ACCEPTED! ");
        message3.setStyle("-fx-background-color: #f0a500");
        message3.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        GridPane gridPane2 = new GridPane();
        gridPane2.add(message3, 0, 0);
        gridPane2.setPadding(new Insets(50, 0, 0, 150));//bottom/left/top/right

        confirmation.getChildren().addAll(gridPane, gridPane1, gridPane2);

        return new Scene(confirmation);
    }

    /**
     * orderManagerScene
     *
     * @return The main scene for the order manager
     * */

    private Scene orderManagerScene() {return new Scene(new Label("orderManagerScene"));}

}