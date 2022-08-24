package it.polito.tdp.TravelManager.model;

import java.util.List;
import java.util.Objects;

public class Itinerario {
	
	private List<Adiacenza> scali;
	private Aeroporto Departure;
	private Aeroporto Arrival;
	private List<Double> prezzi;
	private double prezzo = 0;
	private int stops;
	
	public Itinerario(List<Adiacenza> scali, List<Double> prezzi) {
		this.scali = scali;
		this.prezzi = prezzi;
		stops = scali.size() - 1;
		this.Departure = scali.get(0).getOrigine();
		this.Arrival = scali.get(scali.size()-1).getDest();
		
		for(Double p : prezzi) {
			prezzo += p;
		}
	}

	public List<Adiacenza> getScali() {
		return scali;
	}



	public void setScali(List<Adiacenza> scali) {
		this.scali = scali;
	}



	public Aeroporto getDeparture() {
		return Departure;
	}



	public void setDeparture(Aeroporto departure) {
		Departure = departure;
	}



	public Aeroporto getArrival() {
		return Arrival;
	}



	public void setArrival(Aeroporto arrival) {
		Arrival = arrival;
	}



	public List<Double> getPrezzi() {
		return prezzi;
	}



	public void setPrezzi(List<Double> prezzi) {
		this.prezzi = prezzi;
	}



	public double getPrezzo() {
		return prezzo;
	}



	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}



	public int getStops() {
		return stops;
	}



	public void setStops(int stops) {
		this.stops = stops;
	}

	


	@Override
	public int hashCode() {
		return Objects.hash(prezzi, scali);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Objects.equals(prezzi, other.prezzi) && Objects.equals(scali, other.scali);
	}

	@Override
	public String toString() {
		return "Viaggio da: " + scali.get(0).getOrigine() + " a " + scali.get(scali.size()-1).getDest() +  " ,composto da: " + stops + " scali";
	}

}
