package voidful.view.component;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainPane extends VBox {

	public MainPane(TextField test) {
		getChildren().add(test);
	}
	
}
