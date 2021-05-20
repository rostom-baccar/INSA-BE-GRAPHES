package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;//pour reconnaître la classe arc (et autres)

public class Label implements Comparable<Label>{//pour la fonction compareTo
	
	//le label de chaque noeud pourra nous donner toutes les infos dont on a besoin
	//lors de l'écriture de l'algo de Dijkstra
	
	//instances
	private Node sommet_courant;


	private boolean marque;
	private double coût;
	private Arc père; //il vaut mieux stocker l'arc que le sommet d'après le sujet
	
	//constructeur
	public Label (Node sommet_courant) { //pas besoin de spécifier les autres attributs, on connait déjà leur init
		this.sommet_courant=sommet_courant;
		this.marque=false; //init: le sommet n'est pas marqué quand il est créé
		this.coût= Double.POSITIVE_INFINITY; //init: le coût initial d'un sommet est l'infini
		this.père=null; //init: un sommet n'a initialement pas de père
	}
	
	//getters et setters générés automatiquement avec eclipse
	public Node getSommet_courant() {
		return this.sommet_courant;
	}

	public void setSommet_courant(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
	}

	public boolean getMarque() {
		return this.marque;
	}

	public void setMarque_true() {
		this.marque = true;
	}

	public double getCost() {
		return this.coût;
	}

	public void setCost(double coût) {
		this.coût = coût;
	}

	public Arc getPère() {
		return this.père;
	}

	public void setPère(Arc père) {
		this.père = père;
	}
	
	public double getTotalCost() {
		return this.getCost();
	}
	
	@Override
    public int compareTo(Label other) {
        return Double.compare(this.getTotalCost(), other.getTotalCost());
    }

}
