package voidful.util;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

import javafx.scene.control.Alert;
public class DialogUtil implements Util {
	
	public static final void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("Embarassing an error occurred.");
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static boolean showShouldISaveDialog() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("There are still some unsaved changes.");
		alert.setContentText("Should I exit without saving?");
		alert.getButtonTypes().remove(0);
		alert.getButtonTypes().addAll(ButtonType.YES,ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES){
		   return false;
		} else {
			return true;
		}
		
	}
}
