package gui;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
/**
 * Created by Samsung on 20/04/2017.
 */
public class view {
    public static void main(String args[]) {
            Graph graph = new SingleGraph("Tutorial 1");
        graph.addNode("A" );
        Node node = graph.getNode("A");
        node.setAttribute("xy", 1, 3);
        graph.addNode("B" );
        node = graph.getNode("B");
        node.setAttribute("xy", 6, 3);
        graph.addNode("C" );
         node = graph.getNode("C");
        node.setAttribute("xy", 12, 3);
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        //graph.addEdge("CA", "C", "A");
        graph.display(false);

    }
}
