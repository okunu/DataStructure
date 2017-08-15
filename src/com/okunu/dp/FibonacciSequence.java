package com.okunu.dp;

public class FibonacciSequence {

    public static void main(String[] args) {
        fi(10);
    }
    
    public static int fi(int n){
        if (n < 2) {
            return n==0 ? 0 : 1;
        }
        int[] s = new int[n+1];
        s[0] = 0;
        s[1] = 1;
        for (int i = 2; i < s.length; i++) {
            s[i] = s[i-1] + s[i-2];
        }
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + "   ");
        }
        return s[n];
    }
}
