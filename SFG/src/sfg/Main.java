package sfg;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		SFG g = new SFG(7);
		
		Node y1 = new Node("y1");
		Node y2 = new Node("y2");
		Node y3 = new Node("y3");
		Node y4 = new Node("y4");
		Node y5 = new Node("y5");
		Node y6 = new Node("y6");
		Node y7 = new Node("y7");
		
		y1.addEdge(new Edge(1, y2));
		y2.addEdge(new Edge(5, y3));
		y2.addEdge(new Edge(10, y6));	
		y3.addEdge(new Edge(10, y4));
		y4.addEdge(new Edge(-1, y3));
		y4.addEdge(new Edge(2, y5));		
		y5.addEdge(new Edge(-1, y2));
		y5.addEdge(new Edge(-2, y4));
		y5.addEdge(new Edge(1, y7));
		y6.addEdge(new Edge(-1, y6));
		y6.addEdge(new Edge(2, y5));
	    
		g.addNode(y1);
		g.addNode(y2);
		g.addNode(y3);
		g.addNode(y4);
		g.addNode(y5);
		g.addNode(y6);
		g.addNode(y7);
		
		ArrayList<ArrayList<String>> forwardPaths = g.forwardPaths(y1, y7);
		System.out.println("Following are the " + forwardPaths.size() +  " different forward paths from y1 to y7:");
		print(forwardPaths);
	    
	    System.out.println("Following are the corresponding forward gains from y1 to y7:");
	    ArrayList<ArrayList<Integer>> forwardGains = g.forwardGains();
	    print(forwardGains);
		
		ArrayList<ArrayList<String>> loops = g.loops();
		System.out.println("Following are the " + loops.size() +  " different loops in the SFG:");	    
		print(loops);
	    
	    System.out.println("Following are the corresponding loops gains in the SFG:");
	    ArrayList<ArrayList<Integer>> loopGains = g.loopGains();
	    print(loopGains);
	    
	    MasonSolver solver = new MasonSolver(forwardPaths, forwardGains, loops, loopGains);
	    System.out.println("Delta = " + solver.calculateDeltaTotal());
	    
	    int i = 0;
	    for (ArrayList<String> forwardPath : forwardPaths)
	    	System.out.println("Delta-" + i++ + " = " + solver.calculateDelta(forwardPath));
	    
	    System.out.println("TF = " + solver.calculateTF());
	}

	public static <T> void print(ArrayList<ArrayList<T>> arr) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(i).size(); j++)
				System.out.print(arr.get(i).get(j) + " ");
			System.out.println();
	    }
	}

}
