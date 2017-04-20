package sfg;

import java.util.ArrayList;

/**
 * Created by Salma.Ahmed on 18/04/2017.
 */
public class Mason {

    private ArrayList<String> forwardPaths;
    private ArrayList<String> loops;
    private ArrayList<Integer> combination;
    /* Arraylist to hold valid non touching loops in ascending order ..
   eg. : all 2 non touching loops then all 3 non touching loops and so on..
    */
    private ArrayList<ArrayList<String>> nonTouchingLoops;

    /* constructor*/
    private Mason() {
        forwardPaths = new ArrayList<>();
        loops = new ArrayList<>();
        combination = new ArrayList<>();
        nonTouchingLoops = new ArrayList<>();
    }

    /* a temporary function to fill the arraylist of loops*/
    private void fillLoops() {
        loops.add("abc");
        loops.add("bdef");
        loops.add("xyz");
        loops.add("m");
    }

    /* adds non touching loops to an arraylist to be used in calculations later*/
    private void addNonTouching(int length) {
        ArrayList<String> nonTouchingComb = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            nonTouchingComb.add(loops.get(combination.get(i) - 1));
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
                char letters[] = loops.get(loopsIndex.get(j) - 1).toCharArray();
                for (int k = 0; k < letters.length; k++) {
                    if (loops.get(loopsIndex.get(i) - 1).indexOf(letters[k]) >= 0) {
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

    // returns an arraylist of loops non touching with a certain forward path.
    private ArrayList<String> nonTouchingPath(String path) {
        boolean nonTouching = true;
        ArrayList<String> nonTouchingLoops = new ArrayList<>();
        char pathLetters[] = path.toCharArray();
        for (int i = 0; i < loops.size(); i++) {
            for (int j = 0; j < pathLetters.length; j++) {
                if (loops.get(i).indexOf(pathLetters[j]) >= 0) {
                    nonTouching = false;
                    break;
                }
            }
            if (nonTouching)
                nonTouchingLoops.add(loops.get(i));
        }
        return nonTouchingLoops;
    }

    private Double calculateDelta() {
        Double delta = 1.0;
        double sum = 0.0;
        for (int i = 0; i < loops.size(); i++) {
            //sum += loops.get(i);  replace it with the gain of the loop not the loop itself
        }
        delta-=sum;
        int sign=1;
        for(int j=0;j<nonTouchingLoops.size();j++){
            for (int k = 0; k < nonTouchingLoops.get(j).size(); k++) {
                //sum += nonTouchingLoops.get(j).get(k);  replace it with the gain of the loop not the loop itself
            }
            delta+=(sign*sum);
            sign*=-1;
        }
        return delta;
    }

    private Double calculateTF(){
       double TF=0.0;
       for(int i=0;i<forwardPaths.size();i++){
           ArrayList<String> nonTouching= nonTouchingPath(forwardPaths.get(i));
           double sum=1.0;
           for(int j=0;j<0;j++){
               //sum-=nonTouching.get(j);replace it with the gain of the loop not the loop itself
           }
           //TF+=(sum*forwardPaths.get(i));replace it with the gain of the FP not the path itself
       }
       TF=TF/calculateDelta();
       return TF;

    }
    public static void main(String[] args) {
        Mason mason = new Mason();
        mason.fillLoops();
        mason.getCombinations();
        System.out.println(mason.nonTouchingPath("opq"));
        //System.out.println(mason.nonTouchingLoops);

    }
}
