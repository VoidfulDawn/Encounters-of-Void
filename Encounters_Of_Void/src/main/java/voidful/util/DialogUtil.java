package voidful.util;


import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import voidful.entity.session.SessionEntity;
import voidful.exceptions.CreationError;

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

	public static SessionEntity createSessionDialog() {
		TextInputDialog dialog = new TextInputDialog("New Session");
		dialog.setTitle("Create new Session");
		dialog.setHeaderText("Let's start a new session");
		dialog.setContentText("Please enter the name of the session:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) 
			return new SessionEntity(result.get());
		
		throw new CreationError(ExceptionUtil.SESSION_FAILED_CREATION);
		}
}
