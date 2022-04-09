module vava.edo.presentationlayer {
    requires javafx.controls;
    requires javafx.fxml;


    opens vava.edo to javafx.fxml;
    exports vava.edo;
    exports vava.edo.controllers;
    opens vava.edo.controllers to javafx.fxml;
}