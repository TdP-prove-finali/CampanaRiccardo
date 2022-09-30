package it.polito.tdp.TravelManager.model;

import java.util.Objects;

public class Adiacenza {
	
	private Aeroporto origine;
	private Aeroporto dest;
	private double prezzo;
	
	public Adiacenza(Aeroporto origine, Aeroporto dest, double prezzo) {
		this.origine = origine;
		this.dest = dest;
		this.prezzo = prezzo;
	}

	public Aeroporto getOrigine() {
		return origine;
	}

	public void setOrigine(Aeroporto origine) {
		this.origine = origine;
	}

	public Aeroporto getDest() {
		return dest;
	}

	public void setDest(Aeroporto dest) {
		this.dest = dest;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dest, origine, prezzo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		return Objects.equals(dest, other.dest) && Objects.equals(origine, other.origine)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo);
	}

	@Override
	public String toString() {
		return "Flight from: " + origine + " to: " + dest + ", priced: " + prezzo;
	}
}
