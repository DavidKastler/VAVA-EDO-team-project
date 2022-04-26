package vava.edo.Handlers;

import javafx.scene.layout.VBox;
import vava.edo.controllers.models.FriendElementAdminModel;
import vava.edo.controllers.models.TodoHBoxModel;
import vava.edo.models.Relationship;
import vava.edo.models.Todo;
import vava.edo.models.User;

public class RefreshUserScreen {
    private User user;
    private VBox vBoxTodos;
    private VBox vBoxFriends;
    private Todo selectedTodo;

    public RefreshUserScreen(User user, VBox vBoxTodos, VBox vBoxFriends) {
        this.user = user;
        this.vBoxTodos = vBoxTodos;
        this.vBoxFriends = vBoxFriends;
    }

    public void initLoader() {
        refresh();
    }

    public void refresh() {
        vBoxTodos.getChildren().clear();
        vBoxFriends.getChildren().clear();

        for(Todo todo : user.getTodos()) {
            vBoxTodos.getChildren().add(new TodoHBoxModel(todo, this).getTodoHBOx());
        }

        for(Relationship relationship : RelationshipHandler.getAllFriends(user.getUid())) {
            vBoxFriends.getChildren().add(new FriendElementAdminModel(relationship.getUserName()).getFriendHBox());
        }
    }
}
