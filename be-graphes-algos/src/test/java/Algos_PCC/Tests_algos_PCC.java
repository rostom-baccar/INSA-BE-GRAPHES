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
	private static Graph graphe_carre;
	private static Graph graphe_haute_garonne;
	private static Graph graphe_toulouse;
	private static Graph graphe_belgique;
	private static Graph graphe_tunisie;
	private static Graph graphe_cuba;

	// déclaration de la liste des noeuds des graphes (pour ces tests, on n'a besoin que des noeuds du graphe carre)
	private static ArrayList<Node> noeuds_carre;

	// déclarations paths
	private static Path path_bikini_hg;
	private static Path path_insa_toulouse;
	private static Path path_belgique_eu;
	private static Path path_org_dest;

	// tableaux de solutions pour tests randoms
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

	// solutions pour tests paths avec Dijkstra et AStar
	protected static ShortestPathSolution pcc_pathtest_dijkstra_bikini0;
	protected static ShortestPathSolution pcc_pathtest_astar_bikini0;
	protected static ShortestPathSolution pcc_pathtest_dijkstra_insa2;
	protected static ShortestPathSolution pcc_pathtest_astar_insa2;
	protected static ShortestPathSolution pcc_pathtest_dijkstra_belgique3;
	protected static ShortestPathSolution pcc_pathtest_astar_belgique3;
	protected static ShortestPathSolution pcc_pathtest_org_dest_dijkstra0;
	protected static ShortestPathSolution pcc_pathtest_org_dest_astar0;
	protected static ShortestPathSolution pcc_pathtest_org_dest_dijkstra1;
	protected static ShortestPathSolution pcc_pathtest_org_dest_astar1;
	protected static ShortestPathSolution pcc_pathtest_org_dest_dijkstra2;
	protected static ShortestPathSolution pcc_pathtest_org_dest_astar2;
	protected static ShortestPathSolution pcc_path_test_nopath_dijkstra0_tun;
	protected static ShortestPathSolution pcc_path_test_nopath_astar0_tun;
	protected static ShortestPathSolution pcc_path_test_nopath_dijkstra1_tun;
	protected static ShortestPathSolution pcc_path_test_nopath_astar1_tun;
	protected static ShortestPathSolution pcc_path_test_nopath_dijkstra0_cuba;
	protected static ShortestPathSolution pcc_path_test_nopath_astar0_cuba;
	protected static ShortestPathSolution pcc_path_test_nopath_dijkstra1_cuba;
	protected static ShortestPathSolution pcc_path_test_nopath_astar1_cuba;
	
	@BeforeClass
	public static void init() throws IOException {

		// récupération graphes
		// carre
		try {
			String carre = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/carre.mapgr";
			GraphReader reader_carre = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(carre))));
			graphe_carre = reader_carre.read();
		} catch (Exception e) {
		}
		// haute-garonne
		try {
			String garonne = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/haute-garonne.mapgr";
			GraphReader reader_haute_garonne = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(garonne))));
			graphe_haute_garonne = reader_haute_garonne.read();
		} catch (Exception e) {
		}

		// toulouse
		try {
			String toulouse = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/toulouse.mapgr";
			GraphReader reader_toulouse = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(toulouse))));
			graphe_toulouse = reader_toulouse.read();
		} catch (Exception e) {
		}

		// belgique
		try {
			String belgique = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/belgium.mapgr";
			GraphReader reader_belgique = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(belgique))));
			graphe_belgique = reader_belgique.read();
		} catch (Exception e) {
		}
		// tunisie
		try {
			String tunisie = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/tunisia.mapgr";
			GraphReader reader_tunisie = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(tunisie))));
			graphe_tunisie = reader_tunisie.read();
		} catch (Exception e) {
		}
		// cuba
		try {
			String cuba = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/cuba.mapgr";
			GraphReader reader_cuba = new BinaryGraphReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(cuba))));
			graphe_cuba = reader_cuba.read();
		} catch (Exception e) {
		}

		// Récupération paths avec modes différents

		// récupération du path bikini (MODE 0)
		try {
			String path_bikini = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/path_fr31_insa_bikini_canal.path";
			PathReader reader_path_bikini = new BinaryPathReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(path_bikini))));
			path_bikini_hg = reader_path_bikini.readPath(graphe_haute_garonne);
		} catch (Exception e) {
		}

		// récupération du path bikini insa (MODE 2)
		try {
			String path_insa = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/path_fr31tls_insa_n7.path";
			PathReader reader_path_insa = new BinaryPathReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(path_insa))));
			path_insa_toulouse = reader_path_insa.readPath(graphe_toulouse);
		} catch (Exception e) {
		}

		// récupération du path belgique (MODE 3)
		try {
			String path_belgique = "C:/Users/Rostom/Videos/_BE GRAPHES/BE-GRAPHES/Maps/path_be_173101_302442.path";
			PathReader reader_path_belgique = new BinaryPathReader(
					new DataInputStream(new BufferedInputStream(new FileInputStream(path_belgique))));
			path_belgique_eu = reader_path_belgique.readPath(graphe_belgique);
		} catch (Exception e) {
		}

		// on récupère les noeuds des graphes (pour ces tests, on n'a besoin que des noeuds du graphe carre)
		noeuds_carre = new ArrayList<>(graphe_carre.getNodes());

//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Origine et Destinations randoms
//////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Tous les modes sont testés avec le graphe carre pour les noeuds randoms
		// pour les noeuds randoms car la comparaison enter les 3 algos prend du temps
		// (Bellmann fait perdre bcp de temps sur les grands graphes)
		Random random_50 = new Random();

		// init du path qui commence du noeud_origine_carre
		// path = new Path(graphe_carre, noeud_origine_carre)

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

		int i = 0; // compteur
		while (i < 50) {

			Node noeud_origine_carre = noeuds_carre.get(random_50.nextInt(noeuds_carre.size()));
			Node noeud_destination_carre = noeuds_carre.get(random_50.nextInt(noeuds_carre.size()));

			// Vérification que les noeuds sont différents (il y a un autre test quand il
			// s'agira du même noeud)

			if (!noeud_origine_carre.equals(noeud_destination_carre)) {

				// MODE 0
				// PCC
				ShortestPathData randoms_pcc0 = new ShortestPathData(graphe_carre, noeud_origine_carre,
						noeud_destination_carre, ArcInspectorFactory.getAllFilters().get(0));
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
				ShortestPathData randoms_pcc1 = new ShortestPathData(graphe_carre, noeud_origine_carre,
						noeud_destination_carre, ArcInspectorFactory.getAllFilters().get(1));
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
				ShortestPathData randoms_pcc2 = new ShortestPathData(graphe_carre, noeud_origine_carre,
						noeud_destination_carre, ArcInspectorFactory.getAllFilters().get(2));
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
				ShortestPathData randoms_pcc4 = new ShortestPathData(graphe_carre, noeud_origine_carre,
						noeud_destination_carre, ArcInspectorFactory.getAllFilters().get(4));
				// Dijkstra
				DijkstraAlgorithm randoms_dijkstra4 = new DijkstraAlgorithm(randoms_pcc4);
				solution_randoms_dijkstra4[i] = randoms_dijkstra4.doRun();
				// AStar
				AStarAlgorithm randoms_astar4 = new AStarAlgorithm(randoms_pcc4);
				solution_randoms_astar4[i] = randoms_astar4.doRun();
				// Bellman Ford
				BellmanFordAlgorithm randoms_bellman4 = new BellmanFordAlgorithm(randoms_pcc4);
				solution_randoms_bellman4[i] = randoms_bellman4.doRun();
				// incrémentation compteur
				i++;
			}

		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Test de certains paths sur Dijkstra et A* (path sur des graphes importants)
//////////////////////////////////////////////////////////////////////////////////////////////////////////

		// PATH BIKINI MODE 0
		// PCC
		ShortestPathData pathtest_bikini0 = new ShortestPathData(graphe_haute_garonne, path_bikini_hg.getOrigin(),
				path_bikini_hg.getDestination(), ArcInspectorFactory.getAllFilters().get(0));
		// Dijkstra
		DijkstraAlgorithm pathtest_dijkstra_bikini0 = new DijkstraAlgorithm(pathtest_bikini0);
		pcc_pathtest_dijkstra_bikini0 = pathtest_dijkstra_bikini0.doRun();

		// AStar
		AStarAlgorithm pathtest_astar_bikini0 = new AStarAlgorithm(pathtest_bikini0);
		pcc_pathtest_astar_bikini0 = pathtest_astar_bikini0.doRun();

		// PATH INSA MODE 2
		// PCC
		ShortestPathData pathtest_insa2 = new ShortestPathData(graphe_toulouse, path_insa_toulouse.getOrigin(),
				path_insa_toulouse.getDestination(), ArcInspectorFactory.getAllFilters().get(2));
		// Dijkstra
		DijkstraAlgorithm pathtest_dijkstra_insa2 = new DijkstraAlgorithm(pathtest_insa2);
		pcc_pathtest_dijkstra_insa2 = pathtest_dijkstra_insa2.doRun();

		// AStar
		AStarAlgorithm pathtest_astar_insa2 = new AStarAlgorithm(pathtest_insa2);
		pcc_pathtest_astar_insa2 = pathtest_astar_insa2.doRun();

		// PATH BELGIQUE MODE 3
		// PCC
		ShortestPathData pathtest_belgique3 = new ShortestPathData(graphe_belgique, path_belgique_eu.getOrigin(),
				path_belgique_eu.getDestination(), ArcInspectorFactory.getAllFilters().get(3));
		// Dijkstra
		DijkstraAlgorithm pathtest_dijkstra_belgique3 = new DijkstraAlgorithm(pathtest_belgique3);
		pcc_pathtest_dijkstra_belgique3 = pathtest_dijkstra_belgique3.doRun();

		// AStar
		AStarAlgorithm pathtest_astar_belgique3 = new AStarAlgorithm(pathtest_belgique3);
		pcc_pathtest_astar_belgique3 = pathtest_astar_belgique3.doRun();

//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Test de solutions quand le noeud origine et le même que la destination
//////////////////////////////////////////////////////////////////////////////////////////////////////////

		// variable random pour le choix du noeud
		Random random = new Random();

		// On récupère un noeud random du graphe carre qu'on va considérer à la fois
		// comme
		// l'ogigine et la destination
		Node noeud_org_dest = noeuds_carre.get(random.nextInt(noeuds_carre.size()));
		path_org_dest = new Path(graphe_carre, noeud_org_dest);

		// MODE 0
		// PCC
		ShortestPathData pathtest_org_dest0 = new ShortestPathData(graphe_carre, noeud_org_dest, noeud_org_dest,
				ArcInspectorFactory.getAllFilters().get(0));
		// Dijkstra
		DijkstraAlgorithm pathtest_org_dest_dijkstra0 = new DijkstraAlgorithm(pathtest_org_dest0);
		pcc_pathtest_org_dest_dijkstra0 = pathtest_org_dest_dijkstra0.doRun();
		// AStar
		AStarAlgorithm pathtest_org_dest_astar0 = new AStarAlgorithm(pathtest_org_dest0);
		pcc_pathtest_org_dest_astar0 = pathtest_org_dest_astar0.doRun();
		// MODE 1
		// PCC
		ShortestPathData pathtest_org_dest1 = new ShortestPathData(graphe_carre, noeud_org_dest, noeud_org_dest,
				ArcInspectorFactory.getAllFilters().get(1));
		// Dijkstra
		DijkstraAlgorithm pathtest_org_dest_dijkstra1 = new DijkstraAlgorithm(pathtest_org_dest1);
		pcc_pathtest_org_dest_dijkstra1 = pathtest_org_dest_dijkstra1.doRun();
		// AStar
		AStarAlgorithm pathtest_org_dest_astar1 = new AStarAlgorithm(pathtest_org_dest1);
		pcc_pathtest_org_dest_astar1 = pathtest_org_dest_astar1.doRun();
		// MODE 2
		// PCC
		ShortestPathData pathtest_org_dest2 = new ShortestPathData(graphe_carre, noeud_org_dest, noeud_org_dest,
				ArcInspectorFactory.getAllFilters().get(2));
		// Dijkstra
		DijkstraAlgorithm pathtest_org_dest_dijkstra2 = new DijkstraAlgorithm(pathtest_org_dest2);
		pcc_pathtest_org_dest_dijkstra2 = pathtest_org_dest_dijkstra2.doRun();
		// AStar
		AStarAlgorithm pathtest_org_dest_astar2 = new AStarAlgorithm(pathtest_org_dest2);
		pcc_pathtest_org_dest_astar2 = pathtest_org_dest_astar2.doRun();
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Graphe non connexe: chaque noeud dans une partie
	//Vérification de la non existence du chemin
	//Remarque: les noeuds ont été choisis suite à une exécution du programme où le résultat avec
	//Bellman Ford présentait un chemin inexistant
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Déclaration des noeuds qui donnent un chemin non existant avec Bellman Ford
	//Tunisie
	Node noeud_origine_nopath_tun=graphe_tunisie.get(282427);
	Node noeud_destination_nopath_tun=graphe_tunisie.get(202288);
	
	//Cuba
	//Déclaration des noeuds qui donnent un chemin non existant avec Bellman Ford
	Node noeud_origine_nopath_cuba=graphe_cuba.get(190131);
	Node noeud_destination_nopath_cuba=graphe_cuba.get(63528);
	
	//TUNISIE
	//MODE 0
	//PCC
	ShortestPathData nopath0_tun = new ShortestPathData(graphe_tunisie, noeud_origine_nopath_tun,
			noeud_destination_nopath_tun, ArcInspectorFactory.getAllFilters().get(0));
	//Dijkstra
	DijkstraAlgorithm nopath_dijkstra0 = new DijkstraAlgorithm(nopath0_tun);
	pcc_path_test_nopath_dijkstra0_tun = nopath_dijkstra0.doRun();
	//AStar
	AStarAlgorithm nopath_astar0 = new AStarAlgorithm(nopath0_tun);
	pcc_path_test_nopath_astar0_tun = nopath_astar0.doRun();
	
	//MODE 1
	//PCC
	ShortestPathData nopath1_tun = new ShortestPathData(graphe_tunisie, noeud_origine_nopath_tun,
			noeud_destination_nopath_tun, ArcInspectorFactory.getAllFilters().get(1));
	//Dijkstra
	DijkstraAlgorithm nopath_dijkstra1_tun = new DijkstraAlgorithm(nopath1_tun);
	pcc_path_test_nopath_dijkstra1_tun = nopath_dijkstra1_tun.doRun();
	//AStar
	AStarAlgorithm nopath_astar1_tun = new AStarAlgorithm(nopath1_tun);
	pcc_path_test_nopath_astar1_tun = nopath_astar1_tun.doRun();
	
	//CUBA
	//MODE 0
	//PCC
	ShortestPathData nopath0_cuba = new ShortestPathData(graphe_cuba, noeud_origine_nopath_cuba,
			noeud_destination_nopath_cuba, ArcInspectorFactory.getAllFilters().get(0));
	//Dijkstra
	DijkstraAlgorithm nopath_dijkstra0_cuba = new DijkstraAlgorithm(nopath0_cuba);
	pcc_path_test_nopath_dijkstra0_cuba = nopath_dijkstra0.doRun();
	//AStar
	AStarAlgorithm nopath_astar0cuba = new AStarAlgorithm(nopath0_cuba);
	pcc_path_test_nopath_astar0_cuba = nopath_astar0cuba.doRun();
	
	//MODE 1
	//PCC
	ShortestPathData nopath1_cuba = new ShortestPathData(graphe_cuba, noeud_origine_nopath_cuba,
			noeud_destination_nopath_cuba, ArcInspectorFactory.getAllFilters().get(1));
	//Dijkstra
	DijkstraAlgorithm nopath_dijkstra1_cuba = new DijkstraAlgorithm(nopath1_cuba);
	pcc_path_test_nopath_dijkstra1_cuba = nopath_dijkstra1_cuba.doRun();
	//AStar
	AStarAlgorithm nopath_astar1_cuba = new AStarAlgorithm(nopath1_cuba);
	pcc_path_test_nopath_astar1_cuba = nopath_astar1_cuba.doRun();
	
	
	}
	
	// TESTS //

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Origine et Destinations randoms
	// Tests en distance et en temps, avec plusieurs modes
//////////////////////////////////////////////////////////////////////////////////////////////////////////

	// MODE 0 (EN DISTANCE)
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra0() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra0[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra0[i].getPath().getLength()
					- solution_randoms_bellman0[i].getPath().getLength()) < 0.1);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar0() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar0[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar0[i].getPath().getLength()
					- solution_randoms_bellman0[i].getPath().getLength()) < 0.1);
		}
	}

	// MODE 1 (EN DISTANCE)
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra1() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra1[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra1[i].getPath().getLength()
					- solution_randoms_bellman1[i].getPath().getLength()) < 0.1);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar1() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar1[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar1[i].getPath().getLength()
					- solution_randoms_bellman1[i].getPath().getLength()) < 0.1);
		}
	}

	// MODE 2 (EN TEMPS)
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra2() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra2[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra2[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman2[i].getPath().getMinimumTravelTime()) < 0.1);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar2() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar2[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar2[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman2[i].getPath().getMinimumTravelTime()) < 0.1);
		}
	}

	// MODE 4 (EN TEMPS)
	// Dijkstra: test validité du path + même solution que Bellman (50 paires de
	// noeuds différents)
	@Test
	public void test_randoms_dijkstra4() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_dijkstra4[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_dijkstra4[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman4[i].getPath().getMinimumTravelTime()) < 0.1);
		}

	}

	// AStar: test validité du path + même solution que Bellman (50 paires de noeuds
	// différents)
	@Test
	public void test_randoms_astar4() {
		for (int i = 0; i < 50; i++) {
			assertTrue(solution_randoms_astar4[i].getPath().isValid());
			assertTrue(Math.abs(solution_randoms_astar4[i].getPath().getMinimumTravelTime()
					- solution_randoms_bellman4[i].getPath().getMinimumTravelTime()) < 0.1);
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Test de certains paths sur Dijkstra et A* (path sur des graphes importants)
	// Tests en distance et en temps, avec plusieurs modes
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// BIKINI MODE 0 (EN DISTANCE)
	// Dijkstra
	@Test
	public void test_path_bikini_dijkstra0_dist() {
		assertTrue(Math.abs(pcc_pathtest_dijkstra_bikini0.getPath().getLength() - path_bikini_hg.getLength()) < 0.1);
		assertTrue(pcc_pathtest_dijkstra_bikini0.getPath().isValid());
	}

	// Astar
	@Test
	public void test_path_bikini_astar0_dist() {
		assertTrue(Math.abs(pcc_pathtest_astar_bikini0.getPath().getLength() - path_bikini_hg.getLength()) < 0.1);
		assertTrue(pcc_pathtest_astar_bikini0.getPath().isValid());
	}

	// INSA MODE 2 (EN DISTANCE)
	// Dijkstra
	@Test
	public void test_path_insa_dijkstra2_dist() {
		assertTrue(Math.abs(pcc_pathtest_dijkstra_insa2.getPath().getLength() - path_insa_toulouse.getLength()) < 0.1);
		assertTrue(pcc_pathtest_dijkstra_insa2.getPath().isValid());
	}

	// Astar
	@Test
	public void test_path_insa_astar2_dist() {
		assertTrue(Math.abs(pcc_pathtest_astar_insa2.getPath().getLength() - path_insa_toulouse.getLength()) < 0.1);
		assertTrue(pcc_pathtest_astar_insa2.getPath().isValid());
	}

	// INSA MODE 2 (EN TEMPS)
	// Dijkstra
	@Test
	public void test_path_insa_dijkstra2_temps() {
		assertTrue(Math.abs(pcc_pathtest_dijkstra_insa2.getPath().getMinimumTravelTime()
				- path_insa_toulouse.getMinimumTravelTime()) < 0.1);
		assertTrue(pcc_pathtest_dijkstra_insa2.getPath().isValid());
	}

	// Astar
	@Test
	public void test_path_insa_astar2_temps() {
		assertTrue(Math.abs(pcc_pathtest_astar_insa2.getPath().getLength() - path_insa_toulouse.getLength()) < 0.1);
		assertTrue(pcc_pathtest_astar_insa2.getPath().isValid());
	}

	// BELGIQUE MODE 3 (EN TEMPS)
	// Dijkstra
	@Test
	public void test_path_belgique_dijkstra3_temps() {
		assertTrue(Math.abs(pcc_pathtest_dijkstra_belgique3.getPath().getMinimumTravelTime()
				- path_belgique_eu.getMinimumTravelTime()) < 0.1);
		assertTrue(pcc_pathtest_dijkstra_belgique3.getPath().isValid());
	}

	// Astar
	@Test
	public void test_path_belgique_astar3_temps() {
		assertTrue(Math.abs(pcc_pathtest_astar_belgique3.getPath().getMinimumTravelTime()
				- path_belgique_eu.getMinimumTravelTime()) < 0.1);
		assertTrue(pcc_pathtest_astar_belgique3.getPath().isValid());
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Test de solution quand le noeud origine et le même que la destination
	// Avec Dijkstra et AStar
	// Les tests sont faits sur le graphe Carre car on n'a pas besoin d'un grand
	// graphe pour un même noeud
	// Tests en distance et en temps, avec plusieurs modes
//////////////////////////////////////////////////////////////////////////////////////////////////////////

	// MODE 0
	// Dijkstra (EN DISTANCE)
	@Test
	public void test_org_dest_dijkstra0_dist() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_dijkstra0.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getLength() - pcc_pathtest_org_dest_dijkstra0.getPath().getLength()) == 0);
		assertTrue(pcc_pathtest_org_dest_dijkstra0.getPath().isValid());
	}

	// AStar (EN DISTANCE)
	@Test
	public void test_org_dest_astar0_dist() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_astar0.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getLength() - pcc_pathtest_org_dest_astar0.getPath().getLength()) == 0);
		assertTrue(pcc_pathtest_org_dest_astar0.getPath().isValid());
	}

	// Dijkstra (EN TEMPS)
	@Test
	public void test_org_dest_dijkstra0_temps() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_dijkstra0.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getMinimumTravelTime()
				- pcc_pathtest_org_dest_dijkstra0.getPath().getMinimumTravelTime()) == 0);
		assertTrue(pcc_pathtest_org_dest_dijkstra0.getPath().isValid());
	}

	// AStar (EN TEMPS)
	@Test
	public void test_org_dest_astar0_temps() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_astar0.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getMinimumTravelTime()
				- pcc_pathtest_org_dest_astar0.getPath().getMinimumTravelTime()) == 0);
		assertTrue(pcc_pathtest_org_dest_astar0.getPath().isValid());
	}

	// MODE 1
	// Dijkstra (EN DISTANCE)
	@Test
	public void test_org_dest_dijkstra1_dist() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_dijkstra1.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getLength() - pcc_pathtest_org_dest_dijkstra1.getPath().getLength()) == 0);
		assertTrue(pcc_pathtest_org_dest_dijkstra1.getPath().isValid());
	}

	// AStar (EN TEMPS)
	@Test
	public void test_org_dest_astar1_temps() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_astar1.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getMinimumTravelTime()
				- pcc_pathtest_org_dest_astar1.getPath().getMinimumTravelTime()) == 0);
		assertTrue(pcc_pathtest_org_dest_astar1.getPath().isValid());
	}

	// MODE 2
	// Dijkstra (EN DISTANCE)
	@Test
	public void test_org_dest_dijkstra2_dist() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_dijkstra2.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getLength() - pcc_pathtest_org_dest_dijkstra2.getPath().getLength()) == 0);
		assertTrue(pcc_pathtest_org_dest_dijkstra2.getPath().isValid());
	}

	// AStar (EN TEMPS)
	@Test
	public void test_org_dest_astar2_temps() {
		assertEquals(0, path_org_dest.getOrigin().compareTo(pcc_pathtest_org_dest_astar2.getPath().getOrigin()));
		assertTrue(Math.abs(path_org_dest.getMinimumTravelTime()
				- pcc_pathtest_org_dest_astar2.getPath().getMinimumTravelTime()) == 0);
		assertTrue(pcc_pathtest_org_dest_astar2.getPath().isValid());
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Graphe non connexe: chaque noeud dans une partie
	//Vérification de la non existence du chemin
	//Remarque: les noeuds ont été choisis suite à une exécution du programme où le résultat avec
	//Bellman Ford présentait un chemin inexistant
	//Test avec plusieurs graphes et différents noeuds
//////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//Graphe Tunisie
	//MODE 0
	//Dijkstra
	@Test 
	public void test_nopath_dijkstra_0_tun() {
		assertEquals(pcc_path_test_nopath_dijkstra0_tun.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}
	//AStar
	@Test 
	public void test_nopath_astar0_tun() {
		assertEquals(pcc_path_test_nopath_astar0_tun.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}	
	//MODE 1
	//Dijkstra
	@Test 
	public void test_nopath_dijkstra1_tun() {
		assertEquals(pcc_path_test_nopath_dijkstra1_tun.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}
	//AStar
	@Test 
	public void test_nopath_astar1_tun() {
		assertEquals(pcc_path_test_nopath_astar1_tun.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}	
	
	//Graphe Cuba
	//MODE 0
	//Dijkstra
	@Test 
	public void test_nopath_dijkstra_0_cuba() {
		assertEquals(pcc_path_test_nopath_dijkstra0_cuba.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}
	//AStar
	@Test 
	public void test_nopath_astar0_cuba() {
		assertEquals(pcc_path_test_nopath_astar0_cuba.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}	
	//MODE 1
	//Dijkstra
	@Test 
	public void test_nopath_dijkstra1_cuba() {
		assertEquals(pcc_path_test_nopath_dijkstra1_cuba.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}
	//AStar
	@Test 
	public void test_nopath_astar_cuba() {
		assertEquals(pcc_path_test_nopath_astar1_cuba.getStatus(), AbstractSolution.Status.INFEASIBLE);
	}
	
}