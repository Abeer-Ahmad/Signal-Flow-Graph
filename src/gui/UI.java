package gui;

import sfg.MasonSolver;
import sfg.SFG;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI extends JFrame {

    private static final long serialVersionUID = 1L;

    protected JFrame frame;
    private JPanel panel;

    private JLabel numLBL;
    private JButton setNum;
    private JTextField num;

    private JLabel nodeLBL;
    private JButton addNode;
    private JTextField node;

    private JLabel sourceLBL;
    private JTextField source;
    private JLabel sinkLBL;
    private JTextField sink;

    private JLabel fromLBL;
    private JButton addEdge;
    private JTextField from;
    private JLabel toLBL;
    private JTextField to;
    private JLabel gainLBL;
    private JTextField gain;

    private JButton solve;
    private JLabel deltaTotalLBL;
    private JLabel deltaTotal;
    private JLabel TF_LBL;
    private JLabel TF;

    private JScrollPane pathScroll;
    private JScrollPane nonTouchingScroll;
    private JScrollPane deltaScroll;
    private JTable pathTable;
    private JTable nonTouchingTable;
    private JTable deltaTable;

    private FrameDesigner frameDesigner;
    private SizeSectionDesigner sizeSecDesigner;
    private NodeSectionDesigner nodeSecDesigner;
    private EdgeSectionDesigner edgeSecDesigner;
    private ResultSectionDesigner resultSecDesigner;
    private TableSectionDesigner tableSectionDesigner;
    private GraphManager graphManager;

    private SFG sfg;
    private MasonSolver solver;
    private ArrayList<ArrayList<String>> forwardPaths;
    private ArrayList<ArrayList<Double>> forwardGains;
    private ArrayList<ArrayList<String>> loops;
    private ArrayList<ArrayList<Integer>> nonTouchingLoops;
    private ArrayList<ArrayList<Double>> loopGains;
    private ArrayList<Double> deltas;

    public UI() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        panel = new JPanel();
        frameDesigner = new FrameDesigner(panel, frame);

        numLBL = new JLabel("Set SFG Size");
        num = new JTextField();
        setNum = new JButton("Set");
        sizeSecDesigner = new SizeSectionDesigner(numLBL, num, setNum, frame);
        readSize();

        nodeLBL = new JLabel("Add New Node");
        node = new JTextField();
        addNode = new JButton("Add");
        sourceLBL = new JLabel("Source");
        source = new JTextField();
        sinkLBL = new JLabel("Sink");
        sink = new JTextField();
        nodeSecDesigner = new NodeSectionDesigner(nodeLBL, node, addNode, sourceLBL, source, sinkLBL, sink, frame);
        readNode();


        fromLBL = new JLabel("Add Edge From");
        from = new JTextField();
        toLBL = new JLabel("To");
        to = new JTextField();
        gainLBL = new JLabel("Gain");
        gain = new JTextField();
        addEdge = new JButton("Add");
        edgeSecDesigner = new EdgeSectionDesigner(fromLBL, from, toLBL, to, gainLBL, gain, addEdge, frame);
        readEdge();

        solve = new JButton("Solve");
        solve.setFont(new Font("Tahoma", Font.BOLD, 13));
        solve.setBounds(10, 505, 93, 25);
        frame.getContentPane().add(solve);
        solve();

        deltaTotalLBL = new JLabel("Delta Total = ");
        deltaTotal = new JLabel();
        TF_LBL = new JLabel("Overall TF = ");
        TF = new JLabel();
        resultSecDesigner = new ResultSectionDesigner(deltaTotalLBL, deltaTotal, TF_LBL, TF, frame);

        pathScroll = new JScrollPane();
        pathTable = new JTable();
        nonTouchingScroll = new JScrollPane();
        nonTouchingTable = new JTable();
        deltaScroll = new JScrollPane();
        deltaTable = new JTable();
        tableSectionDesigner = new TableSectionDesigner(pathScroll, pathTable, nonTouchingScroll, nonTouchingTable, deltaScroll, deltaTable, frame);
    }

    private void readSize() {
        setNum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sfg = new SFG(Integer.parseInt(num.getText()));
                num.setText("");
            }
        });
    }

    private void readNode() {
        addNode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sfg.addNode(new sfg.Node(node.getText()));
                node.setText("");
            }
        });
    }

    private void readEdge() {
        addEdge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fromNode = from.getText();
                String toNode = to.getText();
                Double edgeGain = Double.parseDouble(gain.getText());
                sfg.getNode(fromNode).addEdge(new sfg.Edge(edgeGain, sfg.getNode(toNode)));
                from.setText("");
                to.setText("");
                gain.setText("");
            }
        });
    }

    private void solve() {
        solve.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sfg.Node sourceNode = sfg.getNode(source.getText());
                sfg.Node sinkNode = sfg.getNode(sink.getText());
                source.setText("");
                sink.setText("");
                graphManager = new GraphManager(sfg, frame); // draw graph
                forwardPaths = sfg.forwardPaths(sourceNode, sinkNode);
                forwardGains = sfg.forwardGains();
                loops = sfg.loops();
                loopGains = sfg.loopGains();
                solver = new MasonSolver(forwardPaths, forwardGains, loops, loopGains);
                // fill labels: deltaTotal, TF
                deltaTotal.setText(solver.calculateDeltaTotal().toString());
                TF.setText(solver.calculateTF().toString());
                nonTouchingLoops=solver.getNonTouchingLoops();
                deltas=solver.getDeltas();
                // fill tables
                fillPathTable();
                fillNonTouchingTable();
                fillDeltaTable();

            }
        });
    }

    /*fills the table of forward paths and loops*/
    private void fillPathTable() {
        int tableSize = Math.max(forwardPaths.size(), loops.size());
        tableSize = Math.max(tableSize, 7);
        Object list[][] = new Object[tableSize][tableSize];
        for (int i = 0; i < tableSize; i++) {
            String path = "";
            if (i < forwardPaths.size()) {
                for (int j = 0; j < forwardPaths.get(i).size(); j++) {
                    path += forwardPaths.get(i).get(j);
                }
                list[i][0] = path;
            } else
                list[i][0] = null;
        }
        for (int i = 0; i < tableSize; i++) {
            String path = "";
            if (i < loops.size()) {
                for (int j = 0; j < loops.get(i).size(); j++) {
                    path += loops.get(i).get(j);
                }
                list[i][1] = path;
            } else
                list[i][1] = null;
        }
        pathTable.setModel(new DefaultTableModel(list, new String[]{
                "ForwardPaths", "Loops"}));
    }

    /*fills the table of non-touching loops*/
    private void fillNonTouchingTable() {
        int tableSize = Math.max(nonTouchingLoops.size(), 7);
        Object list[][] = new Object[tableSize][tableSize];
        for(int i=0;i<nonTouchingLoops.size();i++){
            String loop="";
            for(int j=0;j<nonTouchingLoops.get(i).size();j++){
                loop+=loops.get(nonTouchingLoops.get(i).get(j));
            }
            list[i][0]=loop;
        }
        nonTouchingTable.setModel(new DefaultTableModel(list,new String[]{"Non-Touching Loops"}));
    }

    /*fills the table of deltas*/
    private void fillDeltaTable() {
        int tableSize = Math.max(deltas.size(), 7);
        Object list[][] = new Object[tableSize][tableSize];
        for(int i=0;i<deltas.size();i++)
            list[i][0]=deltas.get(i);
        deltaTable.setModel(new DefaultTableModel(list, new String[]{"Deltas"}));
    }
}