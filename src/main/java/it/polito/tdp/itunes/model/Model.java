package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	Graph<Track, DefaultWeightedEdge> grafo;
	ItunesDAO dao;
	Map<Integer, Track> canzoniMap = new HashMap<Integer, Track>();
	List<Track> canzoni;
	List<Adiacenze> adiacenze;
	ConnectivityInspector<Track, DefaultWeightedEdge> ispettore;
	List<Track> risultato;
	int maxByte;
	
	public Model() {
		super();
		this.dao = new ItunesDAO();
	}

	public List<Track> getCanzoniGrafo(Genre genre){
		return this.dao.getCanzoniGrafo(genre);
	}
	
	public List<Adiacenze> getAllAdiacenze(Genre g){
		return this.dao.getAllAdiacenze(g);
	}

	public void creaGrafo(Genre g) {
		this.grafo = new SimpleWeightedGraph<Track, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		canzoni = this.getCanzoniGrafo(g);
		for(Track t : canzoni) {
			canzoniMap.put(t.getTrackId(), t);
		}
		
		adiacenze = this.getAllAdiacenze(g);
		
		Graphs.addAllVertices(this.grafo, canzoni);
		
		for(Adiacenze a : adiacenze) {
			Graphs.addEdge(this.grafo, canzoniMap.get(a.getT1Id()), canzoniMap.get(a.getT2Id()), Math.abs(a.getDelta()));
		}
		
		ispettore = new ConnectivityInspector<Track, DefaultWeightedEdge>(this.grafo);
	}
	
	public List<Genre> getAllGenres(){
		return this.dao.getAllGenres();
	}

	public List<Adiacenze> getAdiacenze() {
		return adiacenze;
	}
	
	public List<Adiacenze> getDeltaMax() {
		double deltaMax = 0;
		List<Adiacenze> aList = new ArrayList<Adiacenze>();
		
		for(Adiacenze  a : adiacenze) {
			if(a.getDelta() > deltaMax) {
				deltaMax = a.getDelta();
				if(!aList.contains(a)) {
					aList.add(a);
				}
			}
		}
		
		List<Adiacenze> result = new ArrayList<Adiacenze>();
		
		for(Adiacenze a : aList){
			if(a.getDelta() == deltaMax) {
				result.add(a);
			}
		}
		
		return result;
	}
	
	public int sizeV() {
		return this.grafo.vertexSet().size();
	}
	
	public int sizeE() {
		return this.grafo.edgeSet().size();
	}

	public Graph<Track, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	public List<Track> getListaCanzoni(int m, Track t) {
		List<Track> parziale = new ArrayList<Track>();
		Set<Track> compconnessa = ispettore.connectedSetOf(t);
		maxByte = m;
		parziale.add(t);
		risultato = new ArrayList<Track>(parziale);
		
		ricorsione(parziale, compconnessa);
		
		return risultato;
	}

	private void ricorsione(List<Track> parziale, Set<Track> compconnessa) {
		if(parziale.size() > risultato.size()) {
			risultato = new ArrayList<Track>(parziale);
		}
		
		for(Track t : compconnessa) {
			if(!parziale.contains(t)) {
				parziale.add(t);
				if(sommabyte(parziale) <= maxByte) {
					ricorsione(parziale, compconnessa);
				}
				parziale.remove(t);
			}
		}
	}

	private int sommabyte(List<Track> parziale) {
		int somma = 0;
		for(Track t : parziale) {
			somma += t.getBytes();
		}
		
		return somma;
	}
	
	public Set<Track> getCompConnessa(Track t){
		return this.ispettore.connectedSetOf(t);
	}

}
