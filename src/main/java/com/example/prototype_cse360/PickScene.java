package com.example.prototype_cse360;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

class VBoxPicker {
    private final String[] options;
    private final EventHandler<ActionEvent>[] eventHandlers;
    public VBoxPicker(String[] _options, EventHandler<ActionEvent>... _eventhandlers) {
        options = _options;
        eventHandlers = _eventhandlers;
    }
    public VBox graphic() {
        VBox retBox = new VBox();
        for(int i = 0; i<options.length; i++) {
            String option = options[i];
            EventHandler<ActionEvent> eventHandler = eventHandlers[i];

            Button newButton = new Button(option);
            newButton.setOnAction(eventHandler);
            retBox.getChildren().add(new Button(option));

        }
        retBox.setPadding(new Insets(30));
        retBox.setSpacing(10);
        return retBox;
    }
}
