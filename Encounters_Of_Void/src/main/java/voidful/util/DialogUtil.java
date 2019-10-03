package voidful.util;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextInputDialog;
import voidful.entity.session.SessionEntity;
import voidful.exceptions.CreationError;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.YES) {
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
	if (result.isPresent()) {
	    SessionEntity se = new SessionEntity();
	    se.setName(result.get());
	    return se;
	}
	return null;
	// throw new CreationError(ExceptionUtil.SESSION_FAILED_CREATION);
    }

    public static File openSessionDialog() {
	Map<String, File> sessions = new HashMap<>();
	FileUtil.getAllSessions().stream().forEach(file -> {
	    sessions.put(file.getName().split("---")[0], file);
	});
	List<String> choices = new ArrayList<>();
	choices.addAll(sessions.keySet());

	ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
	dialog.setTitle("Open session");
	dialog.setHeaderText("Which session do you want to continue?");
	dialog.setContentText("Session name:");

	Optional<String> s = dialog.showAndWait();
	if (s.isPresent())
	    return sessions.get(s.get());
	else
	    return null;
    }

    public static void showInfo() {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Info");

	alert.setHeaderText(
		"Hey, user! \nThis is the developer of this software. If there are any questions you can ask me them personally via github below.\n I hope that this thing is working and helps you out some way or the other. Send me feedback also via github.");
	alert.getDialogPane().setContent(new Hyperlink("https://github.com/VoidfulDawn/Encounters-of-Void"));
	alert.showAndWait();

    }
}
