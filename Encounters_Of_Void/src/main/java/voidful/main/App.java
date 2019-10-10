package voidful.main;

import javafx.application.*;
import javafx.stage.Stage;
import voidful.exceptions.InitializationError;
import voidful.model.SessionKeeper;
import voidful.util.DialogUtil;
import voidful.util.FileUtil;
import voidful.util.LoggerUtil;
import voidful.view.MainView;

public class App extends Application {

    public static MainView v;

    public static void main(String[] args) {
	App.launch(args);
    }

    @Override
    public void start(Stage ps) throws Exception {
	try {
	    System.setProperty("log.name", FileUtil.LOG.getAbsolutePath().concat("/mylog.log"));

	    SessionKeeper s = new SessionKeeper();
	    Control c = new Control(s, this);
	    v = new MainView(ps, s, c);

	} catch (InitializationError e) {
	    DialogUtil.showError(e.getMessage());
	}

    }

}
