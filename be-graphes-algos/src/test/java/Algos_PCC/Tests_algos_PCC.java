package Algos_PCC;

import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.ArcInspectorFactory ;
import org.insa.graphs.algorithm.weakconnectivity.*;
import org.insa.graphs.algorithm.weakconnectivity.WeaklyConnectedComponentsData ; 
import org.insa.graphs.algorithm.weakconnectivity.WeaklyConnectedComponentsSolution ;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random ; 
import org.insa.graphs.model.*;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path ; 
import org.insa.graphs.model.io.BinaryGraphReader; 
import org.insa.graphs.model.io.GraphReader; 
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.PathReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.insa.graphs.algorithm.AbstractSolution ; 


public class Tests_algos_PCC {
	
	//déclaration graphes
	private static Graph graphCarre;
	
	//déclaration de la liste des noeuds des graphes
	private static ArrayList<Node> nodesCarre;
	
	//solutions des algos
	protected static ShortestPathSolution 
	solution_dijkstra,solution_astar,solution_bellman;
	
	//import de chemins pour le test
	protected static Path path ; 
	
	@BeforeClass
	public static void init() throws IOException {
		
	//stockage du graphe à tester (carre)
		try {
			String mapCarre = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/carre.mapgr" ; 
			GraphReader readerCarre = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarre))));
			graphCarre = readerCarre.read() ; 
		} catch (Exception e) {}	
		
		
		//on récupère les noeuds du graphe
		nodesCarre = new ArrayList<>(graphCarre.getNodes()) ; 
		
		//paramètres test 1: les 3 algos renvoient le même résultat
		//on commence par choisir une origine et une destination au hasard
		
		Random rand = new Random(); 
		Node noeud_origine = nodesCarre.get(rand.nextInt(nodesCarre.size()));
		Node noeud_destination = nodesCarre.get(rand.nextInt(nodesCarre.size()));
		
		//init du path qui commence du noeud_origine
		path = new Path(graphCarre, noeud_origine);
		
		
		//Origine et Destinations randoms
			//MODE 0
				//PCC 
		ShortestPathData random_pcc0 = new ShortestPathData(graphCarre, noeud_origine, noeud_destination, ArcInspectorFactory.getAllFilters().get(0));
				//Dijsktra 
		DijkstraAlgorithm random_dijkstra0 = new DijkstraAlgorithm(random_pcc0);
		solution_dijkstra = random_dijkstra0.doRun();
				//AStar
		AStarAlgorithm random_astar0 = new AStarAlgorithm(random_pcc0) ; 
		solution_astar = random_astar0.doRun();
				//Bellman Ford
		BellmanFordAlgorithm random_bellman0 = new BellmanFordAlgorithm(random_pcc0);
		solution_bellman =  random_bellman0.doRun();
		
		
	}
	
	//TESTS
	
	//Origine et Destinations randoms
		//MODE 0
			//Dijkstra: test validité du path + même solution que Bellman
	@Test
	public void algo_dijkstra() {
		assertTrue(solution_dijkstra.getPath().isValid()) ;
		assertTrue(Math.abs(solution_dijkstra.getPath().getLength() - solution_bellman.getPath().getLength()) == 0);
		
	}
			//AStar: test validité du path + même solution que Bellman
	@Test
	public void algo_astar() {
		assertTrue(solution_astar.getPath().isValid()) ;
		assertTrue(Math.abs(solution_astar.getPath().getLength() - solution_bellman.getPath().getLength()) == 0);
	}
		
		
		
	
	
	
	
	
	

	
}