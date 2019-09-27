package voidful.entity.formular;

import java.util.List;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public interface EntityFormular {
	
	public List<FormularComponent> getFormular();

}
