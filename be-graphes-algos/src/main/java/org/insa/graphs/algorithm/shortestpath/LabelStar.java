package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;

public class LabelStar extends Label implements Comparable<Label> {
	
	protected double coût_vol_oiseau ; 
	
	// Constructeur
	public LabelStar(Node sommet_courant) {
		super(sommet_courant) ; 
		//le coût du noeud vers la destination à vol d'oiseau est initialisé à l'infini
		this.coût_vol_oiseau = Double.POSITIVE_INFINITY;
	}
	
	//fonction renvoyant le coût total du label: son coût + le trajet à vol d'oiseau vers la destination
	public double getTotalCost() {
		return this.getCost()+this.coût_vol_oiseau;
	}
		
	//fonction renvoyant seulement le trajet à vol d'oiseau
	public double get_vol_oiseau() {
		return this.coût_vol_oiseau;
	}
	
	//fonction permettant de changer le cout du trajet à vol d'oiseau initialisé à l'infini (setter)
	public void set_vol_oiseau(double coût_vol_oiseau) {
        this.coût_vol_oiseau = coût_vol_oiseau;
    } 
		
	//fonction comparant le trajet à vol d'oiseau de deux labels différents pour en choisir un
	//en cas d'égalité de coût depuis l'origine
    public int compareTo(LabelStar autre_label) {
    	return Double.compare(this.get_vol_oiseau(), autre_label.get_vol_oiseau());
	}
	
	
	
	
}
