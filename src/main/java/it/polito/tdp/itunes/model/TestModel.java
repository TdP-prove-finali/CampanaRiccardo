package it.polito.tdp.itunes.model;

public class TestModel {
	public static void main(String[] args) {
		Model model = new Model();
		
		model.creaGrafo(new Genre(1, "Rock"));
		
		System.out.println(model.grafo.vertexSet().size() + " " + model.grafo.edgeSet().size());
		
		
		
	}
	


}
