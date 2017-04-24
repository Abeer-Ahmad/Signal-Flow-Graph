package sfg;

import java.util.ArrayList;

public class Main2 {

	public static void main(String[] args) {
		SFG g = new SFG(9);
		
		Node R = new Node("R");
		Node x1 = new Node("x1");
		Node x2 = new Node("x2");
		Node x3 = new Node("x3");
		Node x4 = new Node("x4");
		Node x5 = new Node("x5");
		Node x6 = new Node("x6");
		Node C1 = new Node("C1");
		Node C2 = new Node("C2");
		
		R.addEdge(new Edge(1, x1));
		x1.addEdge(new Edge(1, x2));
		x2.addEdge(new Edge(10, x3));
		x2.addEdge(new Edge(40, C1));
		x3.addEdge(new Edge(1, x4));
		x4.addEdge(new Edge(20, x5));
		x5.addEdge(new Edge(30, x6));
		x5.addEdge(new Edge(-11, x2));
		x6.addEdge(new Edge(1, C1));
		C1.addEdge(new Edge(1, C2));
		C1.addEdge(new Edge(-22, x4));
		C1.addEdge(new Edge(-1, x1));
		
		g.addNode(R);
		g.addNode(x1);
		g.addNode(x2);
		g.addNode(x3);
		g.addNode(x4);
		g.addNode(x5);
		g.addNode(x6);
		g.addNode(C1);
		g.addNode(C2);
		
		ArrayList<ArrayList<String>> forwardPaths = g.forwardPaths(R, C2);
		System.out.println("Following are the " + forwardPaths.size() +  " different forward paths from R to C2:");
		print(forwardPaths);
	    
	    System.out.println("Following are the corresponding forward gains from R to C2:");
	    ArrayList<ArrayList<Integer>> gains = g.forwardGains();
	    print(gains);
		
		ArrayList<ArrayList<String>> loops = g.loops();
		System.out.println("Following are the " + loops.size() +  " different loops in the SFG:");	    
		print(loops);
	    
	    System.out.println("Following are the corresponding loops gains in the SFG:");
	    ArrayList<ArrayList<Integer>> loopgains = g.loopGains();
	    print(loopgains);
	}

	public static <T> void print(ArrayList<ArrayList<T>> arr) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < arr.get(i).size(); j++)
				System.out.print(arr.get(i).get(j) + " ");
			System.out.println();
	    }
	}
}
