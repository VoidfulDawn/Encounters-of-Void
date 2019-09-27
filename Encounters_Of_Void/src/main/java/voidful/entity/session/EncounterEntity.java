package voidful.entity.session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class EncounterEntity implements IEntity {

    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAnyElement
    private List<IEntity> children = new ArrayList<>();

    public EncounterEntity() {
	id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
	// TODO Auto-generated method stub
	return id;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return name;
    }

    public List<IEntity> getChildren() {
	return children;
    }

    public void setChildren(List<IEntity> children) {
	this.children = children;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

}
