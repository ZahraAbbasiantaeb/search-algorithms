package algorithms;

import java.util.Vector;
import problems.MazeProblem;
import problems.Node;
import problems.Problem;

public class A_Star  {

	private Vector<Node> close_list;
	private Vector<Node> open_list;
	
	private Node first;
	private Problem my_problem;
	private String type;
	private Integer  memory;
	private Integer expand;
	private Integer seenNodes;

	 public A_Star (Problem my_problem , String type) {
		// TODO Auto-generated constructor stub
	 
		this.my_problem = my_problem;
		close_list = new Vector<Node>();
		open_list = new Vector<Node>();	
		first = my_problem.getFirstNode();
		open_list.add(first);
		expand=0;
		seenNodes=0;
		memory=0;
		this.type=type;
		start_AstarSearch();

	}

	

	public void start_AstarSearch() {

		while (!open_list.isEmpty()) {
			System.out.println("here");
			if (getMinFCostNode(open_list).isFinal()) {
			
				showResult_g(getMinFCostNode(open_list));
				return;
			}

			Node last = getMinFCostNode(open_list);	
			
			if(type.equalsIgnoreCase("graph"))
			close_list.add(last);
			
			open_list.remove(last);
			expand++;

			for (int i = 0; i < last.getChild().size(); i++) {

				boolean flag = true;
		
				for (int j = 0; j < close_list.size(); j++) {
					if (close_list.elementAt(j).isSame(last.getChild().elementAt(i)))
						flag = false;
				}
				
//				for (int j = 0; j < open_list.size(); j++) {
//					if (open_list.elementAt(j).isSame(last.getChild().elementAt(i)) ){
//						flag = false;
//						if(open_list.elementAt(j).getFcost()>last.getChild().elementAt(i).getFcost()){
//							open_list.elementAt(j).setFcost(last.getChild().elementAt(i).getFcost());
//							open_list.elementAt(j).setParent(last.getChild().elementAt(i).getParent());
//						}
//					}
//				}

				

				if (flag) {
					seenNodes++;
						boolean why= true;
					for (int j = 0; j < open_list.size(); j++) {
						if (open_list.elementAt(j).hasSameFcost(last.getChild().elementAt(i)))
							why = false;
					}
					if(why){
					open_list.add(last.getChild().elementAt(i));
					}
				}
			}

			if (open_list.size()+close_list.size() > memory) {
				memory = open_list.size()+close_list.size();
			}

		}

	}

	

	private Node getMinFCostNode(Vector<Node> current_node2) {
		// TODO Auto-generated method stub
		
		int min = current_node2.get(0).getFcost();
		int index =0;
		
		for (int i = 1; i < current_node2.size(); i++) {
			if(current_node2.get(i).getFcost()<min){
				index=i;
				min = current_node2.get(i).getFcost();
			}
		}
		System.out.println("min is "+ min);
		return current_node2.get(index);
		
		
	}


	private void showResult_g(Node node) {
		
		System.out.println("this is A* search (graph): ");
		System.out.println("this is count of expanded nodes : " + expand);
		System.out.println("this is count of abserved nodes : " + seenNodes);
		System.out.println("and the memory usage : " + memory);
		System.out.println();
		
		node.print();
		System.out.println("***************");
		
		while (node.getParent() != null) {
			node = node.getParent();
			node.print();			
			System.out.println("****************");

		}
		

	}
	
	
}
