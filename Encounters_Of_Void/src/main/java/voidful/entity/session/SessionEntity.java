package voidful.entity.session;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessionEntity implements IEntity {
	@XmlAttribute
	private String name;
	@XmlElement
	private String lastUpdateDate;
	@XmlElement
	private String createdDate;
	@XmlAnyElement
	private List<IEntity> children;
	
	public SessionEntity(String name) {
		this.name = name;
		this.createdDate = String.valueOf(new Date().getTime());
	}
	
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
	public List<IEntity> getChildren() {
		return children;
	}
	public void setChildren(List<IEntity> children) {
		this.children = children;
	}
	

}
