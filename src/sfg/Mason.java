package sfg;

import java.util.ArrayList;

/**
 * Created by Salma.Ahmed on 18/04/2017.
 */
public class Mason {

    ArrayList<Integer> forwardPaths = new ArrayList<>();
    ArrayList<Integer> loops = new ArrayList<>();
    ArrayList<Integer> combination = new ArrayList<>();
    private void fillLoops(){
        for(int i=1;i<=3;i++)
           loops.add(i);
    }
    private void print(int length){
        System.out.println(combination.size());
        for(int i=0;i<length;i++){
            System.out.print(combination.get(i)+" ");
        }
        System.out.println();
    }

    /*length is the length of the combination*/
    private void loopsCombination(int x,int length){
        if(combination.size()==length){
            /*if(isNonTouching()){
               // print();
            }*/
            print(length);
            return;
        }
        for(int i=x;i<=loops.size();i++){
            combination.add(i);
            loopsCombination(i+1,length);
            combination.remove(combination.size()-1);
        }
    }
    //checks if a certain loop is non touching to a certain forward path
    /*private boolean isNonTouching(ArrayList<String> loop){

    }*/
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
    }
}
