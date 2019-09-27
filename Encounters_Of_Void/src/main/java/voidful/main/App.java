package voidful.main;



import javafx.application.*;
import javafx.stage.Stage;
import voidful.exceptions.InitializationError;
import voidful.model.SessionKeeper;
import voidful.util.DialogUtil;
import voidful.view.MainView;

public class App extends Application 
{
	
    public static void main( String[] args )
    {
        App.launch(args);
    }

	@Override
	public void start(Stage ps) throws Exception {
		try {
		SessionKeeper s = new SessionKeeper();
		Control c = new Control(s);
		MainView v = new MainView(ps,s,c);
		
		}catch(InitializationError e) {
			DialogUtil.showError(e.getMessage());
		}
		
	}


}
