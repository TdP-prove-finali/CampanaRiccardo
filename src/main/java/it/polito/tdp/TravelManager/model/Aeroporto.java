package it.polito.tdp.TravelManager.model;

import java.util.Objects;

public class Aeroporto {
	
	private int ID;
	private String IATA;
	private String name;
	private String city;
	private String state;
	private String stateName;
	
	public Aeroporto(int iD, String iATA, String name, String city, String state, String stateName) {
		ID = iD;
		IATA = iATA;
		this.name = name;
		this.city = city;
		this.state = state;
		this.stateName = stateName;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getIATA() {
		return IATA;
	}

	public void setIATA(String iATA) {
		IATA = iATA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeroporto other = (Aeroporto) obj;
		return ID == other.ID;
	}

	@Override
	public String toString() {
		return "Aeroporto " + name + " di " + city;
	}
	
}
