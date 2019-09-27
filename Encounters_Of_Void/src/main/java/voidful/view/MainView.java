package voidful.view;

import javafx.stage.Stage;
import voidful.exceptions.InitializationError;
import voidful.util.ExceptionUtil;

public class MainView {

	private Stage ps;
	
	public MainView(Stage ps)  {
		this.ps = ps;
		this.init();
		
	}
	private void init() {
	
			throw new InitializationError(ExceptionUtil.PS_NOT_FOUND_MESSAGE);
	}

}
