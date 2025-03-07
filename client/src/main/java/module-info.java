module org.example.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires android.json;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires com.google.gson;
    requires static lombok;

    opens battle.client to javafx.fxml;
    exports battle.client;
}