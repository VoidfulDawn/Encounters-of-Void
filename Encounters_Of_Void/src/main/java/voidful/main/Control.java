package voidful.main;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;

import javafx.application.Application;
import voidful.entity.session.SessionEntity;
import voidful.model.SessionKeeper;
import voidful.util.DialogUtil;
import voidful.util.FileUtil;

public class Control {

    private SessionKeeper sessionKeeper;
    JAXBContext sessionContext;
    private Application app;

    public Control(SessionKeeper s, Application app) {
	this.sessionKeeper = s;
	this.app = app;
	try {
	    sessionContext = JAXBContext.newInstance(SessionEntity.class);
	} catch (JAXBException e) {
	    e.printStackTrace();
	    DialogUtil.showError(e.getMessage());
	}
    }

    public void closing() {
	if (sessionKeeper.isEverythingSaved()) {
	    System.exit(0);
	} else {
	    boolean save = DialogUtil.showShouldISaveDialog();
	    if (save) {
		saveCorrectly();
		System.exit(0);
	    } else
		System.exit(0);
	}

    }

    private void saveCorrectly() {

	sessionKeeper.setEverythingSaved(true);
	saveSession(sessionKeeper.getSession());
    }

    public void createAndLoadNewSession() {
	try {
	    saveSession(sessionKeeper.getSession());
	    SessionEntity se = DialogUtil.createSessionDialog();
	    if (se == null)
		return;
	    saveSession(se);
	    loadSession(se);
	} catch (Exception e) {
	    DialogUtil.showError(e.getMessage());
	}

    }

    public void loadSavedSession() {
	try {
	    File sessionFile = DialogUtil.openSessionDialog();
	    if (sessionFile == null)
		return;
	    Unmarshaller unmarshaller = sessionContext.createUnmarshaller();
	    SessionEntity se = (SessionEntity) unmarshaller.unmarshal(sessionFile);
	    loadSession(se);
	} catch (Exception e) {
	    DialogUtil.showError(e.getMessage());
	}

    }

    public void saveSession(SessionEntity se) {
	if (se == null)
	    return;
	if (!FileUtil.DIRECTORY.exists()) {
	    FileUtil.SESSION.mkdirs();
	}
	if (!FileUtil.SESSION.exists()) {
	    FileUtil.SESSION.mkdir();
	}
	try {

	    Marshaller marshaller = sessionContext.createMarshaller();
	    DocumentResult dr = new DocumentResult();
	    marshaller.marshal(se, dr);
	    Document document = dr.getDocument();
	    FileUtil.saveFile(FileUtil.SESSION, se.getName() + "---" + se.getId(), document);

	} catch (JAXBException e) {
	    e.printStackTrace();
	}

    }

    private void loadSession(SessionEntity se) {
	sessionKeeper.setSession(se);
	sessionKeeper.setEverythingSaved(true);

    }

    public void hyperlinkClick(String url) {
	app.getHostServices().showDocument(url);

    }

}
