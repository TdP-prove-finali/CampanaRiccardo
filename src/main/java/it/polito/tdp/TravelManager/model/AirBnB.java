package it.polito.tdp.TravelManager.model;

import java.util.Objects;

public class AirBnB {
	
	private int id;
	private double log_price;
	private double prezzo;
	private String property_type;
	private int accomodates;
	private String city;
	private String name;
	private String neighbourhood;
	private int number_of_reviews;
	private String review_scores_rating;
	
	public AirBnB(int id, double log_price, String property_type, int accomodates, String city,
			String name, String neighbourhood, int number_of_reviews, String review_scores_rating) {
		this.id = id;
		this.log_price = log_price;
		this.property_type = property_type;
		this.accomodates = accomodates;
		this.city = city;
		this.name = name;
		this.neighbourhood = neighbourhood;
		this.number_of_reviews = number_of_reviews;
		this.review_scores_rating = review_scores_rating;
		
		prezzo = Math.exp(log_price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLog_price() {
		return log_price;
	}

	public void setLog_price(double log_price) {
		this.log_price = log_price;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getProperty_type() {
		return property_type;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public int getAccomodates() {
		return accomodates;
	}

	public void setAccomodates(int accomodates) {
		this.accomodates = accomodates;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public int getNumber_of_reviews() {
		return number_of_reviews;
	}

	public void setNumber_of_reviews(int number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}

	public String getReview_scores_rating() {
		return review_scores_rating;
	}

	public void setReview_scores_rating(String review_scores_rating) {
		this.review_scores_rating = review_scores_rating;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirBnB other = (AirBnB) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return name + " in " + city;
	}

}
