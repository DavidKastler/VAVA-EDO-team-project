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
    requires lombok;

    // needed for the DateUtils to compare dates
    requires commons.lang3;

    opens vava.edo to javafx.fxml;
    exports vava.edo;
    exports vava.edo.models;
    opens vava.edo.models to gson, javafx.fxml;
    exports vava.edo.controllers.TodoScreen;
    opens vava.edo.controllers.TodoScreen to javafx.fxml;
    exports vava.edo.controllers.MenuScreen;
    opens vava.edo.controllers.MenuScreen to javafx.fxml;
    exports vava.edo.controllers.CalendarScreen;
    opens vava.edo.controllers.CalendarScreen to javafx.fxml;
    exports vava.edo.controllers.ChatScreen;
    exports vava.edo.controllers.FriendScreen;
    exports vava.edo.controllers.AdminScreen;
    exports vava.edo.controllers.RegisterScreen;
    opens vava.edo.controllers.RegisterScreen to javafx.fxml;
    exports vava.edo.controllers.LoginScreen;
    opens vava.edo.controllers.LoginScreen to javafx.fxml;
    exports vava.edo.controllers.ManagerScreen;
    exports vava.edo.controllers.ReportScreen;
    opens vava.edo.controllers.ReportScreen to javafx.fxml;
    opens vava.edo.controllers.AdminScreen to gson, javafx.fxml;
    opens vava.edo.controllers.ChatScreen to gson, javafx.fxml;
    opens vava.edo.controllers.FriendScreen to gson, javafx.fxml;
    opens vava.edo.controllers.ManagerScreen to gson, javafx.fxml;
}