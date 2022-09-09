package it.polito.tdp.TravelManager.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		
		model.loadAll();
		
//		List<List<Adiacenza>> result = model.percorso(model.mappaAeroporti.get("LAX").getName(), model.mappaAeroporti.get("JFK").getName(), 1, 730);
//		
//		for(List<Adiacenza> list : result) {
//			System.out.println(list);
//		}
		
//		List<Itinerario> result = model.percorso(model.getNameMap().get("LAX").getName(), model.getNameMap().get("JFK").getName(), 0, 730);
		
//		for(Itinerario i : result) {
//			System.out.println(i);
//		}
	}
}
