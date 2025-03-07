module org.example.battlearena {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.battlearena to javafx.fxml;
    exports org.example.battlearena;
}