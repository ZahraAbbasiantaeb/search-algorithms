package algorithms;

import problems.EquationProblem;
import problems.Problem;
import problems.QueenNode;
import problems.QueenProblem;

public class Genetic {
	
	Problem my_problem;
	public Genetic(Problem my_problem){
		
		this.my_problem=my_problem;
		begin();
	}

	private void begin() {
	
		my_problem.setPopulation();
		int x = 0 ;
		
		while(!my_problem.found() && x<2000) {						
			x++;
			my_problem.getGenerationFitness();
			my_problem.setChildsOFGeneration();
			my_problem.mutateChilds();
			my_problem.selectParent();
			
		}
		
		//((EquationProblem)my_problem).print();
	}
}
