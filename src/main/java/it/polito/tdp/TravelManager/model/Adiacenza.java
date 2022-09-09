package it.polito.tdp.TravelManager.model;

public class Adiacenza {
	
	private Aeroporto origine;
	private Aeroporto dest;
	private double prezzo;
	
	public Adiacenza(Aeroporto origine, Aeroporto dest, double prezzo) {
		super();
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
	public String toString() {
		return "Flight from: " + origine + " to: " + dest + ", priced: " + prezzo;
	}
}
