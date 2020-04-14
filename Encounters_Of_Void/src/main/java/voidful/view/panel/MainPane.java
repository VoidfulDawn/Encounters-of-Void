package voidful.view.panel;

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
    private SessionPanel sessionPane;
    private MainView view;
    private EncounterPanel encounterPane;

    public enum Status {
	NO_SESSION, SESSION_LOADED, ENCOUNTER_LOADED
    }

    public MainPane(SessionKeeper sessionKeeper, MainView view) {
	this.sessionKeeper = sessionKeeper;
	this.currentStatus = Status.NO_SESSION;
	this.sessionPane = new SessionPanel();
	this.encounterPane = new EncounterPanel();
	this.view = view;
	sessionPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	sessionPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	this.setWidth(1024);
	this.setHeight(768);
	this.setMaxWidth(1024);
	this.setMaxHeight(768);
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
		this.getChildren().clear();
		this.getChildren().add(sessionPane);
		this.setVisible(true);
		sessionPane.loadSessionEntities(this);
		addFurtherOptions();
		break;
	    case ENCOUNTER_LOADED:
		this.getChildren().remove(sessionPane);
		this.getChildren().add(encounterPane);
		encounterPane.loadEncounter(this);
		break;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void addFurtherOptions() {

    }

    private ObservableList<Node> getScrollPaneContent() {
	return ((VBox) sessionPane.getContent()).getChildren();
    }

    public SessionKeeper getSessionKeeper() {
	return sessionKeeper;
    }

    public void setSessionKeeper(SessionKeeper sessionKeeper) {
	this.sessionKeeper = sessionKeeper;
    }

    public ScrollPane getPane() {
	return sessionPane;
    }

    MainView getView() {
	return view;
    }

    public void setView(MainView view) {
	this.view = view;
    }

    public void setCurrentStatus(Status currentStatus) {
	this.currentStatus = currentStatus;
    }

    public void update() {

    }

}
