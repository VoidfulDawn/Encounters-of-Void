package voidful.util;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
public class DialogUtil implements Util {
	
	public static final void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Embarassing an error occurred.");
		alert.setContentText(message);

		alert.showAndWait();
	}
}
