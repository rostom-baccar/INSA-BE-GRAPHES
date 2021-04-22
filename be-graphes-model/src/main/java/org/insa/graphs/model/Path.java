//rostom
package org.insa.graphs.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Class representing a path between nodes in a graph.
 * </p>
 * 
 * <p>
 * A path is represented as a list of {@link Arc} with an origin and not a list
 * of {@link Node} due to the multi-graph nature (multiple arcs between two
 * nodes) of the considered graphs.
 * </p>
 *
 */
public class Path {

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the fastest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     */
    public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
        List<Arc> arcs = new ArrayList<Arc>();
        //1er cas: pas de noeuds
        if(nodes.size() == 0) {
        	return new Path(graph) ;  //rien à retourner pour Arc car on n'a pas de noeuds
        }
        //2eme cas: un seul noeud
        else if (nodes.size()==1) {
        	return new Path(graph, nodes.get(0)); //on retourne le noeud en question
        }
        //3eme cas: 2 noeuds ou plus (on peut ainsi parler de path. S'il n'y en a qu'un, c'est par défaut le plus rapide
        else {
        	//parcours des noeuds
        	for (int i=0; i<nodes.size()-1; i++) 
        	{
        		Arc Arc_retenu = null;
        				//on explore la liste des arcs de chaque noeud et on retient celui qui est le plus rapide
        				
        			for (Arc A : nodes.get(i).getSuccessors()){
        				//dans le cas ou un arc reboucle sur le même noeud: on prend cet arc comme init si l'arc n'a pas encore de valeur
 	        		if (A.getDestination() == nodes.get(i+1)){
 	        			
 	        			if (Arc_retenu == null){
 	        				Arc_retenu = A;
 	        			//sinon on prend l'arc le plus rapide	
 	        			}else if (A.getMinimumTravelTime() < Arc_retenu.getMinimumTravelTime() ) {
 	        				Arc_retenu = A; //recherche et màj de l'arc le plus rapide
 	        			}
 	        		}
 	        	}
        			//on renvoie une erreur si on ne trouve pas d'arc
        			if (Arc_retenu == null) {
     	        		throw new IllegalArgumentException();
     	        		
     	        	}else{
     	        		//sinon on ajoute l'arc retenu à la liste des arcs retenus du path le plus rapide
     	        		arcs.add(Arc_retenu);
     	        	}
        	}
        }
        return new Path(graph, arcs);
    }

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the shortest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     */
    public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	//cette fonction retourne le path le plus court qui passe par tous les noeuds donnés en paramètre
        List<Arc> arcs = new ArrayList<Arc>();
        //1er cas: pas de noeuds
        if (nodes.size()==0) {
        	return new Path(graph); //rien à retourner pour arcs
        }
        //2eme cas: un seul noeud
        else if (nodes.size()==1) {
        	return new Path(graph, nodes.get(0)); //on retourne le noeud en question
        }
        else {
        	//parcours des noeuds
        	 for (int i = 0; i < nodes.size()-1; i++)
 	        {
        		 Arc Arc_retenu = null ; 
        		//on explore la liste des arcs de chaque noeud et on retient celui qui est le plus court
 	        	for (Arc A : nodes.get(i).getSuccessors()){
 	        		//dans le cas ou un arc reboucle sur le même noeud: on prend cet arc comme init si l'arc n'a pas encore de valeur
 	        		if (A.getDestination() == nodes.get(i+1)){
 	        			
 	        			if (Arc_retenu == null){
 	        				Arc_retenu = A; 
 	        				//sinon on prend l'arc le plus court
 	        			}else if (A.getLength() < Arc_retenu.getLength()) {
 	        				Arc_retenu=A; //recherche et màj de l'arc le plus court
 	        			}
 	        		}
 	        	}
 	        	//on renvoie une erreur si on ne trouve pas d'arc
 	        	if (Arc_retenu == null) {
 	        		throw new IllegalArgumentException();
 	        		
 	        	}else{
 	        		//sinon on ajoute l'arc retenu à la liste des arcs retenus du path le plus court
 	        		arcs.add(Arc_retenu);
 	        	}
 	        }
        } 	
        //3eme cas: 2 noeuds ou plus 
        //Même raisonnement appliqué à la recherche le l'arc le plus rapide
        //avec dans ce cas le choix de l'arc le plus court
        return new Path(graph, arcs);
    }

    /**
     * Concatenate the given paths.
     * 
     * @param paths Array of paths to concatenate.
     * 
     * @return Concatenated path.
     * 
     * @throws IllegalArgumentException if the paths cannot be concatenated (IDs of
     *         map do not match, or the end of a path is not the beginning of the
     *         next).
     */
    public static Path concatenate(Path... paths) throws IllegalArgumentException {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
        }
        final String mapId = paths[0].getGraph().getMapId();
        for (int i = 1; i < paths.length; ++i) {
            if (!paths[i].getGraph().getMapId().equals(mapId)) {
                throw new IllegalArgumentException(
                        "Cannot concatenate paths from different graphs.");
            }
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        for (Path path: paths) {
            arcs.addAll(path.getArcs());
        }
        Path path = new Path(paths[0].getGraph(), arcs);
        if (!path.isValid()) {
            throw new IllegalArgumentException(
                    "Cannot concatenate paths that do not form a single path.");
        }
        return path;
    }

    // Graph containing this path.
    private final Graph graph;

    // Origin of the path
    private final Node origin;

    // List of arcs in this path.
    private final List<Arc> arcs;

    /**
     * Create an empty path corresponding to the given graph.
     * 
     * @param graph Graph containing the path.
     */
    public Path(Graph graph) {
        this.graph = graph;
        this.origin = null;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path containing a single node.
     * 
     * @param graph Graph containing the path.
     * @param node Single node of the path.
     */
    public Path(Graph graph, Node node) {
        this.graph = graph;
        this.origin = node;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path with the given list of arcs.
     * 
     * @param graph Graph containing the path.
     * @param arcs Arcs to construct the path.
     */
    public Path(Graph graph, List<Arc> arcs) {
        this.graph = graph;
        this.arcs = arcs;
        this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
    }

    /**
     * @return Graph containing the path.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return First node of the path.
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * @return Last node of the path.
     */
    public Node getDestination() {
        return arcs.get(arcs.size() - 1).getDestination();
    }

    /**
     * @return List of arcs in the path.
     */
    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    /**
     * Check if this path is empty (it does not contain any node).
     * 
     * @return true if this path is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.origin == null;
    }

    /**
     * Get the number of <b>nodes</b> in this path.
     * 
     * @return Number of nodes in this path.
     */
    public int size() {
        return isEmpty() ? 0 : 1 + this.arcs.size();
    }

    /**
     * Check if this path is valid.
     * 
     * A path is valid if any of the following is true:
     * <ul>
     * <li>it is empty;</li>
     * <li>it contains a single node (without arcs);</li>
     * <li>the first arc has for origin the origin of the path and, for two
     * consecutive arcs, the destination of the first one is the origin of the
     * second one.</li>
     * </ul>
     * 
     * @return true if the path is valid, false otherwise.
     * 
     */
    public boolean isValid() {
    	//1er cas: path vide
    	if (this.isEmpty()) {
    		return true;
    	}
    	//2eme cas: contient un seul noeud sans arcs
    	//remarque: this est une variable de même type que la classe sur laquelle on travaille.
    	//ici, elle représente donc un path
    	else if (this.size()==1) {
    		return true;
    	}
    	//3eme cas: premier arc est l'origine du path
    	//pour deux arcs consécutifs: le bout de l'un est l'origine de l'autre
    	else {
    		Node noeud_origine=this.getOrigin(); //origine d'un path est un noeud
    		for ( Arc A:this.arcs) {
    		//on parcourt tous les arcs: il faut que pour deux arcs consécutifs, le bout de l'un soit l'origine de l'autre
    		//on retourne false si l'origine du path est différente de l'origine du premier arc
    			if(!noeud_origine.equals(A.getOrigin())) {
    				return false ; 
    			}
    			//on met à jour le noeud pour passer aux deux arcs consécutifs suivants
    			noeud_origine=A.getDestination();
    		}
    	}
        return true;
    }

    /**
     * Compute the length of this path (in meters).
     * 
     * @return Total length of the path (in meters).
     * 
     
     */
    public float getLength() {
    	float L=0;
        //afin de calculer la longueur totale, on parcourt tous les arcs intermédiaires
        for (Arc A:this.arcs) {
        	L+=A.getLength();
        }
        return L;
    }

    /**
     * Compute the time required to travel this path if moving at the given speed.
     * 
     * @param speed Speed to compute the travel time.
     * 
     * @return Time (in seconds) required to travel this path at the given speed (in
     *         kilometers-per-hour).
     * 
     */
    public double getTravelTime(double speed) {
    	//on calcule le temps qu'on prend à parcourir un path avec une certaine vitesse
    	float L=getLength(); //longueur
        double T=0; //temps
        //la vitesse est en kmh
        //on la divise par 3.6 pour qu'elle soit en mps
        //on aura donc le temps en secondes
        double V=speed/3.6; //vitesse en mps
        T=L/V; //temps=longueur/vitesse
        return T;
    }

    /**
     * Compute the time to travel this path if moving at the maximum allowed speed
     * on every arc.
     * 
     * @return Minimum travel time to travel this path (in seconds).
     * 
     */
    public double getMinimumTravelTime() {
    	//pour connaître le temps minimum sur un certain path on additionne les temps minimums qu'on
    	//passe sur chaque arc avec la même fonction getMinimumTravelTime
    	double Tmin=0; //init du temps minimum
    	//on parcourt tous les arcs
    	for (Arc A:this.arcs) {
    		Tmin+=A.getMinimumTravelTime();
    	}
        return Tmin;
    }

}
