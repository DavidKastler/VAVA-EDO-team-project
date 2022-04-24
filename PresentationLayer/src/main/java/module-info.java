module vava.edo.presentationlayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    //needed for HTTP
    requires unirest.java;

    //needed for JSON
    requires gson;
    requires json;

    // needed for the DateUtils to compare dates
    requires commons.lang3;

    opens vava.edo to javafx.fxml;
    exports vava.edo;
    exports vava.edo.controllers;
    opens vava.edo.controllers to javafx.fxml;
    exports vava.edo.models;
    opens vava.edo.models to gson, javafx.fxml;
    exports vava.edo.controllers.models;
    opens vava.edo.controllers.models to javafx.fxml;
}