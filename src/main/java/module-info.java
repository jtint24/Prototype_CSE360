module com.example.prototype_cse360 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prototype_cse360 to javafx.fxml;
    exports com.example.prototype_cse360;
}