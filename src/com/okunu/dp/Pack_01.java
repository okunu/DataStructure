package com.okunu.dp;

public class Pack_01 {

    public static void main(String[] args) {
        int[] value = {0, 60, 100, 120};
        int[] weight = {0, 10, 20, 30};
        int w = 50;
        int r = pack(value, weight, w);
        System.out.println(r);
    }
    
    /**
     * 0 1背包问题
     * 此问题满足最优子问题结构。
     * 如果加上第i项的重量，不会超重，那么总体最大价值为value[i] + select[i-1][j-weight[i]]
     * 如果加上第i项的重量超重了，那么总体最大价值为select[i-1][j]
     */
    public static int pack(int[] value, int[] weight, int w){
        int[][] select = new int[value.length][w+1];
        for (int i = 1; i <= w; i++) {
            select[0][i] = 0;
        }
        for (int i = 1; i < value.length; i++) {
            select[i][0] = 0;
            for (int j = 0; j <= w; j++) {
                if (weight[i] <= j) {
                    if (value[i] + select[i-1][j-weight[i]] > select[i-1][j]) {
                        select[i][j] = value[i] + select[i-1][j-weight[i]];
                    }else {
                        select[i][j] = select[i-1][j];
                    }
                }else {
                    select[i][j] = select[i-1][j];
                }
            }
        }
        
        return select[value.length-1][w];
    }
}
