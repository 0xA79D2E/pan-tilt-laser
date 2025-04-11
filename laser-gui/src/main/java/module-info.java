module com.shygaan.pantilt.lasergui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shygaan.pantilt.lasergui to javafx.fxml;
    exports com.shygaan.pantilt.lasergui;
}