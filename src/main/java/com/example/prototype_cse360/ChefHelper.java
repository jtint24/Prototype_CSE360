package com.example.prototype_cse360;

import java.util.ArrayList;

import com.example.prototype_cse360.ShoppingCart.OrderState;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ChefHelper {
    
    int numberOfChefs=0;
    int currentSelection;
    OrderListHelper oLHelper = new OrderListHelper();
    VBox mainBox = new VBox();
    private final ArrayList<ShoppingCart> orders = new ArrayList<>();

    final String[] CookingStates ={"Ready to Prep", "Ready to Cook", "Cooking"}; 

    ChefHelper(ArrayList<ShoppingCart> orders1, int added){
        if(added> 2){
        }
        else{
            orders.addAll(orders1);
        }
        mainBox.setStyle("-fx-background-color: #850E35;");
        mainBox.setSpacing(20);  
        mainBox.setPadding(new Insets(20));
        mainBox.setAlignment(Pos.CENTER);
    }

    public HBox TopBox(){
        ObservableList<Integer> options=  FXCollections.observableArrayList(
            1,2,3,4,5
        );
        ComboBox<Integer> chefsComboBox = new ComboBox(options);
        Label my_label=new Label("ASU Pizza Chefs");  
        my_label.setFont(new Font("Arial", 30));
        my_label.setTextFill(Color.web("#FFFFFF"));
        HBox topBox = new HBox();  
        topBox.setSpacing(120);  
        topBox.setPadding(new Insets(50));
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(my_label, chefsComboBox);  

        chefsComboBox.setOnAction(actionEvent -> {
            if(chefsComboBox.getValue()==null){
                numberOfChefs= 0;
            }
            else{    
                numberOfChefs= chefsComboBox.getValue();

            }
            UpdateChefList();
        });

        return topBox;
    }

    public VBox ChefRadios(){
        VBox chefRadios = new VBox();
        ToggleGroup optionsGroup = new ToggleGroup();
        for(int i=0; i<=numberOfChefs;i++){
            if(i==0){
                RadioButton option = new RadioButton("None");
                option.setToggleGroup(optionsGroup);
                if (i == currentSelection) {
                    option.setSelected(true);
                    option.requestFocus();
                }
                //int finalI = i;
                option.setOnAction(actionEvent -> {
                    //currentSelection = finalI;
                });
                chefRadios.getChildren().add(option);
            }
            else{
                RadioButton option = new RadioButton("Chef "+ i);
                option.setToggleGroup(optionsGroup);
                if (i == currentSelection) {
                    option.setSelected(true);
                    option.requestFocus();
                }
                option.setOnAction(actionEvent -> {
                    
                });
                chefRadios.getChildren().add(option);
            }
        }
        chefRadios.setAlignment(Pos.CENTER);
        return chefRadios;
    }

    public VBox CookingStateRadios(int j){
        VBox cookingRadios = new VBox();
        ToggleGroup optionsGroup = new ToggleGroup();

        for(int i=0; i<CookingStates.length;i++){
                RadioButton option = new RadioButton(CookingStates[i]);
                option.setToggleGroup(optionsGroup);

                if(orders.get(j).GetCookingState()==0.0 && i == 0){
                    option.setSelected(true);
                    option.requestFocus();
                }
                else if(orders.get(j).GetCookingState()==0.3 && i == 1){
                    option.setSelected(true);
                    option.requestFocus();
                }
                else if(orders.get(j).GetCookingState()==0.6 && i == 2){
                    option.setSelected(true);
                    option.requestFocus();
                }

                option.setOnAction(actionEvent -> {
                    if(option.isSelected()){
                        if(option.getText()=="Cooking"){
                            orders.get(j).SetCookingState(0.6);
                        }
                        else if(option.getText()=="Ready to Cook"){
                            orders.get(j).SetCookingState(0.3);
                        }
                        else{
                            orders.get(j).SetCookingState(0.0);
                        }
                    }
                });
                cookingRadios.getChildren().add(option);
        }
        cookingRadios.setAlignment(Pos.CENTER);
        return cookingRadios;
    }

    public VBox DeleteButton(int i){
        VBox buttonBox = new VBox();
        Button deleteButton = new Button("Order Complete");
        buttonBox.getChildren().add(deleteButton);
        deleteButton.setOnAction(actionEvent -> {
            orders.get(i).SetOrderState(OrderState.DONE);
            orders.get(i).SetCookingState(1);
            UpdateChefList();
        });
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }
    public VBox Order(int i){
        VBox newBox= new VBox();
        Label newLabel= new Label(orders.get(i).toString());
        newBox.getChildren().add(newLabel);
        newBox.setAlignment(Pos.CENTER);
        return newBox;
    }
    public ScrollPane OrderList(){
        for(int i=0; i<orders.size(); i++){
            if(orders.get(i).GetOrderState()==OrderState.ACCEPTED){
                HBox orderBox = new HBox();   
                
                orderBox.setSpacing(100);
                orderBox.setPadding(new Insets(10)); 
                orderBox.setAlignment(Pos.CENTER);
                orderBox.setStyle("-fx-background-color: #FFFFFF;");

                orderBox.getChildren().add(ChefRadios());  
                orderBox.getChildren().add(Order(i));
                orderBox.getChildren().add(CookingStateRadios(i));
                orderBox.getChildren().add(DeleteButton(i));
                mainBox.getChildren().add(orderBox);
            }

        }

        return new ScrollPane(mainBox);
    }

    public void UpdateChefList(){
        mainBox.getChildren().removeIf(node -> true);
        OrderList();
    }
}
