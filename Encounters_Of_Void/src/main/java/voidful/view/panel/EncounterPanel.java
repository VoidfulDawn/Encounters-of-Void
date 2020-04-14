package voidful.view.panel;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import voidful.entity.session.EncounterEntity;
import voidful.entity.session.IEntity;
import voidful.entity.session.Monster;
import voidful.entity.session.NPC;
import voidful.view.component.EncounterComponent;
import voidful.view.component.MonsterComponent;

public class EncounterPanel extends StackPane {
    private ObservableList<Node> monsterList;
    private ObservableList<Node> npcList;
    private ObservableList<Node> itemList;

    public EncounterPanel() {
	VBox monster = new VBox();
	VBox npc = new VBox();
	VBox item = new VBox();

	monsterList = monster.getChildren();
	npcList = npc.getChildren();
	itemList = item.getChildren();

	ScrollPane scrollMonster = new ScrollPane();
	initScrollPanes(monster, scrollMonster);
	ScrollPane scrollNPC = new ScrollPane();
	initScrollPanes(npc, scrollNPC);
	ScrollPane scrollItem = new ScrollPane();
	initScrollPanes(item, scrollItem);
	HBox scrollHolder = new HBox();

	scrollHolder.getChildren().addAll(scrollMonster, scrollNPC, scrollItem);
	StackPane.setAlignment(scrollHolder, Pos.CENTER_LEFT);
	this.getChildren().add(scrollHolder);

    }

    private void initScrollPanes(VBox content, ScrollPane scrollPane) {
	scrollPane.setContent(content);
	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollPane.setFitToHeight(true);
	scrollPane.setFitToWidth(true);
	scrollPane.setMinWidth(300);
	scrollPane.setMinHeight(250);
    }

    void loadEncounter(MainPane mainPane) {
	fillMonsters(mainPane);
	fillNpc(mainPane);
	fillItems(mainPane);
	monsterList.clear();
	List<? extends IEntity> entities;
	if (mainPane.getSessionKeeper().getEncounter().getMonster() == null)
	    mainPane.getSessionKeeper().getEncounter().setMonster(new ArrayList<>());
	entities = mainPane.getSessionKeeper().getEncounter().getMonster();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof Monster) {
		monsterList.add(new MonsterComponent((Monster) entity, mainPane.getView()));
	    }
	}
	HBox addEncounter = new HBox();
	Button addMonster = new Button("++ADD++");
	addMonster.setOnAction(e -> {
	    mainPane.getView().addMonster();
	    this.loadEncounter(mainPane);
	});

	addEncounter.getChildren().add(addMonster);
	monsterList.add(monsterList.size(), addEncounter);
	mainPane.getSessionKeeper().setEverythingSaved(false);
    }

    private void fillItems(MainPane mainPane) {
	itemList.clear();
	List<? extends IEntity> entities;
	if (mainPane.getSessionKeeper().getEncounter().getReward() == null)
	    mainPane.getSessionKeeper().getEncounter().setReward(new ArrayList<>());
	entities = mainPane.getSessionKeeper().getEncounter().getMonster();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof Monster) {
		itemList.add(new MonsterComponent((Monster) entity, mainPane.getView()));
	    }
	}
	HBox addEncounter = new HBox();
	Button addMonster = new Button("++ADD++");
	addMonster.setOnAction(e -> {
	    mainPane.getView().addMonster();
	    this.loadEncounter(mainPane);
	});

	addEncounter.getChildren().add(addMonster);
	itemList.add(itemList.size(), addEncounter);
    }

    private void fillNpc(MainPane mainPane) {
	npcList.clear();
	List<? extends IEntity> entities;
	if (mainPane.getSessionKeeper().getEncounter().getNpcs() == null)
	    mainPane.getSessionKeeper().getEncounter().setNpcs(new ArrayList<>());
	entities = mainPane.getSessionKeeper().getEncounter().getMonster();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof Monster) {
		npcList.add(new MonsterComponent((Monster) entity, mainPane.getView()));
	    }
	}
	HBox addEncounter = new HBox();
	Button addMonster = new Button("++ADD++");
	addMonster.setOnAction(e -> {
	    mainPane.getView().addMonster();
	    this.loadEncounter(mainPane);
	});

	addEncounter.getChildren().add(addMonster);
	npcList.add(npcList.size(), addEncounter);

    }

    private void fillMonsters(MainPane mainPane) {
	monsterList.clear();
	List<? extends IEntity> entities;
	if (mainPane.getSessionKeeper().getEncounter().getMonster() == null)
	    mainPane.getSessionKeeper().getEncounter().setMonster(new ArrayList<>());
	entities = mainPane.getSessionKeeper().getEncounter().getMonster();
	entities.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
	for (IEntity entity : entities) {
	    if (entity instanceof Monster) {
		monsterList.add(new MonsterComponent((Monster) entity, mainPane.getView()));
	    }
	}
	HBox addEncounter = new HBox();
	Button addMonster = new Button("++ADD++");
	addMonster.setOnAction(e -> {
	    mainPane.getView().addMonster();
	    this.loadEncounter(mainPane);
	});

	addEncounter.getChildren().add(addMonster);
	monsterList.add(monsterList.size(), addEncounter);
    }

    public ObservableList<Node> getMonsterList() {
	return monsterList;
    }

    public void setMonsterList(ObservableList<Node> monsterList) {
	this.monsterList = monsterList;
    }

}
