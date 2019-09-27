package voidful.model;

import java.util.Observable;
import java.util.Observer;


public class SessionKeeper implements Observer {

	private boolean isEverythingSaved=true;
	public SessionKeeper() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.print("I was notified");
	if(isEverythingSaved)
		isEverythingSaved=false;
		
	}

	public boolean isEverythingSaved() {
		return isEverythingSaved;
	}
	
	public void save() {
		isEverythingSaved=true;
	}
}
