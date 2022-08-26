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
	
	TravelManagerDAO dao = new TravelManagerDAO();
	Map<String, Aeroporto> mappaAeroporti;		//IATA
	Map<String, Aeroporto> mappaNomi;			//names
	List<Aeroporto> aeroporti;
	List<Volo> voli;
	Graph<Aeroporto, DefaultWeightedEdge> grafo;
	List<List<DefaultWeightedEdge>> finale;
	List<Adiacenza> adiacenze;
	List<AirBnB> bnbs;
	
	public void loadAll(){
		aeroporti = new ArrayList<Aeroporto>(this.dao.loadAllAeroporti());
		
		System.out.println("AEROPORTI CARICATI");
		
		mappaAeroporti = new HashMap<String, Aeroporto>();
		mappaNomi = new HashMap<String, Aeroporto>();
		
		for(Aeroporto a : aeroporti) {
			mappaAeroporti.put(a.getIATA(), a);
			mappaNomi.put(a.getName(), a);
		}
		
		System.out.println("MAPPE AEROPORTI CREATE");
		
//		voli = new ArrayList<Volo>(this.dao.loadAllVoli(mappaAeroporti));		//caricare tutti i voli richiede 3 minuti
//		System.out.println("VOLI CARICATI");
		
		
//		for(Aeroporto a1 : aeroporti) {
//			for(Aeroporto a2 : aeroporti) {										//caricare le adiacenze con un doppio ciclo for richiede 16 giorni secondo veloci calcoli
//				if(!a1.equals(a2)) {
//					adiacenze.add(this.dao.loadAdiacenze(a1, a2));
//				}
//			}
//		}
		
		adiacenze = new ArrayList<Adiacenza>(this.dao.loadAllAdiacenze(mappaAeroporti)); 		//caricare tutte le adiacenze con un'unica query richiede 2 minuti
		
		System.out.println("ADIACENZE CARICATE");
		
		creaGrafo();
		
	}
	
	public List<Aeroporto> getAeroporti() {
		return this.aeroporti;
	}
	
	private void creaGrafo() {
		this.grafo = new SimpleDirectedWeightedGraph<Aeroporto, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, aeroporti);
		
		System.out.println("VERTICI: " + this.grafo.vertexSet().size());		//445 vertici
		
		
//		for(Volo v : voli) {
//			Graphs.addEdge(this.grafo, v.getOrigin(), v.getDest(), v.getItinFare()); //aggiunge solo la prima istanza di volo con origine x e destinazione y
//		}
		
		for(Adiacenza a : adiacenze) {
			Graphs.addEdge(this.grafo, a.getOrigine(), a.getDest(), a.getPrezzo());
		}	
				
		System.out.println("ARCHI: " + this.grafo.edgeSet().size());			//11180 archi
	}
	
	public List<Itinerario> percorso(String origine, String destinazione, int scali, double prezzo){
		finale = new ArrayList<List<DefaultWeightedEdge>>();
		List<DefaultWeightedEdge> parziale = new ArrayList<DefaultWeightedEdge>();
		List<Aeroporto> listaScali = new ArrayList<Aeroporto>();
		listaScali.add(mappaNomi.get(origine));
		
		percorsoRicorsiva(mappaNomi.get(origine), mappaNomi.get(destinazione), parziale, scali, listaScali, prezzo);
		
		//in questo momento finale contiene liste di archi, devo trasformarle in liste di adiacenze prima di returnarla
		
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
		if(listaScali.size() == scali + 2 && !listaScali.get(listaScali.size()-1).equals(destinazione)) {		//caso terminale negativo
			return;
		}
		
		if(listaScali.size() <= scali + 2 && listaScali.get(listaScali.size()-1).equals(destinazione) && sommaprezzo(parziale) <= prezzo) {		//caso terminale positivo
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
	
	public Map<String, Aeroporto> getMappaNomi(){
		return this.mappaNomi;
	}
	
	public void loadBnBs() {
		bnbs = new ArrayList<AirBnB>(this.dao.loadAllAirBnBs());
	}
	
	public List<String> loadTypes(){
		return this.dao.loadTypes();
	}
	
}
