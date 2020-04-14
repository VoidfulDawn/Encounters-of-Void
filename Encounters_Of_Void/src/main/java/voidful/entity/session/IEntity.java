package voidful.entity.session;

import java.io.Serializable;

public interface IEntity extends Serializable, Named {

    public enum Attributes {
	NAME, APL, DESCRIPTION, CR
    }

    public String getId();

}
