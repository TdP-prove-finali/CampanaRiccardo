package it.polito.tdp.TravelManager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.TravelManager.db.TravelManagerDAO;

public class Model {
	
	private TravelManagerDAO dao = new TravelManagerDAO();
	
	private Map<String, Aeroporto> IATAMap;		
	private Map<String, Aeroporto> nameMap;	
	private List<Aeroporto> airports;
	
	private Graph<Aeroporto, DefaultWeightedEdge> grafo;
	
	private List<List<DefaultWeightedEdge>> finale;
	private List<Adiacenza> adiacenze;
	
	private List<AirBnB> bnbs;
	private List<AirBnB> finaleBnb;
	
//	private List<Volo> flights;
	
	
	public Map<String, Aeroporto> getMappaNomi(){
		return this.nameMap;
	}
	
	public List<Aeroporto> getAeroporti() {
		return this.airports;
	}
	
	public void loadBnBs() {
		bnbs = new ArrayList<AirBnB>(this.dao.loadAllAirBnBs());
	}
	
	public List<String> loadTypes(){
		return this.dao.loadTypes();
	}
	
	
	
	
	public void loadAll(){
		airports = new ArrayList<Aeroporto>(this.dao.loadAllAeroporti());
		
		IATAMap = new HashMap<String, Aeroporto>();
		nameMap = new HashMap<String, Aeroporto>();
		
		for(Aeroporto a : airports) {
			IATAMap.put(a.getIATA(), a);
			getMappaNomi().put(a.getName(), a);
		}
	
		
/**
 * 		previous edge searching method using a direct query, 3 minutes loading time	
 */
		
//		voli = new ArrayList<Volo>(this.dao.loadAllVoli(mappaAeroporti));		
//		System.out.println("VOLI CARICATI");
		
		
		
/**		
 * 		previous edge searching method using nested for cycles, supposed loading time of several days
 */
		
//		for(Aeroporto a1 : aeroporti) {
//			for(Aeroporto a2 : aeroporti) {										
//				if(!a1.equals(a2)) {
//					adiacenze.add(this.dao.loadAdiacenze(a1, a2));
//				}
//			}
//		}
		
		
	
/**
 * 		current edge searching method, 2 minutes loading time	
 */
	
		adiacenze = new ArrayList<Adiacenza>(this.dao.loadAllAdiacenze(IATAMap)); 		
		
		System.out.println("ADIACENZE CARICATE");
		
		creaGrafo();
		
	}
	
	private void creaGrafo() {
		this.grafo = new SimpleDirectedWeightedGraph<Aeroporto, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, airports);
		
		//current vertexSet size = 445 
		System.out.println("VERTICI: " + this.grafo.vertexSet().size());		
		
		
/**
 * 		previous edge loading method, loaded only the first instance of a flight with origin x and destination y.
 * 		the edge weight, or flight fare, would not have been accurate.
 */
		
//		for(Volo v : voli) {
//			Graphs.addEdge(this.grafo, v.getOrigin(), v.getDest(), v.getItinFare()); 
//		}
		
		
/**
 *		current edge loading method, the query returns an average fare for all flights with origin x and destination y.
 *		the edge weight, or flight fare, is now more accurate  
 */
		for(Adiacenza a : adiacenze) {
			Graphs.addEdge(this.grafo, a.getOrigine(), a.getDest(), a.getPrezzo());
		}	
				
		//current edgeSet size = 11180 
		System.out.println("ARCHI: " + this.grafo.edgeSet().size());			
	}
	
	
	
	
	public List<Itinerario> percorso(String origine, String destinazione, int scali, double prezzo){
		finale = new ArrayList<List<DefaultWeightedEdge>>();
		List<DefaultWeightedEdge> parziale = new ArrayList<DefaultWeightedEdge>();
		List<Aeroporto> listaScali = new ArrayList<Aeroporto>();
		listaScali.add(getMappaNomi().get(origine));
		
		percorsoRicorsiva(getMappaNomi().get(origine), getMappaNomi().get(destinazione), parziale, scali, listaScali, prezzo);

		List<Itinerario> finaleItinerari = new ArrayList<Itinerario>();
		
		for(List<DefaultWeightedEdge> list : finale) {
			List<Adiacenza> percorso = new ArrayList<Adiacenza>();
			List<Double> prezzi = new ArrayList<Double>();
			
			for(DefaultWeightedEdge d : list) {
				Adiacenza a = new Adiacenza(this.grafo.getEdgeSource(d), this.grafo.getEdgeTarget(d), this.grafo.getEdgeWeight(d));
				percorso.add(a);
				prezzi.add(a.getPrezzo());
			}
			
			finaleItinerari.add(new Itinerario(percorso.get(0).getOrigine(), percorso.get(percorso.size()-1).getDest(), percorso, prezzi));
		}
		
		return finaleItinerari;
	}

	private void percorsoRicorsiva(Aeroporto origine, Aeroporto destinazione, List<DefaultWeightedEdge> parziale, int scali, List<Aeroporto> listaScali, double prezzo) {
		if(listaScali.size() == scali + 2 && !listaScali.get(listaScali.size()-1).equals(destinazione)) {
			return;
		}
		
		if(listaScali.size() <= scali + 2 && listaScali.get(listaScali.size()-1).equals(destinazione) && sommaprezzo(parziale) <= prezzo) {
			finale.add(new ArrayList<DefaultWeightedEdge>(parziale));
			return;
		}
		
		GraphIterator<Aeroporto, DefaultWeightedEdge> visita = new DepthFirstIterator<>(this.grafo, listaScali.get(listaScali.size()-1));
		
		for(int i=0; i<=scali; i++) {
			while(visita.hasNext()) {
				Aeroporto a = visita.next();
				
				if(this.grafo.containsEdge(listaScali.get(listaScali.size()-1), a)){
					listaScali.add(a);
					
					if(listaScali.size() <= scali + 2 && sommaprezzo(parziale) <= prezzo) {	
					
						parziale.add(this.grafo.getEdge(listaScali.get(listaScali.size()-2), a));
						percorsoRicorsiva(origine, destinazione, parziale, scali, listaScali, prezzo);
						parziale.remove(parziale.size()-1);
						
					}
					
					listaScali.remove(a);
				}
			}
		}
	}

	private double sommaprezzo(List<DefaultWeightedEdge> parziale) {
		double tot = 0;
		
		for(DefaultWeightedEdge d : parziale) {
			tot += this.grafo.getEdgeWeight(d);
		}
		
		return tot;
	}

	
	
	
	public List<AirBnB> ricercaBnb(String type, int prezzo, int accommodations, int reviews, int rating, String arrival_city) {
		finaleBnb = new ArrayList<AirBnB>();
		List<AirBnB> parziale = new ArrayList<AirBnB>();
		
		String arrival = "";
		
		if(arrival_city.compareTo("Los Angeles") == 0) {
			arrival = "LA";
		}
		
		if(arrival_city.compareTo("New York") == 0) {
			arrival = "NYC";
		}
		
		if(arrival_city.compareTo("Washington") == 0) {
			arrival = "DC";
		}
		
		if(arrival_city.compareTo("San Francisco") == 0) {
			arrival = "SF";
		}
		
		if(arrival_city.compareTo("Chicago") == 0) {
			arrival = arrival_city;
		}
		
		if(arrival_city.compareTo("Boston") == 0) {
			arrival = arrival_city;
		}
		
		List<AirBnB> bnbsInCity = new ArrayList<AirBnB>();
		
		for(AirBnB a : bnbs) {
			if(a.getCity().compareTo(arrival) == 0) {
				bnbsInCity.add(a);
			}
		}
		
		int livello = 0;
		
		ricercaBnbRicorsiva(parziale, type, prezzo, accommodations, reviews, rating, livello, bnbsInCity);
		
		return finaleBnb;
	}

	private void ricercaBnbRicorsiva(List<AirBnB> parziale, String type, int prezzo, int accommodations, int reviews,
			int rating, int livello, List<AirBnB> bnbsInCity) {
		
		if(livello == bnbsInCity.size()) {
			finaleBnb = new ArrayList<AirBnB>(parziale);
			return;
		}
		
		if(rispettaParametri(bnbsInCity.get(livello), type, prezzo, accommodations, reviews, rating)){
			parziale.add(bnbsInCity.get(livello));
		}
		
		livello++;
		ricercaBnbRicorsiva(parziale, type, prezzo, accommodations, reviews, rating, livello, bnbsInCity);
		
	}

	private boolean rispettaParametri(AirBnB a, String type, int prezzo, int accommodations, int reviews, int rating) {
		double rating_bnb = -1;
		
		try {
			rating_bnb = Double.parseDouble(a.getReview_scores_rating());
		} catch (NumberFormatException e) {
			rating_bnb = 0;
		}
		
		if(a.getAccommodates() >= accommodations && a.getPrezzo() <= prezzo && (a.getProperty_type().compareTo(type) == 0 || 
				type.compareTo("No Preference") == 0) && a.getNumber_of_reviews() >= reviews && rating_bnb >= rating) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
}
