package algorithms;

import java.util.Vector;

import problems.Node;
import problems.Problem;
import problems.QueenNode;
import problems.QueenProblem;

public class BidirectionalSearch {

	private Vector<Node> closed_list_1;
	private Vector<Node> closed_list_2;
	
	private Vector<Node> open_list_1;
	private Vector<Node> open_list_2;
	private Node first;
	private Node last;
	private Problem my_problem;
	private String type;
	private Integer  memory;
	private Integer  expand;
	private Integer  seen_nodes;

	public BidirectionalSearch(Problem my_problem , String type) {

		this.my_problem = my_problem;
		this.type=type;
		
		closed_list_1 = new Vector<Node>();
		
		open_list_1 = new Vector<Node>();

		closed_list_2 = new Vector<Node>();
		
		open_list_2 = new Vector<Node>();

		memory = 0;
		expand = 0;
		seen_nodes = 0;

		first = my_problem.getFirstNode();
		last = my_problem.getFinalNode();

		
		open_list_1.add(first);

		open_list_2.add(last);

		start_tree();
		
	}

	public void start_tree() {

		while (!open_list_1.isEmpty() && !open_list_2.isEmpty()) {

			Node last = open_list_1.firstElement();
			expand++;
			open_list_1.remove(last);
			
			if(type.equalsIgnoreCase("graph"))
				closed_list_1.add(last);
			
			for (int i = 0; i < last.getChild().size(); i++) {
				seen_nodes++;
				boolean what = true;
				
				for (int j = 0; j < closed_list_1.size(); j++) {
					if (closed_list_1.elementAt(j).isSame(
							last.getChild().elementAt(i)))
						what = false;
				}
				for (int j = 0; j < open_list_1.size(); j++) {
					if (open_list_1.elementAt(j).isSame(
							last.getChild().elementAt(i)))
						what = false;
				}
				
				
				if(what)
				open_list_1.add(last.getChild().elementAt(i));
				
			}

			if (open_list_2.size()+open_list_1.size()+closed_list_1.size()+closed_list_2.size()> memory) {
				memory = open_list_2.size()+open_list_1.size()+closed_list_1.size()+closed_list_2.size();
			}

			
			
			
			
			
			last = open_list_2.firstElement();
			expand++;
			open_list_2.remove(last);
			if(type.equalsIgnoreCase("graph"))
				closed_list_2.add(last);

			for (int i = 0; i < last.getChild().size(); i++) {
				seen_nodes++;
				open_list_2.add(last.getChild().elementAt(i));
				
				boolean what = true;
				for (int j = 0; j < closed_list_2.size(); j++) {
					if (closed_list_2.elementAt(j).isSame(
							last.getChild().elementAt(i)))
						what = false;
				}	
				
				for (int j = 0; j < open_list_2.size(); j++) {
					if (open_list_2.elementAt(j).isSame(
							last.getChild().elementAt(i)))
						what = false;
				}	
				if(what)
				open_list_2.add(last.getChild().elementAt(i));
				
				
				
				
				
				for (int j = 0; j < open_list_1.size(); j++) {

					if (open_list_1.elementAt(j).isSame(
							last.getChild().elementAt(i))) {
						printPath(open_list_1.elementAt(j), last.getChild()
								.elementAt(i));

						if (open_list_2.size()+open_list_1.size()+closed_list_1.size()+closed_list_2.size() > memory) {
							memory = open_list_2.size();
						}
						return;
					}
				}

			}

			if (open_list_2.size()+open_list_1.size()+closed_list_1.size()+closed_list_2.size() > memory) {
				memory = open_list_2.size()+open_list_1.size()+closed_list_1.size()+closed_list_2.size();
			}
		}

	
	
	}

	private void printPath(Node first, Node end) {

		System.out.println("This is BDirectional search ("+type+") : ");
		System.out.println("this is count of expanded nodes : " + expand);
		System.out.println("this is count of abserved nodes : " + seen_nodes);
		System.out.println("and the memory usage : " + memory);

		while (first != null) {
			first.print();
			first = first.getParent();
			System.out.println("************");
		}

		
		System.out.println("second part :D");

		while (end != null) {
			end.print();
			end = end.getParent();
			System.out.println("************");
		}

		
	}

	

}
