package voidful.view.component;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import voidful.entity.session.EncounterEntity;
import voidful.entity.session.Monster;
import voidful.view.MainView;

public class MonsterComponent extends StackPane {
    private MainView view;
    private final Monster encounter;

    public MonsterComponent(Monster e, MainView view) {
	super();
	this.view = view;
	this.encounter = e;
	Label name = new Label(e.getName());
	EncounterComponent.setAlignment(name, Pos.TOP_CENTER);

	Label description = new Label(e.getDescription());
	EncounterComponent.setAlignment(description, Pos.CENTER_LEFT);

	Button edit = new Button("Edit Monster");
	EncounterComponent.setAlignment(edit, Pos.BOTTOM_RIGHT);
	edit.setOnAction(action -> {
	    editMonster(action, encounter);
	});

	this.setStyle("-fx-border-color: black");
	this.getChildren().addAll(name, description, edit);

    }

    private void editMonster(ActionEvent e, Monster monster) {
	view.showMonsterView(monster);
    }
}
