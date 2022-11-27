package com.example.prototype_cse360;

import java.util.ArrayList;

import com.example.prototype_cse360.ShoppingCart.OrderState;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class OPHelper {

    int numberOfChefs = 0;

    HBox mainBox = new HBox();

    private static ArrayList<ShoppingCart> orders;

    OPHelper(ArrayList<ShoppingCart> _orders) {

        orders = _orders;
    }

    public Label emptyLabel(){
        Label emptyLabel = new Label("Empty");
        emptyLabel.setMinWidth(400);
        emptyLabel.setAlignment(Pos.CENTER);
        emptyLabel.setFont(new Font("Arial", 80));
        emptyLabel.setTextFill(Color.web("#000000"));
        return emptyLabel;
    }

    /**
     * TitleLabels
     *
     * @return The labels for titles in the OPHelper view
     * */

    public Label TitleLabels(String contents) {
        Label newLabel = new Label(contents);
        newLabel.setFont(new Font("Arial", 40));
        newLabel.setTextFill(Color.web("#FFFFFF"));
        return newLabel;
    }

    /**
     * ColumnTitles
     *
     * @return The titles for columns in the OPHelper view
     * */

    public HBox ColumnTitles() {
        HBox titles = new HBox();

        titles.setAlignment(Pos.CENTER);
        titles.setSpacing(150);
        titles.setPadding(new Insets(50));

        titles.getChildren().addAll(TitleLabels("Sent Orders"), TitleLabels("Submitted Orders"));
        return titles;
    }

    /**
     * OrderStateRadios
     *
     * @param order the order to generate the radio buttons for
     * @return The set of Radio buttons that control the orderstate for a given order
     * */

    public VBox OrderStateRadios(ShoppingCart order) {
        VBox orderRadios = new VBox();
        ToggleGroup optionsGroup = new ToggleGroup();

        for(int i=0; i<OrderState.values().length;i++){
                OrderState activeState = OrderState.values()[i];

                if (!activeState.opCanAssign()) {
                    continue;
                }

                RadioButton option = new RadioButton(activeState.toString());
                option.setToggleGroup(optionsGroup);
                if (activeState == order.getOrderState()) {
                    option.setSelected(true);
                    option.requestFocus();
                }
                
                option.setOnAction(actionEvent -> {
                    order.setOrderState(activeState);
                });
                orderRadios.getChildren().add(option);
        }

        return orderRadios;
    }


    /**
     * Order
     *
     * @return The view for a given order
     * */

    public VBox Order(int i) {
        VBox newBox= new VBox();
        Label newLabel = new Label(orders.get(i).toString());
        
        newBox.getChildren().add(newLabel);
        return newBox;
    }

    /**
     * OrderList
     *
     * Returns the view for all of the orders in a list
     * */

    public HBox OrderList() {
        VBox sent = new VBox();
        VBox submitted = new VBox();

        boolean sentEmpty = true, submittedEmpty = true;
        Label emptyLabel1 =emptyLabel();
        Label emptyLabel2 =emptyLabel();
        
        if(orders.size() == 0){

        }
        else{
            for(int i=0; i<orders.size(); i++){
                if(orders.get(i).getOrderState() == OrderState.RECEIVED || orders.get(i).getOrderState().chefCanAssign()){
                    sentEmpty = false;
                    HBox orderBox = new HBox();   
                    VBox orderBox1 = new VBox();
                    VBox orderBox2 = new VBox();

                    orderBox.setSpacing(30);
                    orderBox.setPadding(new Insets(30));
                    orderBox.setMinWidth(400);

                    orderBox1.getChildren().add(Order(i));
                    orderBox1.getChildren().add(PBarPlace(i));
                    orderBox2.getChildren().add(OrderStateRadios(orders.get(i)));
                    orderBox2.getChildren().add(DeleteButton(i));
                    orderBox.getChildren().addAll(orderBox1, orderBox2);
                    sent.getChildren().add(orderBox);
                } 
                else if(orders.get(i).getOrderState() == OrderState.PREPARED || orders.get(i).getOrderState() == OrderState.DELIVERED) {
                    submittedEmpty= false;
                    HBox orderBox = new HBox();   
                    orderBox.setSpacing(30);
                    orderBox.setPadding(new Insets(30));
                    orderBox.setMinWidth(420);
                
                    orderBox.getChildren().add(Order(i));
                    orderBox.getChildren().add(SendButton(i));


                    submitted.getChildren().add(orderBox);
                }
            }
        }

        if (sentEmpty) {
            sent.getChildren().add(emptyLabel1);
            sent.setMinWidth(400);
        }

        if (submittedEmpty) {
            submitted.getChildren().add(emptyLabel2);
            submitted.setMinWidth(400);
        }

        mainBox.getChildren().addAll(new ScrollPane(submitted), new ScrollPane(sent));
        return mainBox;
    }

    /**
     * SendButton
     *
     * @param i the index of the order to assign for the send button
     * @return A send button for a given order
     * */

    public VBox SendButton(int i) {
        VBox buttonBox = new VBox();
        Button sendButton = new Button("Send to Chef");
        buttonBox.getChildren().add(sendButton);
        sendButton.setOnAction(actionEvent -> {
            orders.get(i).setOrderState(OrderState.ASSIGNED_TO_CHEF);
            UpdateChefList();
        });
        return buttonBox;
    }

    /**
     * DeleteButton
     *
     * @param i the index of the order to assign for the delete button
     * @return A delete button for a given order
     * */

    public VBox DeleteButton(int i) {
        VBox buttonBox = new VBox();
        Button deleteButton = new Button("Delete From Queue");
        buttonBox.getChildren().add(deleteButton);
        deleteButton.setOnAction(actionEvent -> {
            ShoppingCart orderToRemove = orders.remove(i);
            orderToRemove.deleteFile();
            UpdateChefList();
        });
        return buttonBox;
    }

    /**
     * PBarPlace
     *
     * @param i the index of the order to assign for the progress bar
     * @return A progress bar for a given order
     * */

    public VBox PBarPlace(int i) {
        VBox pbBox = new VBox();
        Label placeHolder = new Label(orders.get(i).getOrderState().name());
        ProgressBar pb = new ProgressBar();
        pb.setProgress(orders.get(i).getOrderState().getProgress());
        pbBox.getChildren().addAll(pb, placeHolder);
        return pbBox;
    }

    public void UpdateChefList() {
        mainBox.getChildren().removeIf(node -> true);
        OrderList();
    }
}
