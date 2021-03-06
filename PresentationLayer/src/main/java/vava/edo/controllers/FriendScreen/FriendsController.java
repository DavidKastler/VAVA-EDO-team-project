package vava.edo.controllers.FriendScreen;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vava.edo.Exepctions.HttpStatusExceptions.UnexpectedHttpStatusException;
import vava.edo.Handlers.RelationshipHandler;
import vava.edo.Handlers.ReportHandler;
import vava.edo.Handlers.SearchHandler;
import vava.edo.controllers.MenuScreen.MenuScreenController;
import vava.edo.models.Relationship;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FriendsController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField search_field_new_friend;

    @FXML
    private TextField search_friends;

    @FXML
    private VBox users_vbox;

    private FriendsScreenModel model;

    public void setModel(FriendsScreenModel model) {
        this.model = model;
    }

    private MenuScreenController menuScreenController;

    public void setMenuScreenController(MenuScreenController menuScreenController) {
        this.menuScreenController = menuScreenController;
    }


    @FXML
    private AnchorPane reportPopUp;

    @FXML
    private HBox friends_screen;

    private List<Relationship> friends = null;

    private Relationship reportingThisUser;

    public FriendsController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void loadFriends() {
        this.model.getUser().setFriends(RelationshipHandler.getAllFriends(this.model.getUser().getUid()));
        reloadFriends();
    }

    public void reloadFriends() {

        users_vbox.getChildren().clear();

        @SuppressWarnings("unchecked")
        List<Relationship> searchedFriends = (List<Relationship>)(List)SearchHandler.searchInList(this.model.getUser().getFriends(), "userName", search_friends.getText());

        for (Integer i = 0; i < this.model.getUser().getFriends().size(); i++){
            try {
                FriendElementModel element = new FriendElementModel(searchedFriends.get(i), this, this.model.getUser());
                HBox hbox = element.getFriendElement();
                users_vbox.getChildren().add(hbox);
            } catch (Exception e) {

            }

        }
    }

    public void handleSearchNewFriend(KeyEvent keyEvent) throws IOException {
    }

    public void addFriend() {
        try {
            RelationshipHandler.createFriendRequest(this.model.getUser().getUid(), search_field_new_friend.getText());
            search_field_new_friend.clear();
        } catch (UnexpectedHttpStatusException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void handleSearchFriend(KeyEvent keyEvent) throws IOException {
        reloadFriends();
    }

    public void switchRequestsScreen(MouseEvent mouseEvent) {
        this.menuScreenController.gethBoxChangingScreen().getChildren().clear();
        this.menuScreenController.gethBoxChangingScreen().getChildren().add(new FriendRequestScreenModel(this.model.getUser(), this.menuScreenController).getFriendRequestScreen());
    }


    @FXML
    private TextArea text_area;

    public void handleReportUserButton(Relationship relationship) {
        reportPopUp.setVisible(true);
        reportPopUp.setDisable(false);
        friends_screen.setDisable(true);
        friends_screen.setVisible(false);

        reportingThisUser = relationship;
    }

    public void handleSendReportButton(MouseEvent mouseEvent) {
        ReportHandler.createReport(this.model.getUser().getUid(), reportingThisUser.getUserId(), text_area.getText());
        reportPopUp.setVisible(false);
        reportPopUp.setDisable(true);
        friends_screen.setDisable(false);
        friends_screen.setVisible(true);
    }

    public void handleCancelReport(MouseEvent mouseEvent) {
        reportPopUp.setVisible(false);
        reportPopUp.setDisable(true);
        friends_screen.setDisable(false);
        friends_screen.setVisible(true);
    }
}