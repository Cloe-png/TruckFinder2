module org.example.truckfinder2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;


    opens org.example.truckfinder2 to javafx.fxml;
    exports org.example.truckfinder2;
    exports org.example.truckfinder2.viewmodels;
    opens org.example.truckfinder2.viewmodels to javafx.fxml;
}