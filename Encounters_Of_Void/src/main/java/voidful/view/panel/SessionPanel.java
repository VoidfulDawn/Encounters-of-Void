package voidful.view.panel;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import voidful.entity.session.EncounterEntity;
import voidful.entity.session.IEntity;
import voidful.model.SessionKeeper;
import voidful.view.component.EncounterComponent;

public class SessionPanel extends ScrollPane implements Panel {

    private VBox main = new VBox();
    private ObservableList<Node> childrenList;

    public SessionPanel() {

	this.main = new VBox();
	this.setContent(main);
	childrenList = this.main.getChildren();
    }

    void loadSessionEntities(MainPane mainPane) {
	childrenList.clear();
	List<? extends IEntity> entities;
	if (mainPane.getSessionKeeper().getSession().getChildren() == null)
	    mainPane.getSessionKeeper().getSession().setChildren(new ArrayList<>());
	entities = mainPane.getSessionKeeper().getSession().getChildren();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof EncounterEntity) {
		childrenList.add(new EncounterComponent((EncounterEntity) entity, mainPane.getView()));
	    }
	}
	HBox addEncounter = new HBox();
	Button add = new Button("++ADD++");
	add.setOnAction(e -> {
	    mainPane.getView().addEncounter();
	    this.loadSessionEntities(mainPane);
	});

	addEncounter.getChildren().add(add);
	childrenList.add(childrenList.size(), addEncounter);
	mainPane.getSessionKeeper().setEverythingSaved(false);
    }
}
