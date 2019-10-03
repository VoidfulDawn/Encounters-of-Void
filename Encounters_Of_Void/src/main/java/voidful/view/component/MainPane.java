package voidful.view.component;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import voidful.entity.session.EncounterEntity;
import voidful.entity.session.IEntity;
import voidful.model.SessionKeeper;
import voidful.view.MainView;

public class MainPane extends VBox {
    private SessionKeeper sessionKeeper;
    private Status currentStatus;
    private ScrollPane pane;
    private MainView view;

    public enum Status {
	NO_SESSION, SESSION_LOADED
    }

    public MainPane(SessionKeeper sessionKeeper, MainView view) {
	this.sessionKeeper = sessionKeeper;
	this.currentStatus = Status.NO_SESSION;
	this.pane = new ScrollPane(new VBox());
	this.view = view;
	pane.setHbarPolicy(ScrollBarPolicy.NEVER);
	pane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	this.setWidth(640);
	this.setHeight(400);
	this.setMaxWidth(640);
	this.setMaxHeight(400);
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
		getScrollPaneContent().clear();
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
	getScrollPaneContent().clear();
	List<? extends IEntity> entities;
	if (sessionKeeper.getSession().getChildren() == null)
	    sessionKeeper.getSession().setChildren(new ArrayList<>());
	entities = sessionKeeper.getSession().getChildren();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof EncounterEntity) {
		getScrollPaneContent().add(new EncounterComponent((EncounterEntity) entity));
	    }
	}
	HBox addEncounter = new HBox();
	Button add = new Button("++ADD++");
	add.setOnAction(e -> {
	    view.addEncounter();
	    loadSessionEntities();
	});

	addEncounter.getChildren().add(add);
	getScrollPaneContent().add(getScrollPaneContent().size(), addEncounter);
    }

    private ObservableList<Node> getScrollPaneContent() {
	return ((VBox) pane.getContent()).getChildren();
    }

}
