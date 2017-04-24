package sfg;

import java.util.ArrayList;

public interface ISFG {

	public void addNode(Node node);
	
	public ArrayList<ArrayList<String>> forwardPaths(Node source, Node destination);
	
	public ArrayList<ArrayList<Integer>> forwardGains();
	
	public ArrayList<ArrayList<String>> loops();
	
	public ArrayList<ArrayList<Integer>> loopGains();
}
