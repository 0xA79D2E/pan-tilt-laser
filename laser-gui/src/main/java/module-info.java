module com.shygaan.pantilt.lasergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fazecast.jSerialComm;


    opens com.shygaan.pantilt.lasergui to javafx.fxml;
    exports com.shygaan.pantilt.lasergui;
}