package voidful.util;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import voidful.entity.session.IEntity.Attributes;
import voidful.entity.session.SessionEntity;
import voidful.exceptions.CreationError;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.geometry.Insets;
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

    public static void showInfo(Hyperlink h) {
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Info");
	alert.setHeaderText(
		"Hey, user! \nThis is the developer of this software. If there are any questions you can ask me them personally via github below.\n I hope that this thing is working and helps you out some way or the other. Send me feedback also via github.");
	alert.getDialogPane().setContent(h);
	alert.showAndWait();

    }

    public static Map<Attributes, String> createEncounterSession() {

	Dialog<Map<Attributes, String>> dialog = new Dialog<>();
	dialog.setTitle("Create a new encounter");
	dialog.setHeaderText("Create here the basics for your encounter. Don't worry you can edit it later");
	ButtonType createButton = new ButtonType("Create Encounter", ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);
	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(20, 150, 10, 10));

	TextField name = new TextField();
	name.setPromptText("Encounter-Name");
	grid.add(new Label("Name:"), 0, 0);
	grid.add(name, 1, 0, 3, 1);
	TextField desc = new TextField();
	desc.setPromptText("It is a fine day to fight");
	grid.add(new Label("Description:"), 0, 1);
	grid.add(desc, 1, 1, 3, 1);
	TextField apl = new TextField();
	apl.setPromptText("Average Player Level");
	grid.add(new Label("APL:"), 0, 2);
	grid.add(apl, 1, 2, 3, 1);

	dialog.getDialogPane().setContent(grid);
	Platform.runLater(() -> name.requestFocus());

	dialog.setResultConverter(dialogButton -> {
	    if (dialogButton == createButton) {
		Map<Attributes, String> attributeMap = new HashMap<>();
		attributeMap.putIfAbsent(Attributes.NAME, name.getText());
		attributeMap.putIfAbsent(Attributes.DESCRIPTION, desc.getText());
		attributeMap.putIfAbsent(Attributes.APL, apl.getText());
		return attributeMap;
	    }
	    return null;
	});
	Map<Attributes, String> m = dialog.showAndWait().get();
	return m;

    }

    public static Map<Attributes, String> createMonster() {

	Dialog<Map<Attributes, String>> dialog = new Dialog<>();
	dialog.setTitle("Create a new Monster");
	dialog.setHeaderText("Create here the basics for your monster. Don't worry you can edit it later");
	ButtonType createButton = new ButtonType("Create Monster", ButtonData.OK_DONE);
	dialog.getDialogPane().getButtonTypes().addAll(createButton, ButtonType.CANCEL);
	GridPane grid = new GridPane();
	grid.setHgap(10);
	grid.setVgap(10);
	grid.setPadding(new Insets(20, 150, 10, 10));

	TextField name = new TextField();
	name.setPromptText("Monster Name");
	grid.add(new Label("Name:"), 0, 0);
	grid.add(name, 1, 0, 3, 1);
	TextField desc = new TextField();
	desc.setPromptText("Look it is a very spooky monster");
	grid.add(new Label("Description:"), 0, 1);
	grid.add(desc, 1, 1, 3, 1);
	TextField CR = new TextField();
	CR.setPromptText("XX");
	grid.add(new Label("CR:"), 0, 2);
	grid.add(CR, 1, 2, 3, 1);

	dialog.getDialogPane().setContent(grid);
	Platform.runLater(() -> name.requestFocus());

	dialog.setResultConverter(dialogButton -> {
	    if (dialogButton == createButton) {
		Map<Attributes, String> attributeMap = new HashMap<>();
		attributeMap.putIfAbsent(Attributes.NAME, name.getText());
		attributeMap.putIfAbsent(Attributes.DESCRIPTION, desc.getText());
		attributeMap.putIfAbsent(Attributes.CR, CR.getText());
		return attributeMap;
	    }
	    return null;
	});
	Map<Attributes, String> m = dialog.showAndWait().get();
	return m;

    }
}
