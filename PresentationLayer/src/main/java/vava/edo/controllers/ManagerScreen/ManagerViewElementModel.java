package vava.edo.controllers.ManagerScreen;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import vava.edo.controllers.MenuScreen.MenuScreenController;
import vava.edo.models.Report;
import vava.edo.models.User;

import java.io.IOException;
import java.util.ResourceBundle;


public class ManagerViewElementModel {

    public HBox getElement() {
        return element;
    }

    public void setElement(HBox element) {
        this.element = element;
    }

    private HBox element;

    public User getUser() {
        return user;
    }

    private final User user;

    public Report getReport() {
        return report;
    }

    public MenuScreenController getMenuScreenController() {
        return menuScreenController;
    }

    private final Report report;

    private final MenuScreenController menuScreenController;

    public ManagerViewElementModel(User user, Report report, MenuScreenController menuScreenController){
        this.user = user;
        this.report = report;
        this.menuScreenController = menuScreenController;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vava/edo/ManagerViewElement.fxml"), ResourceBundle.getBundle("Localization Bundle"));
            this.element = loader.load();
            ManagerViewElementController controller = loader.getController();
            controller.setModel(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
