package voidful.entity.session;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class EncounterEntity implements IEntity {

    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlElement
    private String description;
    @XmlElement
    private String averagePlayerLevel;
    @XmlElement
    private List<Item> reward = new ArrayList<>();
    @XmlElement
    private List<NPC> npcs = new ArrayList<>();
    @XmlElement
    private List<Monster> monster = new ArrayList<>();

    public EncounterEntity() {
	id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {

	return id;
    }

    @Override
    public String getName() {

	return name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getAveragePlayerLevel() {
	return averagePlayerLevel;
    }

    public void setAveragePlayerLevel(String averagePlayerLevel) {
	this.averagePlayerLevel = averagePlayerLevel;
    }

    public List<Item> getReward() {
	return reward;
    }

    public void setReward(List<Item> reward) {
	this.reward = reward;
    }

    public List<NPC> getNpcs() {
	return npcs;
    }

    public void setNpcs(List<NPC> npcs) {
	this.npcs = npcs;
    }

    public List<Monster> getMonster() {
	return monster;
    }

    public void setMonster(List<Monster> monster) {
	this.monster = monster;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

}
