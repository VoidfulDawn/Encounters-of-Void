package voidful.view.component;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import voidful.entity.session.EncounterEntity;
import voidful.entity.session.IEntity;
import voidful.model.SessionKeeper;

public class MainPane extends VBox {
    private SessionKeeper sessionKeeper;
    private Status currentStatus;
    private ScrollPane pane;

    public enum Status {
	NO_SESSION, SESSION_LOADED
    }

    public MainPane(SessionKeeper sessionKeeper) {
	this.sessionKeeper = sessionKeeper;
	this.currentStatus = Status.NO_SESSION;
	this.pane = new ScrollPane(new VBox());
	pane.setHbarPolicy(ScrollBarPolicy.NEVER);
	pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	this.getChildren().add(pane);
	this.setVisible(false);
    }

    public Status getCurrentStatus() {
	return currentStatus;
    }

    public void updateScreen(Status status) {
	currentStatus = status;
	try {
	    switch (currentStatus) {
	    case NO_SESSION:
		break;
	    case SESSION_LOADED:
		this.setVisible(true);
		loadSessionEntities();
		addFurtherOptions();
		break;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void addFurtherOptions() {

    }

    private void loadSessionEntities() {
	List<? extends IEntity> entities;
	if (sessionKeeper.getSession().getChildren() == null)
	    sessionKeeper.getSession().setChildren(new ArrayList<>());
	entities = sessionKeeper.getSession().getChildren();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof EncounterEntity) {
		((VBox) pane.getContent()).getChildren().add(new EncounterComponent((EncounterEntity) entity));
	    }
	}
	HBox addEncounter = new HBox();
	Button add = new Button("++ADD++");
	add.setOnAction(e -> {
	    EncounterEntity ee = new EncounterEntity();
	    // DIALOG WILL ADDED
	    ee.setName("TEST ENCOUNTER THINGIE");
	    sessionKeeper.getSession().getChildren().add(ee);
	    ((VBox) pane.getContent()).getChildren().clear();
	    loadSessionEntities();
	});
	addEncounter.getChildren().add(add);
	((VBox) pane.getContent()).getChildren().add(addEncounter);
	System.out.println(((VBox) pane.getContent()).getChildren().size());
    }

}
