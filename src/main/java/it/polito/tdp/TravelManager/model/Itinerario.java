package it.polito.tdp.TravelManager.model;

import java.util.List;
import java.util.Objects;

public class Itinerario implements Comparable<Itinerario>{

	private Aeroporto Departure;
	private Aeroporto Arrival;
	private String fare = "";
	private List<Adiacenza> journey;
	private List<Double> pricesList;
	private String stops = "";
	private String itinerary = "";
	private String prices = "";
	
	public Itinerario(Aeroporto departure, Aeroporto arrival, List<Adiacenza> journey, List<Double> priceslist) {
		Departure = departure;
		Arrival = arrival;
		this.journey = journey;
		this.pricesList = priceslist;
		
		if(this.journey.size() == 1) {
			stops += "Non-Stop";
		}
		
		if(this.journey.size() == 2) {
			stops += "1 Stop";
		}
		
		if(this.journey.size() == 3) {
			stops += "2 Stops";
		}
		
		int temp = 0;
		for(Double d : pricesList) {
			temp += d;
			prices += "$" + (int)d.doubleValue() + ", ";
		}
		
		fare = "$" + temp;
		
		prices = prices.substring(0, prices.length()-2);
		
		itinerary += Departure.getIATA() + "[" +Departure.getCity() + "]  ->  ";
		
		for(Adiacenza a : journey) {
			itinerary += a.getDest().getIATA() + "[" + a.getDest().getCity() + "]  ->  ";
		}
		
		itinerary = itinerary.substring(0, itinerary.length()-5);
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

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public List<Adiacenza> getJourney() {
		return journey;
	}

	public void setJourney(List<Adiacenza> journey) {
		this.journey = journey;
	}

	public List<Double> getPricesList() {
		return pricesList;
	}

	public void setPricesList(List<Double> pricesList) {
		this.pricesList = pricesList;
	}

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public String getPrices() {
		return prices;
	}

	public void setPrices(String prices) {
		this.prices = prices;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Arrival, Departure, journey, prices);
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
		return Objects.equals(Arrival, other.Arrival) && Objects.equals(Departure, other.Departure)
				&& Objects.equals(journey, other.journey) && Objects.equals(prices, other.prices);
	}

	@Override
	public int compareTo(Itinerario o) {
		// TODO Auto-generated method stub
		return this.fare.substring(1).compareTo(o.getFare().substring(1));
	}

	@Override
	public String toString() {
		return "itinerary: " + itinerary + ", with prices:" + prices;
	}
}
