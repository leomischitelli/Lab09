package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private BordersDAO bordersDAO;
	private SimpleGraph <Country, DefaultEdge> grafo;
	private HashMap <Integer, Country> countryMap;
	private List<Country> countries;

	public Model() {
		
		
		
	}
	
	private void creaGrafo(List<Border> coppie) {
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		for(Border b : coppie) {
			Graphs.addEdgeWithVertices(this.grafo, countryMap.get(b.getState1()), countryMap.get(b.getState2()));
		}
		
		
	}
	
	private void creaGrafo() {
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
//		Graphs.addAllVertices(null, null)
	}

	public List<Country> loadAllCountries(){
		if(this.countries == null) {
			this.bordersDAO = new BordersDAO(); 
			countries = new ArrayList<>(bordersDAO.loadAllCountries());
		}
		countryMap = new HashMap<Integer, Country>();
		for(Country c : countries)
			countryMap.put(c.getcCode(), c);
		return countries;
	}
	
	public Set<Country> calcolaConfini (int anno) {
		
		loadAllCountries();
		List<Border> coppie = bordersDAO.getCountryPairs(anno);
		creaGrafo(coppie);
		
		Set<Country> vertici = new HashSet<Country>(this.grafo.vertexSet());
		
		
		for(Country c : vertici) {
			c.setStatiConfinanti(this.grafo.degreeOf(c));
		}
		
//		int tot1 = 0;
//		for(Country c : vertici) {
//			int conn = inspector.connectedSetOf(c).size();
//			c.setConnessioni(conn);
//			tot1+=conn;
//		}
//		
//		List<Set<Country>> listaConnectedSets = inspector.connectedSets();
//		int tot2 = 0;
//		for(Set<Country> set : listaConnectedSets) {
//			tot2+=set.size();
//		}
//		
		return vertici;
	
	}
	
	public int getComponentiConnesse() {
		ConnectivityInspector <Country, DefaultEdge> inspector = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		int tot = 0;
		for(Set<Country> set : inspector.connectedSets()) {
			tot+=set.size();
		}
		
		return tot ;
	}
	
	public Set<Country> getStatiConfinanti(Country country){
		if(this.grafo == null) {
			return null;
		}
		
		ConnectivityInspector <Country, DefaultEdge> inspector = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		Set<Country> lista = inspector.connectedSetOf(country);
		if(lista.contains(country))
			lista.remove(country);
		return lista;
	}
	

}
