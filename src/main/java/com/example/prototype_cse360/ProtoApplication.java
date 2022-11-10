package com.example.prototype_cse360;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProtoApplication extends Application {

    private Stage primaryStage;
    private TempSupportClass caller= new TempSupportClass();
    private final FoodItem[] foodItems = Utils.getFoodItems();
    private  final ArrayList<OrderListHelper> orders = caller.ListOfOrders();

    private int added=0;
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

        primaryStage.setTitle("CSE 360 Prototype");
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

    private Scene chefScene() { 
        added++;
        ChefHelper ch= new ChefHelper(orders, added);
        VBox mainBox = new VBox();
        Button routingButton = new Button("to OPAgent");
        routingButton.setOnAction(event -> {primaryStage.setScene(orderManagerScene());});

        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(25);
        mainBox.setStyle("-fx-background-color: #850E35;");
        
        mainBox.getChildren().addAll(ch.TopBox(),ch.OrderList(), routingButton);

        Scene scene=new Scene(mainBox,800,1020);
        return scene;
        //return new Scene(new Label("chefScene"));
    }

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

        bottomNavigation.getChildren().addAll(prevButton, Utils.spacer(), Utils.spacer(), nextButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        availBox.getChildren().add(bottomNavigation);

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
        VBox mainBox = new VBox(
                new Label("Please Enter Your Payment Info:"),
                Utils.spacer(),
                Utils.labelledTextBox("Name:"),
                Utils.labelledTextBox("ASURITE ID:"),
                Utils.labelledTextBox("ASU Email:"),
                Utils.labelledTextBox("Credit Card Number:"),
                new HBox(
                    Utils.labelledTextBox("Security Code:"),
                    Utils.labelledTextBox("Expiration Date:")
                )
        );

        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(okScene());});
        Button prevButton = new Button("Back");
        prevButton.setOnAction(event -> {primaryStage.setScene(cartScene());});

        bottomNavigation.getChildren().addAll(prevButton, Utils.spacer(), Utils.spacer(), nextButton);
        bottomNavigation.setAlignment(Pos.BOTTOM_RIGHT);

        mainBox.getChildren().add(bottomNavigation);

        return new Scene(mainBox);
    }

    /**
     * okScene
     *
     * @return The scene shown to the customer once they've completed their order
     * */

    private Scene okScene() {
        return new Scene(new Label("Thank you! Your order is on the way!"));
    }

    /**
     * orderManagerScene
     *
     * @return The main scene for the order manager
     * */

    private Scene orderManagerScene() { added++;
        OPHelper ch= new OPHelper(orders, added);
        VBox mainBox = new VBox();
        Button routingButton = new Button("To CHef");
        routingButton.setOnAction(event -> {primaryStage.setScene(chefScene());});


        mainBox.getChildren().addAll(ch.ColumnTitles(), ch.OrderList(), routingButton );

        Scene scene=new Scene(mainBox,1000,1200);
        return scene;
        //return new Scene(new Label("orderManagerScene"));
    }

}