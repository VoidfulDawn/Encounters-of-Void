package voidful.entity.formular;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FormularComponent {

	@XmlElement(name="type")
	private EntityAttributeType type;
	@XmlElement(name="name")
	private String name;
	
	public EntityAttributeType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public void setType(EntityAttributeType type) {
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
