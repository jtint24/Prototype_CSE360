package com.example.prototype_cse360;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;

public class Utils {
    public static Region Spacer() {
        Region retRegion = new Region();
        //retRegion.setPadding(new Insets(30));
        retRegion.setPrefWidth(30);
        return retRegion;
    }
}
