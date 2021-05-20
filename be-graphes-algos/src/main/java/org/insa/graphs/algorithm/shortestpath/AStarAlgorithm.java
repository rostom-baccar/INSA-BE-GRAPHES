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
    
    @Override
    protected Label[] labels_init() {
 	   LabelStar labels_star[] = new LabelStar[nbr_noeuds] ;
 	   List<Node> nodes = graphe.getNodes();
 	   
 	   double Cost = 0;

 	   //int MaxSpeed = Speed() ; 
 	   
 	   Point DestinationP = data.getDestination().getPoint() ; 
 	   
 	   for (Node node : nodes) {
 		  labels_star[node.getId()] = new LabelStar(node);
 		   
 		   // the cost is the distance between this point and the destination point, in meters
 		   if(data.getMode() == AbstractInputData.Mode.LENGTH) {
 			   Cost = node.getPoint().distanceTo(DestinationP);
 			   
 			   //or it's the time (ie Distance divided by speed) 
 	       	} else {
 	       		Cost = node.getPoint().distanceTo(DestinationP) / data.getMaximumSpeed(); 
 	       	}
 		  labels_star[node.getId()].set_vol_oiseau(Cost);
 	   }
 	   return labels_star ; 
     }
    
    
    
    
}
