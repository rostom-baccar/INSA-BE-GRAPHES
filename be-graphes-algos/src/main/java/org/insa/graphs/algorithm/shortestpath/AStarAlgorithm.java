package org.insa.graphs.algorithm.shortestpath;
import java.util.List;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.*;


public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    
    //on redéfinit la fonction labels_init définie dans la classe DijkstraAlgorithm
    //pour créer cette fois un tableau de labels star de taille nbr_noeuds 
    
    protected Label[] labels_init() {
 	   LabelStar labels[] = new LabelStar[nbr_noeuds] ;
 	   List<Node> nodes = graphe.getNodes();
 	   
 	   double coût = 0;
 	   int vitesse_max=Speed();
 	   
 	   //point de destination
 	   Point point_destination = data.getDestination().getPoint() ; 
 	   
 	   //création des labels star
 	   for (Node noeud : nodes) {
 		  labels[noeud.getId()] = new LabelStar(noeud);
 		   
 		  //si le mode sélectionné est LENGTH, on calcule le coût en question en calculant
 		  //la longueur entre le noeud courant et la destination
 		   if(data.getMode() == AbstractInputData.Mode.LENGTH) {
 			  coût = noeud.getPoint().distanceTo(point_destination);
 			   
 		//si c'est le temps le plus court qui nous intéresse, on divise cette longueur par la vitesse 
 	       	} else {
 	       	coût = noeud.getPoint().distanceTo(point_destination) / vitesse_max; 
 	       	}
 		  labels[noeud.getId()].set_vol_oiseau(coût);
 	   }
 	   return labels ; 
     }
    // pour éviter le problème des sommets marqués qui font des cercles
    private int Speed() {
 	   int maxspeed_data =  data.getMaximumSpeed() ; 
 	   int maxspeed_graphe = graphe.getGraphInformation().getMaximumSpeed() ;
 	   int Speed = Math.min(maxspeed_data, maxspeed_graphe) ; 
 	   
 	   if (maxspeed_data ==  GraphStatistics.NO_MAXIMUM_SPEED && maxspeed_graphe ==  GraphStatistics.NO_MAXIMUM_SPEED ) {
 		   Speed = 130 ;
 	   }
 	   if (maxspeed_data ==  GraphStatistics.NO_MAXIMUM_SPEED) {
 		   Speed = maxspeed_graphe; 
 	   }
 	   if (maxspeed_graphe ==  GraphStatistics.NO_MAXIMUM_SPEED) {
 		   Speed = maxspeed_data; 
 	   }
 	return Speed ; 
    }
    
    
    
}
