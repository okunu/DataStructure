package com.okunu.dp;

import java.util.ArrayList;

public class MulMatrix {

    public static void matrix_chain_order(int[] p){
        int n = p.length - 1;
        int j = 0;
        int q = 0;
        ArrayList<NumberPair> m = new ArrayList<>();
        ArrayList<NumberPair> s = new ArrayList<>();
        NumberPair temp = null;
        
        for (int i = 1; i < n; i++) {
            temp = new NumberPair(i, i);
            temp.setValue(0);
            m.add(temp);
        }
        
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                j = i + l - 1;
                findPair(m, i, j).setValue(Integer.MAX_VALUE);
                for (int k = i; k <= j-1; k++) {
                    q = findPair(m, i, k).getValue() + findPair(m, k+1, j).getValue() + p[i-1]*p[k]*p[j];
                    if (q < findPair(m, i, j).getValue()) {
                        findPair(m, i, j).setValue(q);
                        findPair(s, i, j).setValue(k);
                    }
                }
            }
        }
        for (NumberPair numberPair : m) {
            //System.out.println(numberPair.i + "  " + numberPair.j + "  " + numberPair.getValue());
        }
        for (NumberPair numberPair : s) {
            System.out.println(numberPair.i + "  " + numberPair.j + "  " + numberPair.getValue());
        }
    }
    
    public static NumberPair findPair(ArrayList<NumberPair> list, int i, int j){
        for (NumberPair numberPair : list) {
            if (numberPair.i == i && numberPair.j == j) {
                return numberPair;
            }
        }
        NumberPair pair = new NumberPair(i, j);
        list.add(pair);
        return pair;
    }
    
    public static void main(String[] args) {
        test();
    }
    
    public static void test(){
        int[] array = {30,35,15,5,10,20,25};
        matrix_chain_order(array);
    }
}
class NumberPair{
    public int i;
    public int j;
    public int value;

    public NumberPair(int i, int j){
        this.i = i;
        this.j = j;
    }
    
    public void setValue(int v){
        this.value = v;
    }
    
    public int getValue(){
        return value;
    }
}
