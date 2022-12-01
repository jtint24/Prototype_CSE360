package com.example.prototype_cse360;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProtoApplication extends Application {

    private final VBox receiptGraphic = new VBox();
    private Stage primaryStage;     // The stage that is shown at any given time
    private ShoppingCart mainCart;
    private final FoodItem[] foodItems = Utils.getFoodItems();
    private ArrayList<ShoppingCart> orders;

    /**
     * start
     *
     * The method that runs on the application setup. Sets the stage and inserts primaryScene as the main
     * scene
     * */

    @Override
    public void start(Stage stage) throws IOException {
        orders = Utils.readOrdersFromFiles(System.getProperty("user.dir"));
        mainCart = new ShoppingCart();

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
        Button chefButton = Utils.makeCleanButton("Chef");
        Button orderManagerButton = Utils.makeCleanButton("Order Manager");
        Button customerButton = Utils.makeCleanButton("Customer");

        chefButton.setOnAction(event -> {primaryStage.setScene(chefScene());});
        orderManagerButton.setOnAction(event -> {primaryStage.setScene(orderManagerScene());});
        customerButton.setOnAction(event -> {primaryStage.setScene(customerScene(mainCart));});

        retBox.getChildren().addAll(chefButton,orderManagerButton,customerButton);

        retBox.setPadding(new Insets(30));
        retBox.setSpacing(10);
        retBox.setStyle("-fx-background-color: #850E35;");
        return retBox;
    }

    /**
     * chefScene
     *
     * @return The main scene for the chef
     * */

    private Scene chefScene() {

        //Chef helper is helping build the main chef scene
        ChefHelper ch= new ChefHelper(orders);
        //Mainbox is what holds the different aspects, gridpane used for the way it lays out things
        GridPane mainBox = new GridPane();

        //Button for switching between the OPAgent and Chef scene during testing
        Button routingButton = new Button("to OPAgent");
        routingButton.setOnAction(event -> {primaryStage.setScene(orderManagerScene());});

        //Centers and makes the background maroon
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setStyle("-fx-background-color: #850E35;");
        
        mainBox.addRow(0, ch.TopBox());
        mainBox.addRow(1, ch.OrderList());
        mainBox.addRow(2, routingButton);

        Scene scene=new Scene(mainBox,1000,1200);
        return scene;
    }

    /**
     * customerScene
     *
     * @return The main scene for the customer, the menu where they can select items
     * */

    private Scene customerScene(ShoppingCart mainShoppingCart) {
        HashMap<FoodItem.Category,HBox> sortedHBoxes = new HashMap<>();
        VBox retBox = new VBox();


        for (FoodItem.Category category : FoodItem.Category.values()) {
            sortedHBoxes.put(category, new HBox());
            sortedHBoxes.get(category).setStyle("-fx-background-color: #850E35");
            sortedHBoxes.get(category).getChildren().add(Utils.spacer());

        }
        for (FoodItem foodItem : foodItems) {
            sortedHBoxes.get(foodItem.getCategory()).getChildren().add(foodItem.graphicButton(mainShoppingCart));
        }
        VBox foodItemsBox = new VBox();
        foodItemsBox.setStyle("-fx-background-color: #850E35");
       // foodItemsBox.setSpacing(20);
        for (FoodItem.Category category : FoodItem.Category.values()) {
            HBox hbox = sortedHBoxes.get(category);
            hbox.setSpacing(30);
            hbox.setPadding(new Insets(0));

            Label categoryLabel = new Label("\n\t"+category.toString());
            categoryLabel.setTextFill(Color.WHITE);

            if (hbox.getChildren().size() > 3) {
                ScrollPane scrollableHbox = new ScrollPane(hbox);
                scrollableHbox.setFitToHeight(true);
                scrollableHbox.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollableHbox.setPannable(false);
                scrollableHbox.setPrefHeight(hbox.getMaxHeight());
                scrollableHbox.setStyle("-fx-background-color: #850E35");

                foodItemsBox.getChildren().addAll(categoryLabel, scrollableHbox);
            } else {
                foodItemsBox.getChildren().addAll(categoryLabel, hbox);
            }
            // adjusting header text , add new object
        }

        ScrollPane mainScroll = new ScrollPane(foodItemsBox);
        mainScroll.setFitToWidth(true);


        retBox.getChildren().add(mainScroll);

        HBox bottomNavigation = new HBox();
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(modifierScene(mainCart));});

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

    private Scene modifierScene(ShoppingCart mainShoppingCart) {
        VBox availBox = new VBox();

        Label headerLabel = new Label("Make Customizations!");
        headerLabel.setTextAlignment(TextAlignment.CENTER);

        availBox.getChildren().add(headerLabel);

        for (OrderedItem orderedItem : mainCart.getOrderedItems() ) {
            availBox.getChildren().add(orderedItem.modifiersGraphic());
        }

        availBox.setSpacing(20);

        HBox bottomNavigation = new HBox();
        Button nextButton = Utils.makeCleanButton("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(cartScene());});
        Button prevButton = Utils.makeCleanButton("Back");
        prevButton.setOnAction(event -> {primaryStage.setScene(customerScene(mainShoppingCart));});

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
        updateReceiptGraphic();

        VBox mainBox = new VBox();

        Label headerLabel = new Label("Here's Your Cart!");
        headerLabel.setTextAlignment(TextAlignment.CENTER);

        mainBox.getChildren().add(headerLabel);

        mainBox.getChildren().add(receiptGraphic());

        HBox bottomNavigation = new HBox();
        Button nextButton = Utils.makeCleanButton("Next");
        nextButton.setOnAction(event -> {primaryStage.setScene(paymentScene());});
        Button prevButton = Utils.makeCleanButton("Back");
        prevButton.setOnAction(event -> {primaryStage.setScene(modifierScene(mainCart));});

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
        TextField nameField = new TextField();
        TextField asuriteIDField = new TextField();
        TextField asuEmail = new TextField();
        TextField ccNumber = new TextField();
        TextField securityCode = new TextField();
        TextField expDate = new TextField();

        String errorString = "";
        Label errorLabel = new Label(errorString);
        errorLabel.setStyle("-fx-text-fill: red");

        VBox mainBox = new VBox(
                new Label("Please Enter Your Payment Info:"),
                Utils.spacer(),
                Utils.labelledTextBox("Name:", nameField),
                Utils.labelledTextBox("ASU ID:", asuriteIDField),
                Utils.labelledTextBox("ASU Email:", asuEmail),
                Utils.labelledTextBox("Credit Card Number:", ccNumber),
                new HBox(
                    Utils.labelledTextBox("Security Code:", securityCode),
                    Utils.labelledTextBox("Expiration Date:", expDate)
                ),
                errorLabel
        );

        HBox bottomNavigation = new HBox();
        Button nextButton = Utils.makeCleanButton("Next");
        nextButton.setOnAction(event -> {
            errorLabel.setText("");
            if (nameField.getText().isBlank()) {
                errorLabel.setText(errorLabel.getText() + "Please Enter a Name\n");
            }
            if (!Utils.isAsuEmail(asuEmail.getText())) {
                errorLabel.setText(errorLabel.getText() + "Please Enter a Valid ASU Email\n");
            }
            if (!Utils.isNumber(asuriteIDField.getText(), 10)) {
                errorLabel.setText(errorLabel.getText() + "Please Enter a Valid ASU ID\n");
            }
            if (!Utils.isNumber(ccNumber.getText(), 16)) {
                errorLabel.setText(errorLabel.getText() + "Please Enter a Valid Credit Card Number\n");
            }
            if (!Utils.isNumber(securityCode.getText(), 3)) {
                errorLabel.setText(errorLabel.getText() + "Please Enter a Valid Security Code\n");
            }
            if (!Utils.isValidDate(expDate.getText())) {
                errorLabel.setText(errorLabel.getText() + "Please Enter a Valid Expiration Date\n");
            }
            if (errorLabel.getText().length() != 0) {
                return;
            }
            primaryStage.setScene(okScene());
            mainCart.setPaymentInformation(ccNumber.getText(), Integer.parseInt(securityCode.getText()), expDate.getText(), asuEmail.getText(), asuriteIDField.getText());
            mainCart.writeToFile();
        });
        Button prevButton = Utils.makeCleanButton("Back");
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

    private Scene orderManagerScene() { 
        OPHelper ch = new OPHelper(orders);
        GridPane mainBox = new GridPane();
        Button routingButton = Utils.makeCleanButton("To Chef");
        routingButton.setOnAction(event -> {primaryStage.setScene(chefScene());});

        mainBox.setAlignment(Pos.CENTER);
        
        mainBox.setStyle("-fx-background-color: #850E35;");


        mainBox.addRow(0, ch.ColumnTitles());
        mainBox.addRow(1, ch.OrderList());
        mainBox.addRow(2, routingButton );

        Scene scene = new Scene(mainBox,1000,1200);
        return scene;
    }

    public Node receiptGraphic() {
        return receiptGraphic;
    }

    /**
     * updateReceiptGraphic
     *
     * Updates the interactive receipt graphic with the graphics from the ordered items
     * */

    public void updateReceiptGraphic() {
        receiptGraphic.getChildren().removeIf(node -> true);
        for (OrderedItem orderedItem : mainCart.getOrderedItems()) {
            receiptGraphic.getChildren().add(orderedItem.receiptGraphic(mainCart, this));
        }

    }

}