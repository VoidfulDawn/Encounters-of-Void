package voidful.main;

import voidful.entity.session.SessionEntity;
import voidful.model.SessionKeeper;
import voidful.util.DialogUtil;

public class Control {

	private SessionKeeper sessionKeeper;

	public Control(SessionKeeper s) {
		this.sessionKeeper = s;
	}

	public void closing() {
		if(sessionKeeper.isEverythingSaved()) {
			System.exit(0);
		}else {
			boolean save = DialogUtil.showShouldISaveDialog();
			if(save) {
				saveCorrectly();
				System.exit(0);
			}else
				System.exit(0);
		}
		
	}
	private void saveCorrectly() {
		sessionKeeper.save();
	}

	public void createAndLoadNewSession() {
		try {
		SessionEntity se = DialogUtil.createSessionDialog();
		loadSession(se);
		}catch(Exception e)
		{
			DialogUtil.showError(e.getMessage());
		}
		
	}

	private void loadSession(SessionEntity se) {
		
		
	}

}
