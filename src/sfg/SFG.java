package sfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SFG implements ISFG {

    private int size;
    private int counter;
    private Node nodes[];
    private HashMap<String, Integer> indices;
    private ArrayList<ArrayList<String>> forwardPaths;
    private ArrayList<ArrayList<Integer>> forwardGains;
    private ArrayList<ArrayList<String>> loops;
    private ArrayList<ArrayList<Integer>> loopsGains;
    private ArrayList<Integer> combination;
    /* ArrayList to hold ""indexes"" of valid non touching loops in ascending order ..
  eg. : all 2 non touching loops then all 3 non touching loops and so on..
   */
    private ArrayList<ArrayList<Integer>> nonTouchingLoops;

    /* constructor*/
    SFG(int size) {
        this.size = size;
        counter = 0;
        nodes = new Node[size];
        indices = new HashMap<String, Integer>();
        forwardPaths = new ArrayList<ArrayList<String>>();
        forwardGains = new ArrayList<ArrayList<Integer>>();
        combination = new ArrayList<>();
        loops = new ArrayList<>();
        loopsGains = new ArrayList<>();
        nonTouchingLoops = new ArrayList<>();
    }

    @Override
    public void addNode(Node node) {
        indices.put(node.name(), counter);
        nodes[counter] = node;
        counter++;
    }

    @Override
    public ArrayList<ArrayList<String>> forwardPaths(Node source, Node destination) {
        boolean visited[] = new boolean[size]; // initially false
        forwardPaths(source, destination, visited, new ArrayList<String>(), new ArrayList<Integer>());
        return forwardPaths;
    }

    private void forwardPaths(Node source, Node destination, boolean visited[],
                              ArrayList<String> currentPath, ArrayList<Integer> currentGain) {
        int curNode = (int) indices.get(source.name());
        visited[curNode] = true;
        currentPath.add(source.name());
        if (source.name().equals(destination.name())) {// Destination reached
            forwardPaths.add(new ArrayList<>(currentPath));
            forwardGains.add(new ArrayList<>(currentGain));
        } else {
            Iterator<Edge> i = nodes[curNode].edges().listIterator();
            while (i.hasNext()) {
                Edge edge = i.next();
                Node nextNode = edge.to();
                if (!visited[(int) indices.get(nextNode.name())]) {
                    currentGain.add(edge.gain());
                    forwardPaths(nextNode, destination, visited, currentPath, currentGain);
                }
            }
        }
        currentPath.remove(currentPath.size() - 1);
        if (!currentGain.isEmpty())
            currentGain.remove(currentGain.size() - 1);
        visited[curNode] = false;
    }

    /* adds non touching loops ""INDEXES"" to an ArrayList to be used in calculations later*/
    private void addNonTouching(int length) {
        ArrayList<Integer> nonTouchingComb = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            nonTouchingComb.add(combination.get(i) - 1);
        }
        nonTouchingLoops.add(nonTouchingComb);
    }

    /*length is the length of the combination*/
    private void loopsCombination(int x, int length) {
        if (combination.size() == length) {
            if (isNonTouching(combination)) {
                addNonTouching(length);
            }
            return;
        }
        for (int i = x; i <= loops.size(); i++) {
            combination.add(i);
            loopsCombination(i + 1, length);
            combination.remove(combination.size() - 1);
        }
    }

    //checks if a group of loops are non touching to each other given their index in loops array.
    //7ngeb el index deh b2a mn el combinations ely 3mltlhom generation
    private boolean isNonTouching(ArrayList<Integer> loopsIndex) {
        boolean nonTouching = true;
        for (int i = 0; i < loopsIndex.size() && nonTouching == true; i++) {
            for (int j = i + 1; j < loopsIndex.size() && nonTouching == true; j++) {
                for (int k = 0; k < loops.get(loopsIndex.get(j) - 1).size(); k++) {
                    String letter = loops.get(loopsIndex.get(j) - 1).get(k);
                    if (loops.get(loopsIndex.get(i) - 1).indexOf(letter) >= 0) {
                        nonTouching = false;
                        break;
                    }
                }

            }
        }
        return nonTouching;

    }

    // loops to get combinations of different lengths
    private void getCombinations() {
        for (int i = 2; i <= loops.size(); i++) {
            loopsCombination(1, i);
        }
    }

    // returns an ArrayList of "INDEXES" of loops non touching with a certain forward path.
    private ArrayList<Integer> nonTouchingPath(ArrayList<String> path) {
        ArrayList<Integer> nonTouchingLoops = new ArrayList<>();
        for (int i = 0; i < loops.size(); i++) {
            boolean nonTouching = true;
            for (int j = 0; j < path.size(); j++) {
                if (loops.get(i).indexOf(path.get(j)) >= 0) {
                    nonTouching = false;
                    break;
                }
            }
            if (nonTouching)
                nonTouchingLoops.add(i);
        }
        return nonTouchingLoops;
    }

    @Override
    public Double calculateDeltaTotal() {
        Double delta = 1.0;
        int loopGain = 1;
        int sign = -1;
        for (int i = 0; i < loops.size(); i++) {
            loopGain = 1;
            /*inner loop calculates gain of each loop*/
            for (int j = 0; j < loopsGains.get(i).size(); j++) {
                loopGain *= loopsGains.get(i).get(j);
            }
            delta += (loopGain * sign);
        }
        int previousSize = 1;
        for (int i = 0; i < nonTouchingLoops.size(); i++) {
            if (nonTouchingLoops.get(i).size() != previousSize) {
                sign *= -1;
            }
            previousSize = nonTouchingLoops.get(i).size();
            int gainProduct=1;
            for (int j = 0; j < nonTouchingLoops.get(i).size(); j++) {
                loopGain = 1;
               /*inner loop calculates gain of each loop*/
                for (int k = 0; k < loopsGains.get(nonTouchingLoops.get(i).get(j)).size(); k++) {
                    loopGain *= loopsGains.get(nonTouchingLoops.get(i).get(j)).get(k);
                }
                gainProduct*=loopGain;

            }
            delta += (gainProduct* sign);
        }
        return delta;
    }

    /*calculates delta for a certain forward path*/
    @Override
    public Double calculateDelta(ArrayList<String> forwardPath) {
        double delta = 1.0;
        ArrayList<Integer> nonTouching = nonTouchingPath(forwardPath);
        for (int i = 0; i < nonTouching.size(); i++) {
            int loopGain = 1;
                /*inner loop calculates gain of each loop*/
            for (int j = 0; j < loopsGains.get(nonTouching.get(i)).size(); j++) {
                loopGain *= loopsGains.get(nonTouching.get(i)).get(j);
            }
            delta -= loopGain;
        }

        return delta;
    }

    @Override
    public Double calculateTF() {
        double TF = 0.0;
        for (int i = 0; i < forwardPaths.size(); i++) {
            int pathGain = 1;
            for (int j = 0; j < forwardGains.get(i).size(); j++) {
                pathGain *= forwardGains.get(i).get(j);
            }
            TF += (pathGain * calculateDelta(forwardPaths.get(i)));
        }
        TF = TF / calculateDeltaTotal();
        return TF;

    }

    @Override
    public ArrayList<ArrayList<Integer>> forwardGains() {
        return forwardGains;
    }

    @Override
    public ArrayList<ArrayList<String>> loops() {
        return null;
    }

    /* a temporary function to fill the ArrayList of loops*/
    private void fillLoops() {
        ArrayList<String> a = new ArrayList<>();
        a.add("y6");
        a.add("y6");
        loops.add(new ArrayList<>(a));
        ArrayList<Integer> g1 = new ArrayList<>();
        g1.add(-1);
        loopsGains.add(new ArrayList<>(g1));
        ArrayList<String> b = new ArrayList<>();
        b.add("y3");
        b.add("y4");
        b.add("y3");
        loops.add(new ArrayList<>(b));
        ArrayList<Integer> g2 = new ArrayList<>();
        g2.add(-1);
        g2.add(10);
        loopsGains.add(new ArrayList<>(g2));
        ArrayList<String> c = new ArrayList<>();
        c.add("y4");
        c.add("y5");
        c.add("y4");
        loops.add(new ArrayList<>(c));
        ArrayList<Integer> g3 = new ArrayList<>();
        g3.add(-2);
        g3.add(2);
        loopsGains.add(new ArrayList<>(g3));
        ArrayList<String> d = new ArrayList<>();
        d.add("y2");
        d.add("y3");
        d.add("y4");
        d.add("y5");
        d.add("y2");
        loops.add(new ArrayList<>(d));
        ArrayList<Integer> g4 = new ArrayList<>();
        g4.add(5);
        g4.add(10);
        g4.add(2);
        g4.add(-1);
        loopsGains.add(new ArrayList<>(g4));

        ArrayList<String> e = new ArrayList<>();
        e.add("y2");
        e.add("y6");
        e.add("y5");
        e.add("y2");
        loops.add(new ArrayList<>(e));
        ArrayList<Integer> g5 = new ArrayList<>();
        g5.add(10);
        g5.add(2);
        g5.add(-1);
        loopsGains.add(new ArrayList<>(g5));
        ArrayList<String> FP = new ArrayList<>();
        FP.add("y1");
        FP.add("y2");
        FP.add("y3");
        FP.add("y4");
        FP.add("y5");
        forwardPaths.add(new ArrayList<>(FP));
        ArrayList<Integer> gF = new ArrayList<>();
        gF.add(1);
        gF.add(5);
        gF.add(10);
        gF.add(2);
        forwardGains.add(new ArrayList<>(gF));
        ArrayList<String> FP2 = new ArrayList<>();
        FP2.add("y1");
        FP2.add("y2");
        FP2.add("y6");
        FP2.add("y5");
        forwardPaths.add(new ArrayList<>(FP2));
        ArrayList<Integer> gF2 = new ArrayList<>();
        gF2.add(1);
        gF2.add(10);
        gF2.add(2);
        forwardGains.add(new ArrayList<>(gF2));
    }

    public static void main(String[] args) {
        SFG mason = new SFG(6);
        mason.fillLoops();
        mason.getCombinations();
        System.out.println(mason.calculateDeltaTotal());
        System.out.println(mason.calculateTF());
        //System.out.println(mason.nonTouchingPath(c));
        //System.out.println(mason.nonTouchingLoops);

    }
}
