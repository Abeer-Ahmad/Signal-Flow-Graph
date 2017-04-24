package sfg;

public class Edge {
	
	private int gain;
	private Node toNode;

	Edge(int gain, Node toNode) {
		this.gain = gain;
		this.toNode = toNode;
	}
	
	public int gain() {
		return gain;
	}
	
	public Node to() {
		return toNode;
	}
}
