package com.okunu.dp;

/*
 * 有1、3、5三种硬币，凑齐11块最少需要几枚硬币
 */
public class CoinProblem {

    public static int getCoinNum(int n){
        int[] coin = {1,3,5};
        int num = n+1;
        int[] array = new int[num];
        array[0] = 0;
        for (int i = 1; i < num; i++) {
            int coin1 = i - 1;
            int coin3 = i - 3;
            int coin5 = i - 5;
            int minConSum = array[coin1];
            int lastCons = coin1;
            if (coin3 >= 0 && array[coin3] < minConSum) {
                minConSum = array[coin3];
                lastCons = coin3;
            }
            if (coin5 >= 0 && array[coin5] < minConSum) {
                minConSum = array[coin5];
                lastCons = coin5;
            }
            array[i] = minConSum + 1;
            System.out.println(" i = " + i + "  array[i] = " + array[i] + "   " + lastCons);
        }
        return 0;
    }
    
    public static void main(String[] args) {
        getCoinNum(11);
    }
}
