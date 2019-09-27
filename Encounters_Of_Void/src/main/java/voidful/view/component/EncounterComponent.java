package voidful.view.component;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import voidful.entity.session.EncounterEntity;

public class EncounterComponent extends BorderPane {

    public EncounterComponent(EncounterEntity e) {
	super();
	Label l = new Label(e.getName());
	this.setTop(l);
	this.setWidth(300);
	this.setHeight(100);
    }

}
