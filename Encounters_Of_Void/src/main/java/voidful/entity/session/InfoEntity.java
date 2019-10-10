package voidful.entity.session;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class InfoEntity implements IEntity {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String id;
    @XmlElement
    private String text;
    @XmlAttribute
    private String createdDate;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setId(String id) {
	this.id = id;
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public String getName() {
	return name;
    }

}
