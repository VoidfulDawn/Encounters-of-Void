package voidful.view.component;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import voidful.entity.session.EncounterEntity;
import voidful.main.App;
import voidful.view.MainView;

public class EncounterComponent extends StackPane {

    private static final String APL_TEXT = "APL: ";
    private MainView view;
    private final EncounterEntity encounter;

    public EncounterComponent(EncounterEntity e, MainView view) {
	super();
	this.view = view;
	this.encounter = e;
	Label title = new Label(e.getName());
	EncounterComponent.setAlignment(title, Pos.TOP_CENTER);
	Label apl = new Label(APL_TEXT + e.getAveragePlayerLevel());
	EncounterComponent.setAlignment(apl, Pos.TOP_RIGHT);
	Label description = new Label(e.getDescription());
	EncounterComponent.setAlignment(description, Pos.CENTER_LEFT);

	Button edit = new Button("Edit Encounter");
	EncounterComponent.setAlignment(edit, Pos.BOTTOM_RIGHT);
	edit.setOnAction(action -> {
	    editEncounter(action, encounter);
	});

	this.setStyle("-fx-border-color: black");
	this.getChildren().addAll(title, apl, description, edit);
	this.setWidth(621);
	this.setHeight(100);
	this.setMinHeight(100);
	this.setMinWidth(621);
    }

    private void editEncounter(ActionEvent e, EncounterEntity encounter) {
	view.showEncounterView(encounter);
    }

}
