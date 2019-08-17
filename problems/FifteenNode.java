package problems;

import java.util.Vector;

public class FifteenNode extends Node {

	
	private Vector <Vector<Integer>> board ;
	private Node parent;
	private int row ;
	private int col ;
	private int Gcost ;
	private Problem my_parent ;
	private int depth;
	private int fCost ;
	private int hCost ;
	
	public FifteenNode (int row , int col  , int Gcost , Node parent , Problem my_parent, Vector <Vector<Integer>> board, int depth){
		
		this.my_parent=my_parent;
		this.row=row;
		this.col=col;		
		this.parent=parent;
		this.board=board;
		this.depth=depth;
		setFcost();
		
	}
	
	private void setFcost() {
		// TODO Auto-generated method stub
		int cost =0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int row1=2;
				int col1=2;
				int z = board.elementAt(i).elementAt(j);
				
				if(z!=0){
					row1= ((z-1)/3);
					col1= ((z-1)%3);
				}
				 cost+=Math.abs((i-row1));
				 cost+=Math.abs(j-col1);
			}
		}
		this.fCost=cost;
//		System.out.println("this is F"+fCost);
//		this.print();
	}

	public boolean isFinal() {
		
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(board.elementAt(i).elementAt(j)!=(3*i+j+1)%9)
					return false;
			}
		}
		
		return true;
	}

	public Vector<Node> getChild() {
		
		Vector <Node> child = new Vector<Node>();
		
		if(getCol()>0){
			Vector <Vector<Integer>> board2= new Vector<Vector<Integer>>();
			int x = board.elementAt(row).elementAt(col-1);
			
			for (int i = 0; i < 3 ; i++) {
				Vector<Integer> b = new Vector<Integer>();
				for (int j = 0; j < 3; j++) {					
					b.add(board.elementAt(i).elementAt(j));
				}
				board2.add(b);
			}
			board2.elementAt(row).set(col-1, 0);
			board2.elementAt(row).set(col, x);
			FifteenNode n = new FifteenNode(row, col-1, this.Gcost+1, this, my_parent, board2, this.depth+1);
			child.add(n);
		}
		
		if(getCol()<2){
			
			Vector <Vector<Integer>> board2= new Vector<Vector<Integer>>();
			int x = board.elementAt(row).elementAt(col+1);
			
			for (int i = 0; i < 3 ; i++) {
				Vector<Integer> b = new Vector<Integer>();
				for (int j = 0; j < 3; j++) {					
					b.add(board.elementAt(i).elementAt(j));
				}
				board2.add(b);
			}
			board2.elementAt(row).set(col+1, 0);
			board2.elementAt(row).set(col,x);
			FifteenNode n = new FifteenNode(row, col+1, this.Gcost+1, this, my_parent, board2, this.depth+1);
			child.add(n);
			
		}
		
		if(getRow()>0){
			
			Vector <Vector<Integer>> board2= new Vector<Vector<Integer>>();
			int x = board.elementAt(row-1).elementAt(col);
			
			for (int i = 0; i < 3 ; i++) {
				Vector<Integer> b = new Vector<Integer>();
				for (int j = 0; j < 3; j++) {					
					b.add(board.elementAt(i).elementAt(j));
				}
				board2.add(b);
			}
			board2.elementAt(row-1).set(col, 0);
			board2.elementAt(row).set(col, x);
			FifteenNode n = new FifteenNode(row-1, col, this.Gcost+1, this, my_parent, board2, this.depth+1);
			child.add(n);
			
		}
		if(getRow()<2){
			
			Vector <Vector<Integer>> board2= new Vector<Vector<Integer>>();
			int x = board.elementAt(row+1).elementAt(col);
			
			for (int i = 0; i < 3 ; i++) {
				Vector<Integer> b = new Vector<Integer>();
				for (int j = 0; j < 3; j++) {					
					b.add(board.elementAt(i).elementAt(j));
				}
				board2.add(b);
			}
			board2.elementAt(row+1).set(col, 0);
			board2.elementAt(row).set(col, x);
			FifteenNode n = new FifteenNode(row+1, col, this.Gcost+1, this, my_parent, board2, this.depth+1);
			child.add(n);
			
		}
			//this written arguman getGcost+1 in this case is like this , but consider we have a graph where each path has it's
		//	special cost then it will be different and we need one function to return cost of the way of the path between this two states.
		return child;
	}

	

	public void setParent(Node last) {
	
		this.parent=last;
		
	}

	public Node getParent() {
		
		return this.parent;
	}

	public void print() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			System.out.println(board.get(i));
		}
		
		
	}

	public Vector < Vector<Integer>> getBoard() {
		return board;
	}

	public void setBoard(int row , int col) {
	
		
	}
	
	public int getGcost() {
		
		return this.Gcost;
	}
	public void setGcost(int gcost) {
		Gcost = gcost;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public int getDepth() {
		// TODO Auto-generated method stub
		return this.depth;
	}

	public Vector<Vector<Integer>> getBoard2() {
		// TODO Auto-generated method stub
		return this.board;
	}
	
	
	public boolean isSame(Node e) {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(board.elementAt(i).elementAt(j)!=e.getBoard2().elementAt(i).elementAt(j))
					return false;
			}
		}
			return true;
		
		
	}

	
	
	public boolean isSameSetFcost(Node elementAt) {

		if(((MazeNode) elementAt).getRow()==this.getRow() &&
				((MazeNode) elementAt).getCol() ==this.getCol()){
			
			if(elementAt.getFcost()<this.getFcost()){
				this.setParent(elementAt.getParent());
				this.setFcost(elementAt.getFcost());}
			
			return true;
		}
		
		return false;
	}

	public void setFcost(int fcost2) {
		// TODO Auto-generated method stub
		
		
		this.fCost=fcost2;
	}

	public int getFcost() {
	
		return this.fCost;
		
	}
	
}
