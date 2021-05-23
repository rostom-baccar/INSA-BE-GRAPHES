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
	protected static ShortestPathSolution solution1;
	
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	

	
}