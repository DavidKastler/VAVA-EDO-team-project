package vava.edo.models;

import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HBoxWithProperty extends HBox {
    private Relationship property;
}
