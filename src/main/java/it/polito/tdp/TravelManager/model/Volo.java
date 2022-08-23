package it.polito.tdp.TravelManager.model;

import java.util.Objects;

public class Volo {
	
	private String itinID;
	private Aeroporto origin;
	private Aeroporto dest;
	private double FarePerMile;
	private int Passengers;
	private double ItinFare;
	private int Distance;
	
	public Volo(String itinID, Aeroporto origin, Aeroporto dest, double farePerMile, int passengers, double itinFare,
			int distance) {
		this.itinID = itinID;
		this.origin = origin;
		this.dest = dest;
		FarePerMile = farePerMile;
		Passengers = passengers;
		ItinFare = itinFare;
		Distance = distance;
	}

	public String getItinID() {
		return itinID;
	}

	public void setItinID(String itinID) {
		this.itinID = itinID;
	}

	public Aeroporto getOrigin() {
		return origin;
	}

	public void setOrigin(Aeroporto origin) {
		this.origin = origin;
	}

	public Aeroporto getDest() {
		return dest;
	}

	public void setDest(Aeroporto dest) {
		this.dest = dest;
	}

	public double getFarePerMile() {
		return FarePerMile;
	}

	public void setFarePerMile(double farePerMile) {
		FarePerMile = farePerMile;
	}

	public int getPassengers() {
		return Passengers;
	}

	public void setPassengers(int passengers) {
		Passengers = passengers;
	}

	public double getItinFare() {
		return ItinFare;
	}

	public void setItinFare(double itinFare) {
		ItinFare = itinFare;
	}

	public int getDistance() {
		return Distance;
	}

	public void setDistance(int distance) {
		Distance = distance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dest, itinID, origin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Volo other = (Volo) obj;
		return Objects.equals(dest, other.dest) && itinID == other.itinID && Objects.equals(origin, other.origin);
	}

	@Override
	public String toString() {
		return "Volo " + itinID + " da " + origin + " a " + dest;
	}

}
