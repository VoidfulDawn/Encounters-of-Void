package voidful.entity.session;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionEntity implements IEntity {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String id;
    @XmlElement
    private String lastUpdateDate;
    @XmlAttribute
    private String createdDate;
    @XmlElement
    private List<EncounterEntity> children;

    public SessionEntity() {
	this.createdDate = String.valueOf(new Date().getTime());
	this.lastUpdateDate = this.createdDate;
	this.id = UUID.randomUUID().toString();

    }

    @Override
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLastUpdateDate() {
	return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }

    public String getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
    }

    public List<EncounterEntity> getChildren() {
	return children;
    }

    public void setChildren(List<EncounterEntity> children) {
	this.children = children;
    }

    @Override
    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

}
