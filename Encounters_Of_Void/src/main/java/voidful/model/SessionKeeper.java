package voidful.model;

import java.util.Observable;
import java.util.Observer;

import voidful.entity.session.EncounterEntity;
import voidful.entity.session.SessionEntity;

public class SessionKeeper extends Observable {

    private boolean isEverythingSaved = true;
    private SessionEntity se;
    private EncounterEntity encounter;

    public SessionKeeper() {

    }

    public boolean isEverythingSaved() {
	return isEverythingSaved;
    }

    public void setEverythingSaved(boolean isEverythingSaved) {
	this.isEverythingSaved = isEverythingSaved;
    }

    public void setSession(SessionEntity se) {
	this.se = se;
	this.setChanged();
	this.notifyObservers();
    }

    public SessionEntity getSession() {
	return se;
    }

    public void setEncounter(EncounterEntity encounter) {
	this.encounter = encounter;

    }

    public EncounterEntity getEncounter() {
	return encounter;
    }

}
