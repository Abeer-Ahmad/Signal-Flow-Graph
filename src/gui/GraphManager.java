package gui;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import sfg.Edge;
import sfg.SFG;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphManager {

    private static final int Y = 6;

    private Graph graph;
    private Viewer viewer;
    private ViewPanel view;

    public GraphManager(SFG sfg, JFrame frame) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph = new MultiGraph("SFG");
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        view = viewer.addDefaultView(false);
        view.setSize(650, 300);
        view.setLocation(0, 100);
        drawGraph(sfg, frame);
        viewer.disableAutoLayout();
        frame.getContentPane().add((Component) view);
    }

    private void drawGraph(SFG sfg, JFrame frame) {
        sfg.Node[] nodes = sfg.getNodes();
        double nodesSpace = 650 / nodes.length;
        for (int i = 0; i < nodes.length; i++) {
            graph.addNode(nodes[i].name());
            Node node = graph.getNode(nodes[i].name());
            node.setAttribute("xy", nodesSpace + i, Y); // modify
            node.addAttribute("ui.label", node.getId());
        }
        for (int i = 0; i < nodes.length; i++) {
            String from = nodes[i].name();
            ArrayList<Edge> edges = nodes[i].edges();
            for (Edge edge : edges) {
                String to = edge.to().name();
                graph.addEdge(from + to, from, to, true);
                graph.getEdge(from + to).addAttribute("ui.label", edge.gain());
            }
        }
        setStyle();
    }

    private void setStyle() {
        String nodeStyle = "node {" + "fill-color: #87CEEB;"
                + "size: 20px, 20px;" + "text-style: bold;"
                + "text-alignment:under;" + "}";
        String edgeStyle = "edge {"
                + "text-style: bold;" + "text-background-mode:plain;"
                + "shape:cubic-curve;" + "}";
        String styleSheet = nodeStyle + edgeStyle;
        graph.addAttribute("ui.stylesheet", styleSheet);
    }
}