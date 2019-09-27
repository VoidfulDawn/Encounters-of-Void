package voidful.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

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

public class MainView extends Observable {
	private enum ComponentKey{
		SAVE_BUTTON, NEW_SESSION, OPEN_SESSION, EXIT, SAVE_SESSION,
		
	}
	private Stage ps;
	private Map<ComponentKey,EventTarget> interactables;
	private SessionKeeper sessionKeeper;
	private Control control;
	public MainView(Stage ps, SessionKeeper s, Control c)  {
		this.ps = ps;
		this.sessionKeeper=s;
		this.control = c;
		this.addObserver(sessionKeeper);
		this.init();
		
	}
	private void init() {
	if(ps==null)
		throw new InitializationError(ExceptionUtil.PS_NOT_FOUND_MESSAGE);
		interactables = new HashMap<>();
    	MenuBar menuBar = createMenueBar();
    	ToolBar toolBar = createToolBar();

    	
    	VBox headerBars = new VBox();
    	headerBars.getChildren().addAll(menuBar,toolBar);
    	MainPane mainPane = createMainPane();

    	finalInitializationStep(headerBars,mainPane);
    
	}

	private void finalInitializationStep(VBox headerBars, MainPane mainPane) {
		BorderPane root = new BorderPane();
    	root.setTop(headerBars);
    	root.setCenter(mainPane);
    	Scene scene = new Scene(root, 960, 600);
    	ps.setTitle("Encounters of Void");
    	ps.setScene(scene);
    	ps.show();
	}
	
	private MenuBar createMenueBar() {
		
		MenuBar menuBar = new MenuBar();
    	Menu fileMenu = new Menu("File");
    	Menu helpMenu = new Menu("Help");
    	MenuItem newItem = new MenuItem("New Session");
    	MenuItem saveItem = new MenuItem("Save Session");
    	MenuItem openFileItem = new MenuItem("Open Session");
    	MenuItem exitItem = new MenuItem("Exit");
    	exitItem.setOnAction(e->{
    		control.closing();
    		
        	
    		});
    	
    	interactables.put(ComponentKey.NEW_SESSION,newItem);
    	interactables.put(ComponentKey.SAVE_SESSION,saveItem);
    	interactables.put(ComponentKey.OPEN_SESSION,openFileItem);
    	interactables.put(ComponentKey.EXIT,exitItem);
    	
    	
    	fileMenu.getItems().addAll(newItem, openFileItem,saveItem, exitItem);
    	menuBar.getMenus().addAll(fileMenu,helpMenu);
    	
		return menuBar;
	}
	private ToolBar createToolBar() {
		ToolBar toolBar = new ToolBar();
		//toolBar.getItems().add(null);
		return toolBar;
	}
	private MainPane createMainPane() {
		TextField test = new TextField();

		test.textProperty().addListener((observable,oldValue,newValue)->{
			if(oldValue!=newValue)
			{
			setChanged();
			notifyObservers();
			}
		});
		return new MainPane(test);
		
	}
	

}
