package voidful.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voidful.exceptions.InitializationError;
import voidful.main.Control;
import voidful.model.SessionKeeper;
import voidful.util.DialogUtil;
import voidful.util.ExceptionUtil;
import voidful.view.component.MainPane;

public class MainView implements Observer {
    private enum ComponentKey {
	SAVE_BUTTON, NEW_SESSION, OPEN_SESSION, EXIT, SAVE_SESSION,

    }

    private Stage ps;
    private Map<ComponentKey, EventTarget> interactables;
    private SessionKeeper sessionKeeper;
    private Control control;
    private MainPane mainPane;

    public MainView(Stage ps, SessionKeeper s, Control c) {
	this.ps = ps;
	this.sessionKeeper = s;
	this.sessionKeeper.addObserver(this);
	this.control = c;
	this.init();

    }

    private void init() {
	if (ps == null)
	    throw new InitializationError(ExceptionUtil.PS_NOT_FOUND_MESSAGE);
	interactables = new HashMap<>();
	MenuBar menuBar = createMenueBar();
	ToolBar toolBar = createToolBar();

	VBox headerBars = new VBox();
	headerBars.getChildren().addAll(menuBar, toolBar);
	mainPane = createMainPane();

	finalInitializationStep(headerBars, mainPane);

    }

    private void finalInitializationStep(VBox headerBars, MainPane mainPane) {
	BorderPane root = new BorderPane();
	root.setTop(headerBars);
	root.setCenter(mainPane);
	System.out.println(root.getChildren().size());
	Scene scene = new Scene(root, 960, 600);
	ps.setTitle("Encounters of Void");
	ps.setScene(scene);
	ps.show();
    }

    private MenuBar createMenueBar() {

	MenuBar menuBar = new MenuBar();
	Menu fileMenu = new Menu("File");
	Menu helpMenu = new Menu("Help");
	onAction(helpMenu);
	helpMenu.setOnAction(e -> {
	    System.out.println("help was clicked");
	    DialogUtil.showInfo();
	});
	MenuItem newItem = new MenuItem("New Session");
	MenuItem saveItem = new MenuItem("Save Session");
	MenuItem openFileItem = new MenuItem("Open Session");
	MenuItem exitItem = new MenuItem("Exit");
	exitItem.setOnAction(e -> {
	    control.closing();

	});
	saveItem.setOnAction(e -> {
	    control.saveSession(sessionKeeper.getSession());
	});
	newItem.setOnAction(e -> {
	    control.createAndLoadNewSession();
	});
	openFileItem.setOnAction(e -> {
	    control.loadSavedSession();
	});
	interactables.put(ComponentKey.NEW_SESSION, newItem);
	interactables.put(ComponentKey.SAVE_SESSION, saveItem);
	interactables.put(ComponentKey.OPEN_SESSION, openFileItem);
	interactables.put(ComponentKey.EXIT, exitItem);

	fileMenu.getItems().addAll(newItem, openFileItem, saveItem, exitItem);
	menuBar.getMenus().addAll(fileMenu, helpMenu);

	return menuBar;
    }

    private ToolBar createToolBar() {
	ToolBar toolBar = new ToolBar();
	// toolBar.getItems().add(null);
	return toolBar;
    }

    private MainPane createMainPane() {
	TextField test = new TextField();
//		test.textProperty().addListener((observable,oldValue,newValue)->{
//			if(oldValue!=newValue)
//			{
//				sessionKeeper.setEverythingSaved(false);
//			}
//		});

	return new MainPane(sessionKeeper);

    }

    @Override
    public void update(Observable o, Object arg) {
	System.out.printf("Observable has an update");
	if (o.equals(sessionKeeper)) {
	    if (sessionKeeper.getSession() != null)
		ps.setTitle("Encounters of Void - " + sessionKeeper.getSession().getName());
	    else
		ps.setTitle("Encounters of Void ");
	    mainPane.updateScreen(MainPane.Status.SESSION_LOADED);
	}

    }

    private static void onAction(Menu menu) {
	final MenuItem menuItem = new MenuItem();

	menu.getItems().add(menuItem);
	menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
	menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
    }
}
