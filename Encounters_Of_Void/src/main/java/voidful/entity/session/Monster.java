package voidful.entity.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Monster implements IEntity {

    @XmlAttribute
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private String challengeRating;
    @XmlAttribute
    private String id;

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

    public String getDescription() {
	// TODO Auto-generated method stub
	return description;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getChallengeRating() {
	return challengeRating;
    }

    public void setChallengeRating(String challengeRating) {
	this.challengeRating = challengeRating;
    }

}
