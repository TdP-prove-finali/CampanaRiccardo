package it.polito.tdp.itunes.model;

public class Adiacenze {
	
	private int t1Id;
	private int t2Id;
	private double delta;
	
	public Adiacenze(int t1Id, int t2Id, double delta) {
		this.t1Id = t1Id;
		this.t2Id = t2Id;
		this.delta = delta;
	}
	public int getT1Id() {
		return t1Id;
	}
	public void setT1Id(int t1Id) {
		this.t1Id = t1Id;
	}
	public int getT2Id() {
		return t2Id;
	}
	public void setT2Id(int t2Id) {
		this.t2Id = t2Id;
	}
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
	@Override
	public String toString() {
		return "t1Id =" + t1Id + ", t2Id =" + t2Id + ", delt a=" + delta;
	}
	
	

}
