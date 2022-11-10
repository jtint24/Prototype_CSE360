package com.example.prototype_cse360;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    int numberOfChefs=0;
    int currentSelection;
    OrderListHelper oLHelper = new OrderListHelper();
    
    HBox mainBox = new HBox();

    private static final ArrayList<OrderListHelper> orders = new ArrayList<>();

    final static String[] OrderStates ={"Being Cooked", "Ready for Pick-up", "Being Delivered"}; 

    OPHelper(ArrayList<OrderListHelper> orders1, int added){
        if(added> 2){
        }
        else{
            orders.addAll(orders1);
        }
    }

    public Label TitleLabels(String contents){
        Label newLabel= new Label(contents);
        newLabel.setFont(new Font("Arial", 40));
        newLabel.setTextFill(Color.web("#FFFFFF"));
        return newLabel;
    }

    public HBox ColumnTitles(){
        HBox titles = new HBox();

        titles.setAlignment(Pos.CENTER);
        titles.setSpacing(150);
        titles.setPadding(new Insets(50));

        titles.getChildren().addAll(TitleLabels("Sent Orders"), TitleLabels("Submitted Orders"));
        return titles;
    }

    public VBox OrderStateRadios(){
        VBox orderRadios = new VBox();
        ToggleGroup optionsGroup = new ToggleGroup();

        for(int i=0; i<OrderStates.length;i++){
                RadioButton option = new RadioButton(OrderStates[i]);
                option.setToggleGroup(optionsGroup);
                if (i == currentSelection) {
                    option.setSelected(true);
                    option.requestFocus();
                }
                
                option.setOnAction(actionEvent -> {
                    
                });
                orderRadios.getChildren().add(option);
        }
        return orderRadios;
    }

    public VBox Order(int i){
        VBox newBox= new VBox();
        Label newLabel= new Label(orders.get(i).toString());
        
        newBox.getChildren().add(newLabel);
        return newBox;
    }
    public HBox OrderList(){
        VBox sent= new VBox();
        VBox submitted= new VBox();

        for(int i=0; i<orders.size(); i++){
            if(orders.get(i).orderState>=2){
                HBox orderBox = new HBox();   
                VBox orderBox1 = new VBox();
                VBox orderBox2 = new VBox();

                orderBox.setSpacing(30);
                orderBox.setPadding(new Insets(30));
                orderBox.setMinWidth(400);

                orderBox1.getChildren().add(Order(i));
                orderBox1.getChildren().add(PBarPlace(i));
                orderBox2.getChildren().add(OrderStateRadios());
                orderBox2.getChildren().add(DeleteButton(i));
                orderBox.getChildren().addAll(orderBox1, orderBox2);
                sent.getChildren().add(orderBox);
            }
            else{
                HBox orderBox = new HBox();   
                orderBox.setSpacing(30);
                orderBox.setPadding(new Insets(30));
                orderBox.setMinWidth(400);
                
                orderBox.getChildren().add(Order(i));
                orderBox.getChildren().add(SendButton(i));


                submitted.getChildren().add(orderBox);
            }
        }
        sent.setAlignment(Pos.CENTER_LEFT);
        submitted.setAlignment(Pos.CENTER_RIGHT);
        mainBox.getChildren().addAll(new ScrollPane(submitted), new ScrollPane(sent));
        return mainBox;
    }

    public VBox SendButton(int i){
        VBox buttonBox = new VBox();
        Button sendButton = new Button("Send to Chef");
        buttonBox.getChildren().add(sendButton);
        sendButton.setOnAction(actionEvent -> {
            orders.get(i).orderState=2;
            UpdateChefList();
        });
        return buttonBox;
    }

    public VBox DeleteButton(int i){
        VBox buttonBox = new VBox();
        Button deleteButton = new Button("Delete From Queue");
        buttonBox.getChildren().add(deleteButton);
        deleteButton.setOnAction(actionEvent -> {
            orders.remove(i);
            UpdateChefList();
        });
        return buttonBox;
    }

    public VBox PBarPlace(int i){
        VBox pbBox = new VBox();
        Label placeHolder = new Label("empty");
        if(orders.get(i).orderStateOfCooking==0.0 ){
            placeHolder = new Label("Ready to Prep");
        }
        else if(orders.get(i).orderStateOfCooking==0.3 ){
            placeHolder = new Label("Ready to Cook");
        }
        else if(orders.get(i).orderStateOfCooking==0.6 ){
            placeHolder = new Label("Cooking");
        }
        else if(orders.get(i).orderStateOfCooking==1 ){
            placeHolder = new Label("Cooked");
        }
        ProgressBar pb = new ProgressBar();
        pb.setProgress(orders.get(i).orderStateOfCooking);
        pbBox.getChildren().addAll(pb, placeHolder);
        return pbBox;
    }

    public void UpdateChefList(){
        mainBox.getChildren().removeIf(node -> true);
        OrderList();
    }
}
