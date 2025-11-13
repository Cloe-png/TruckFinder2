module org.example.truckfinder2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.truckfinder2 to javafx.fxml;
    exports org.example.truckfinder2;
}