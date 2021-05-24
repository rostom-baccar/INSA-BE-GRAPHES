package Algos_PCC;

import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.weakconnectivity.*;
import org.insa.graphs.algorithm.weakconnectivity.WeaklyConnectedComponentsData;
import org.insa.graphs.algorithm.weakconnectivity.WeaklyConnectedComponentsSolution;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.insa.graphs.model.*;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
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
import org.insa.graphs.algorithm.AbstractSolution;

public class Tests_algos_PCC {

	// déclaration graphes
	private static Graph graphCarre;

	// déclaration de la liste des noeuds des graphes
	private static ArrayList<Node> nodesCarre;

	// tableaux de solutions
	protected static ShortestPathSolution[] solution_randoms_dijkstra0;
	protected static ShortestPathSolution[] solution_randoms_astar0;
	protected static ShortestPathSolution[] solution_randoms_bellman0;
	protected static ShortestPathSolution[] solution_randoms_dijkstra1;
	protected static ShortestPathSolution[] solution_randoms_astar1;
	protected static ShortestPathSolution[] solution_randoms_bellman1;
	protected static ShortestPathSolution[] solution_randoms_dijkstra2;
	protected static ShortestPathSolution[] solution_randoms_astar2;
	protected static ShortestPathSolution[] solution_randoms_bellman2;
	protected static ShortestPathSolution[] solution_randoms_dijkstra4;
	protected static ShortestPathSolution[] solution_randoms_astar4;
	protected static ShortestPathSolution[] solution_randoms_bellman4;

	@BeforeClass
	public static void init() throws IOException {

		// stockage du graphe à tester (carre)
		try {
			String mapCarre = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/carre.mapgr";
			GraphReader readerCarre = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarre))));
			graphCarre = readerCarre.read();
		} catch (Exception e) {
		}

		// on récupère les noeuds du graphe
		nodesCarre = new ArrayList<>(graphCarre.getNodes());

		// pour les noeuds randoms
		Random rand = new Random();

		// init du path qui commence du noeud_origine
		// path = new Path(graphCarre, noeud_origine);

		// Puisque le BeforeClass ne s'exécute qu'une seule fois, il faut stocker
		// toutes les solutions qu'on va tester. On les stocke donc dans un tableau
		// de solutions

		// déclarations des tableaux de solutions
		// MODE 0
		solution_randoms_dijkstra0 = new ShortestPathSolution[50];
		solution_randoms_astar0 = new ShortestPathSolution[50];
		solution_randoms_bellman0 = new ShortestPathSolution[50];
		// MODE 1
		solution_randoms_dijkstra1 = new ShortestPathSolution[50];
		solution_randoms_astar1 = new ShortestPathSolution[50];
		solution_randoms_bellman1 = new ShortestPathSolution[50];
		// MODE 2
		solution_randoms_dijkstra2 = new ShortestPathSolution[50];
		solution_randoms_astar2 = new ShortestPathSolution[50];
		solution_randoms_bellman2 = new ShortestPathSolution[50];
		// MODE 4
		solution_randoms_dijkstra4 = new ShortestPathSolution[50];
		solution_randoms_astar4 = new ShortestPathSolution[50];
		solution_randoms_bellman4 = new ShortestPathSolution[50];

		// boucle pour générer les solutions des 50 noeuds randoms sous différents modes
		
		int i=0;  //compteur
		while (i<50) {

			Node noeud_origine = nodesCarre.get(rand.nextInt(nodesCarre.size()));
			Node noeud_destination = nodesCarre.get(rand.nextInt(nodesCarre.size()));

			// Vérification que les noeuds sont différents (il y a un autre test quand il
			// s'agira du même noeud)

			if (!noeud_origine.equals(noeud_destination)) {

				// Origine et Destinations randoms

				// MODE 0
				// PCC
				ShortestPathData randoms_pcc0 = new ShortestPathData(graphCarre, noeud_origine, noeud_destination,
						ArcInspectorFactory.getAllFilters().get(0));
				// Dijkstra
				DijkstraAlgorithm randoms_dijkstra0 = new DijkstraAlgorithm(randoms_pcc0);
				solution_randoms_dijkstra0[i] = randoms_dijkstra0.doRun();
				// AStar
				AStarAlgorithm randoms_astar0 = new AStarAlgorithm(randoms_pcc0);
				solution_randoms_astar0[i] = randoms_astar0.doRun();
				// Bellman Ford
				BellmanFordAlgorithm randoms_dist_bellman0 = new BellmanFordAlgorithm(randoms_pcc0);
				solution_randoms_bellman0[i] = randoms_dist_bellman0.doRun();

				// MODE 1
				// PCC
				ShortestPathData randoms_pcc1 = new ShortestPathData(graphCarre, noeud_origine, noeud_destination,
						ArcInspectorFactory.getAllFilters().get(1));
				// Dijkstra
				DijkstraAlgorithm randoms_dijkstra1 = new DijkstraAlgorithm(randoms_pcc1);
				solution_randoms_dijkstra1[i] = randoms_dijkstra1.doRun();
				// AStar
				AStarAlgorithm randoms_astar1 = new AStarAlgorithm(randoms_pcc1);
				solution_randoms_astar1[i] = randoms_astar1.doRun();
				// Bellman Ford
				BellmanFordAlgorithm randoms_bellman1 = new BellmanFordAlgorithm(randoms_pcc1);
				solution_randoms_bellman1[i] = randoms_bellman1.doRun();

				// MODE 2
				// PCC
				ShortestPathData randoms_pcc2 = new ShortestPathData(graphCarre, noeud_origine, noeud_destination,
						ArcInspectorFactory.getAllFilters().get(2));
				// Dijkstra
				DijkstraAlgorithm randoms_dijkstra2 = new DijkstraAlgorithm(randoms_pcc2);
				solution_randoms_dijkstra2[i] = randoms_dijkstra2.doRun();
				// AStar
				AStarAlgorithm randoms_astar2 = new AStarAlgorithm(randoms_pcc2);
				solution_randoms_astar2[i] = randoms_astar2.doRun();
				// Bellman Ford
				BellmanFordAlgorithm randoms_bellman2 = new BellmanFordAlgorithm(randoms_pcc2);
				solution_randoms_bellman2[i] = randoms_bellman2.doRun();

				// MODE 4
				// PCC
				ShortestPathData randoms_pcc4 = new ShortestPathData(graphCarre, noeud_origine, noeud_destination,
						ArcInspectorFactory.getAllFilters().get(4));
				// Dijkstra
				DijkstraAlgorithm randoms_dijkstra4 = new DijkstraAlgorithm(randoms_pcc4);
				solution_randoms_dijkstra4[i] = randoms_dijkstra4.doRun();
				// AStar
				AStarAlgorithm randoms_astar4 = new AStarAlgorithm(randoms_pcc4);
				solution_randoms_astar4[i] = randoms_astar4.doRun();
				// Bellman Ford
				BellmanFordAlgorithm randoms_bellman4 = new BellmanFordAlgorithm(randoms_pcc4);
				solution_randoms_bellman4[i] = randoms_bellman4.doRun();
				//incrémentation compteur
				i++;
			}

		}
	}

	// TESTS

	// Origine et Destinations randoms
	// MODE 0
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra0() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra0[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra0[i].getPath().getLength()
					- solution_randoms_bellman0[i].getPath().getLength()) == 0);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar0() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar0[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar0[i].getPath().getLength()
					- solution_randoms_bellman0[i].getPath().getLength()) == 0);
		}
	}

	// MODE 1
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra1() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra1[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra1[i].getPath().getLength()
					- solution_randoms_bellman1[i].getPath().getLength()) == 0);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar1() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar1[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar1[i].getPath().getLength()
					- solution_randoms_bellman1[i].getPath().getLength()) == 0);
		}
	}

	// MODE 2
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra2() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra2[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra2[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman2[i].getPath().getMinimumTravelTime()) == 0);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar2() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar2[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar2[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman2[i].getPath().getMinimumTravelTime()) == 0);
		}
	}

	// MODE 4
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra4() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra4[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra4[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman4[i].getPath().getMinimumTravelTime()) == 0);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar4() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar4[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar4[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman4[i].getPath().getMinimumTravelTime()) == 0);
		}
	}

}