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
	Map<String, Aeroporto> mappaAeroporti;
	List<Aeroporto> aeroporti;
	List<Volo> voli;
	Graph<Aeroporto, DefaultWeightedEdge> grafo;
	List<List<Volo>> finale;
	
	public void loadAll(){
		aeroporti = new ArrayList<Aeroporto>(this.dao.loadAllAeroporti());
		
		System.out.println("AEROPORTI CARICATI");
		
		mappaAeroporti = new HashMap<String, Aeroporto>();
		
		for(Aeroporto a : aeroporti) {
			mappaAeroporti.put(a.getIATA(), a);
		}
		
		System.out.println("MAPPA AEROPORTI CREATA");
		
		voli = new ArrayList<Volo>(this.dao.loadAllVoli(mappaAeroporti));
		
		System.out.println("VOLI CARICATI");
		
		creaGrafo();
		
	}
	
	public List<Aeroporto> getAeroporti() {
		return this.aeroporti;
	}
	
	private void creaGrafo() {
		this.grafo = new SimpleDirectedWeightedGraph<Aeroporto, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, aeroporti);
		
		System.out.println("VERTICI: " + this.grafo.vertexSet().size());	
		
		
		for(Volo v : voli) {
			Graphs.addEdge(this.grafo, v.getOrigin(), v.getDest(), v.getItinFare());
		}
		
		System.out.println("ARCHI: " + this.grafo.edgeSet().size());
	}
	
	public List<List<Volo>> percorso(Aeroporto origine, Aeroporto destinazione, int scali){
		finale = new ArrayList<List<Volo>>();
		List<Volo> parziale = new ArrayList<Volo>();
		
		percorsoRicorsiva(origine, destinazione, parziale, scali);
		
		return finale;
	}

	private void percorsoRicorsiva(Aeroporto origine, Aeroporto destinazione, List<Volo> parziale, int scali) {
		if(parziale.size() <= scali) {
			finale.add(parziale);
		}
		
		GraphIterator<Aeroporto, DefaultWeightedEdge> visita = new DepthFirstIterator<>(this.grafo, origine);
		
		for(int i=0; i<=scali; i++) {
			while(visita.hasNext()) {
				Aeroporto a = visita.next();
			
			}
		}
		
	}
	
}
