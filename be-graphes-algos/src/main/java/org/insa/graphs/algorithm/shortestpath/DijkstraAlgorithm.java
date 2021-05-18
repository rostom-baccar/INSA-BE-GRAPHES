package org.insa.graphs.algorithm.shortestpath;

//import de classes & d'exceptions
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
//import de java.util 
import java.util.*;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	  protected final ShortestPathData data = getInputData();
      
      //import du graphe
	  protected Graph graphe = data.getGraph();
	  //import nbr de noeuds
	  protected int nbr_noeuds = graphe.size();
	
	  
	//constructeur hérité
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    
	
    //fonction créant un tableau de labels de taille nbr_noeuds 
    protected Label[] labels_init() {
    	
	    Label[] labels = new Label[nbr_noeuds];

	    //afin d'initialiser les labels, on utilise une liste des noeuds du graphe
	    //l'initialisation se fait automatiquement à partir du constructeur de la classe label
	    List<Node> nodes = graphe.getNodes();
	    for (Node node : nodes) {
	    	labels[node.getId()] = new Label(node);
	    }
	    //on retourne le tableau créé
	    return labels ; 
    }
    
    @Override
    public ShortestPathSolution doRun() {
    	//déclaration du tableau de labels
    	Label[] labels ; 
    	
    	//appel de la fonction: labels créés et initialisés
    	 labels = labels_init() ; 
    	
        //initialisation du tas
        //création du tas des labels
        BinaryHeap<Label> tas = new BinaryHeap<>();     
        
        //Création des noeuds origine et destination avec leur labels
        Node noeud_origine = data.getOrigin();
        Label label_origine = labels[noeud_origine.getId()];  
        Node Destination = data.getDestination();
        Label label_destination = labels[Destination.getId()];
       
        //insertion du noeud origine dans le tas
        label_origine.setCost(0); //On initialise son cout à 0;
        tas.insert(label_origine);
        
        //Notification utilisateur
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        //Label utilisé dans les itérations pour faire avancer l'algo
        Label label_courant = null;
        
        //Cette boucle tourne tant que le sommet destination n'est pas marqué ET tant que le tas n'est pas vide 
        //çad 
        //Cette boucle s'arrête quand le sommet destination est marqué OU quand le tas est vide 
        while (!label_destination.getMarque() && !tas.isEmpty() ) {
        	
        	//On extrait l'élément du tas avec le coût minimal: ça va être le label origine
        	label_courant = tas.deleteMin() ; 
        	
        	//On marque le label extrait
        	label_courant.setMarque_true();
        	
        	//Notification utilisateur
        	/*Notify observers about the node being marked*/ 
        	notifyNodeMarked(label_courant.getSommet_courant());
        	
        	//On crée une liste de successeurs du noeud courant pour simplifier leur parcours
            List<Arc> successeurs = label_courant.getSommet_courant().getSuccessors() ;
            
        	//Parcours de tous les successeurs du noeud courant
        	for (Arc arc_courant: successeurs ) {
     
        		//Condition importante pour vérifier que l'arc en question est permis selon le mode de déplacement
        		if (data.isAllowed(arc_courant)) {
        			
        			//déclaration du noeud destination de chaque arc
	        		Label destination_iter = labels[arc_courant.getDestination().getId()];
	        		
	        		//Notification
	        		/*Notify observers about the node being reached*/ 
	        		notifyNodeReached(arc_courant.getDestination());
	        		
	        		//Si le noeud destination de l'arc n'est pas marqué
	        		if(!destination_iter.getMarque()) {
	        				
	        			//dans le cas où le coût du noeud destination est plus grand que le coup du noeud courant + la longueur entre les deux noeuds:
	        			
		        			if (destination_iter.getCost() > label_courant.getCost() + data.getCost(arc_courant)) {
		        				
		        				//si le noeud destination de l'arc est déjà dans le tas, on l'enlève (pour l'ajouter à la fin quand il sera màj)
		        				try {
		        					tas.remove(destination_iter);
		        				} catch(ElementNotFoundException e) {}
		        				
		        				//on met à jour le coût du noeud destination de l'arc 
		        				destination_iter.setCost(label_courant.getCost() + data.getCost(arc_courant));
		        				//on met à jour son père qui devient le noeud courant (on met tout l'arc car c'est plus pratique)
		        				destination_iter.setPère(arc_courant);
		        				//on place le noeud destination de l'arc dans le tas
		        				tas.insert(destination_iter);
		        				System.out.println("Cout label courant: " +destination_iter.getCost());
		        			}
	        		}
        		}
        	}
        }
        
        

        
        //Maintenant, tous les sommets ont été marqués (si tout s'est bien passé)
        
        //On traite maintenant les cas restants où il n'y a pas de solution
        
        ShortestPathSolution solution = null; //Variable initialisée à null qui change selon la situation
        
        //Si le sommet destination n'a pas de prédecesseur ou si celui-ci n'a pas été marqué (l'un entraîne l'autre normalement),
        //la solution n'existe pas
		if(label_destination.getPère()==null || !label_destination.getMarque()) {
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        } 
		//Sinon, on a trouvé la destination
		else {
        	
        	//Notification utilisateur
        	// The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());
   
            //On reconstitue le cheminc créé:
            //On crée une liste contenant tous les arcs menants à la destination
        	ArrayList<Arc> arcs = new ArrayList<>();
        	
        	//On commence par la destination (puisqu'on s'y est arrêté) et on retrace le chemin en ajoutant à chaque fois le père du noeud
        	//jusqu'à arriver au noeud origine en mettant à jour à chaque fois le label courant
        	while(!label_courant.equals(label_origine)) {
        		arcs.add(label_courant.getPère());
        		//on met à jour le label courant
        		label_courant = labels[label_courant.getPère().getOrigin().getId()];
        		
        	}
        	//On inverse le tout pour avoir le chemin de l'origine à la destination et pas l'inverse
            Collections.reverse(arcs);
            
            
            //Création de la solution finale à partir de la liste des arcs créée
            Path path = new Path(graphe, arcs) ; 
            
            //On teste si le chemin créé est valide avant de créer la solution finale
            if(path.isValid()) {
            	
                //on crée la solution finale
                solution = new ShortestPathSolution(data, Status.OPTIMAL, path);
                
            } else {
            	System.out.println("Le chemin n'est pas valide") ; 
            }
      
            
        	
        }
		        return solution;
    }

}