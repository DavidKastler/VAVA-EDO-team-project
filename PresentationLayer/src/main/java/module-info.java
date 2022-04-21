module vava.edo.presentationlayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    //needed for HTTP
    requires unirest.java;

    //needed for JSON
    requires gson;
    requires json;
    requires org.jetbrains.annotations;

    //needed for JSON

    opens vava.edo to javafx.fxml;
    exports vava.edo;
    exports vava.edo.controllers;
    opens vava.edo.controllers to javafx.fxml;
    exports vava.edo.models;
    opens vava.edo.models to gson, javafx.fxml;
}