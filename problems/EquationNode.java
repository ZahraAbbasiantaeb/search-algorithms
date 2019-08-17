package problems;

import java.util.Random;
import java.util.Vector;

public class EquationNode extends Node{

	double value ;
	private double varianse = 0.5;
	private double expected_value =0;
	private double fitness;
	private double cumultive ;
	private double lowerbound ;
	private double upperbound ;
	private Random randomno;
	private Vector<Integer>x;
	private Vector<Integer> y;
	
	private double a;
	private double b ;
	private double c;
	
	public EquationNode(double value , Random randomno){
		
		this.randomno=randomno;
		lowerbound = -1;
		upperbound = 1;
		cumultive = 0;
		this.value=value;
		x= new Vector<Integer>();
		y= new Vector<Integer>();
		this.a=a;
		this.b=b;
		this.c=c;
		
		x.add(39);
		x.add(86);
		x.add(64);
		x.add(111);
		x.add(120);
		x.add(120);
		x.add(133);
		x.add(173);
		x.add(171);
		x.add(145);
		
		y.add(43);
		y.add(72);
		y.add(131);
		y.add(175);
		y.add(10);
		y.add(42);
		y.add(98);
		y.add(69);
		y.add(121);
		y.add(155);
		
		setFitness();
		
	}
	
	public void setCumultive(double cumultive) {
		this.cumultive = cumultive;
	}
	
	public double getCumultive(){
		return this.cumultive ;
	}
	
	public void setFitness() {

		int z=0;
		
		for (int i = 0; i < 4; i++) {
			if(this.a*x.elementAt(i)+this.b*y.elementAt(i)+this.c<=0)
				z++;
			else z--;
		}
		for (int i = 4; i < 10; i++) {
			if(this.a*x.elementAt(i)+this.b*y.elementAt(i)+this.c>=0)
				z++;
			else z--;
		}
		this.fitness= z;
		
		
	}
	public double getFitness(){		
		
		return this.fitness;		
	}
	
	public Node CrossOver(Node node , Node node2){
		
		EquationNode temp = new EquationNode(this.value, this.randomno);
		return temp;
		
	}
	
	@Override
	
	
	public void mutation(){
		
		double gussianR = 0;
		  double num = 0;
		  boolean check = false;
		
		  while(!check){
		   gussianR = (randomno.nextGaussian()*varianse)+expected_value;
		   num = gussianR+a;
		   if(num <= upperbound && num >= lowerbound){
		    check = true;
		   }
		  }
		  this.a=num;
		  
		   gussianR = 0;
		   num = 0;
		   check = false;
		
		  while(!check){
		   gussianR = (randomno.nextGaussian()*varianse)+expected_value;
		   num = gussianR+b;
		   if(num <= upperbound && num >= lowerbound){
		    check = true;
		   }
		  }
		  this.b=num;
		  
		  gussianR = 0;
		   num = 0;
		   check = false;
		
		  while(!check){
		   gussianR = (randomno.nextGaussian()*varianse)+expected_value;
		   num = gussianR+c;
		   if(num <= upperbound && num >= lowerbound){
		    check = true;
		   }
		  }
		  this.c=num;
		
	}
}
