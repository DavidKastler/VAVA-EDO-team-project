module edo.vavaedo {
    requires javafx.controls;
    requires javafx.fxml;


    opens edo.vavaedo to javafx.fxml;
    exports edo.vavaedo;
}