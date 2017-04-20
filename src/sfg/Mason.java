package sfg;

import java.util.ArrayList;

/**
 * Created by Salma.Ahmed on 18/04/2017.
 */
public class Mason {
    
    private ArrayList<String> forwardPaths;
    private ArrayList<String> loops;
    private ArrayList<Integer> combination ;
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
    private void fillLoops(){
           loops.add("abc");
           loops.add("bdef");
           loops.add("xyz");
           loops.add("m");
    }

    /* adds non touching loops to an arraylist to be used in calculations later*/
    private void addNonTouching(int length){
        ArrayList<String> nonTouchingComb = new ArrayList<>();
        for(int i=0;i<length;i++){
            nonTouchingComb.add(loops.get(combination.get(i)-1));
        }
        nonTouchingLoops.add(nonTouchingComb);
    }

    /*length is the length of the combination*/
    private void loopsCombination(int x,int length){
        if(combination.size()==length){
            if(isNonTouching(combination)){
               addNonTouching(length);
            }
            return;
        }
        for(int i=x;i<=loops.size();i++){
            combination.add(i);
            loopsCombination(i+1,length);
            combination.remove(combination.size()-1);
        }
    }
    //checks if a group of loops are non touching to each other given their index in loops array.
    //7ngeb el index deh b2a mn el combinations ely 3mltlhom generation
    private boolean isNonTouching(ArrayList<Integer> loopsIndex){
        boolean nonTouching=true;
       for(int i=0;i<loopsIndex.size()&&nonTouching == true;i++) {
           for(int j=i+1;j<loopsIndex.size()&&nonTouching == true;j++){
             char letters[]=loops.get(loopsIndex.get(j)-1).toCharArray();
             for(int k=0;k<letters.length;k++){
                 if(loops.get(loopsIndex.get(i)-1).indexOf(letters[k])>=0){
                     nonTouching = false;
                     break;
                 }
             }

           }
       }
      return nonTouching;

    }
    // loops to get combinations of different lengths
    private void getCombinations(){
        for(int i=2;i<=loops.size();i++){
            loopsCombination(1,i);
        }
    }
    public static void main(String[] args){
        Mason mason=new Mason();
        mason.fillLoops();
        mason.getCombinations();
        System.out.println(mason.nonTouchingLoops);
    }
}
