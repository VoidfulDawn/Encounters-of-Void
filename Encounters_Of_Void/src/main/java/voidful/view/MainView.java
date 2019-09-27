package voidful.view;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import voidful.exceptions.InitializationError;
import voidful.util.ExceptionUtil;

public class MainView {
	private enum ComponentKey{
		SAVE_BUTTON, NEW_SESSION, OPEN_SESSION, EXIT,
		
	}
	private Stage ps;
	private Map<ComponentKey,Node> nodes;
	private Map<ComponentKey,EventTarget> interactables;
	public MainView(Stage ps)  {
		this.ps = ps;
		this.init();
		
	}
	private void init() {
	if(ps==null)
		throw new InitializationError(ExceptionUtil.PS_NOT_FOUND_MESSAGE);
		interactables = new HashMap<>();
    	MenuBar menuBar = createMenueBar();
    	ToolBar toolBar = createToolBar();

    	BorderPane root = new BorderPane();
    	VBox headerBars = new VBox();
    	headerBars.getChildren().addAll(menuBar,toolBar);
    	root.setTop(headerBars);
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
    	MenuItem openFileItem = new MenuItem("Open Session");
    	MenuItem exitItem = new MenuItem("Exit");
    	exitItem.setOnAction(e->{
    		System.exit(0);
    		});
    	
    	interactables.put(ComponentKey.NEW_SESSION,newItem);
    	interactables.put(ComponentKey.OPEN_SESSION,openFileItem);
    	interactables.put(ComponentKey.EXIT,exitItem);
    	
    	
    	fileMenu.getItems().addAll(newItem, openFileItem, exitItem);
    	menuBar.getMenus().addAll(fileMenu,helpMenu);
    	
		return menuBar;
	}
	private ToolBar createToolBar() {
		
		ToolBar toolBar = new ToolBar();
		
		Button b = new Button("Save");
		interactables.put(ComponentKey.SAVE_BUTTON, b);
		
		toolBar.getItems().add(b);
		return toolBar;
	}
	

}
