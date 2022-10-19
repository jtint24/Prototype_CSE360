package com.example.prototype_cse360;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProtoController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}