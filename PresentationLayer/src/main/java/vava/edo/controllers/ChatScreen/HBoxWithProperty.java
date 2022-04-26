package vava.edo.controllers.ChatScreen;

import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;
import vava.edo.models.Relationship;

@Getter
@Setter
public class HBoxWithProperty extends HBox {
    private Relationship property;
}
