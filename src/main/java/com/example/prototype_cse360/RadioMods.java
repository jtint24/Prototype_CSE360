package com.example.prototype_cse360;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RadioMods extends FoodMod {
    String[] optionNames;
    double[] optionPriceDiffs;
    int currentSelection;

    RadioMods(String[] _optionNames, double[] _optionPriceDiffs, int _currentSelection) {
        super(_optionNames[_currentSelection], _optionPriceDiffs[_currentSelection]);
        optionNames = _optionNames;
        optionPriceDiffs = _optionPriceDiffs;
        currentSelection = _currentSelection;
    }

    @Override
    Node graphic() {
        VBox retBox = new VBox();
        ToggleGroup optionsGroup = new ToggleGroup();

        for (int i = 0; i<optionNames.length; i++) {
            RadioButton option = new RadioButton(optionNames[i] +"\t" + optionPriceDiffs[i]);
            option.setToggleGroup(optionsGroup);
            if (i == currentSelection) {
                option.setSelected(true);
                option.requestFocus();
            }
            int finalI = i;
            option.setOnAction(actionEvent -> {
                currentSelection = finalI;
            });
            retBox.getChildren().add(new HBox(Utils.Spacer(),option));
        }

        return retBox;
    }
}
