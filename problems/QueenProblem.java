package problems;

import java.util.Vector;

import algorithms.A_Star;
import algorithms.BFS;
import algorithms.DFS;
import algorithms.HillClimbing;
import algorithms.SimulatedAnnealing;

public class QueenProblem extends Problem {

	Vector<Integer> board ;
	int rand ;
	public QueenProblem() {
		board= new Vector<Integer>();
		for (int i = 0; i < 8; i++) {
			board.add(i, i + 1);
		}
		rand=10;
		 BFS my_bfs = new BFS(this , "graph");
//		 DFS my_dfs= new DFS(this ,0,"tree");
//		A_Star astar = new A_Star(this, "graph");
//		HillClimbing hc = new HillClimbing(this);		
//		SimulatedAnnealing sm = new SimulatedAnnealing(this);
		
	}

	
	public Node getFirstNode() {

		Vector<Integer> board = new Vector<Integer>();

		for (int i = 0; i < 8; i++) {
			board.add(i, i + 1);
		}

		Node n = new QueenNode2(board, null);
		// Node n = new QueenNode(board,0,0 ,null);
		return n;

	}

	public Node getRandomNode() {
	
		for(int j=0 ; j<rand; j++){
			
			int x= (int)(Math.random()*9+rand)%8;
			int y= (int)(Math.random()*9+rand)%8;
			int x1= board.elementAt(x);
			int y1= board.elementAt(y);
			board.set(x, y1);
			board.set(y, x1);
			
		}
		
		rand+=(int)Math.random()*5;
		Node n = new QueenNode2(board, null);
		
		return n;
	}
	
	public static void main(String arg0[]) {
		QueenProblem qp = new QueenProblem();
	}

}
