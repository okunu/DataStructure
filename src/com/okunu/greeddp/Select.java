package com.okunu.greeddp;

public class Select {

    /**
     * 动态规划计算最大活动数。
     * 此问题满足最优子问题条件，而且可推导公式为：
     * max = Math.max(max, c[i][k] + c[k][j] + 1);
     * 类似钢条切割，一定是中间某点分割，两边都是最优选
     */
    public static int recursion(){
        int[] st = {1,3,0,5,3,5,6,8,8,2,12}; 
        int[] ft = {4,5,6,7,8,9,10,11,12,13,14};
        int length = st.length;
        int[][] c = new int[length][length];
        int[][] ret = new int[length][length];
        
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (i == j) {
                    c[i][j] = 0;
                }else {
                    int max = 0;
                    for (int k = i+1; k < j; k++) {
                        if (st[k] >= ft[i] && ft[k] <= st[j]) {
                            max = Math.max(max, c[i][k] + c[k][j] + 1);
                            ret[i][j] = k;
                        }
                    }
                    if (max == 0) {
                        if (st[j] >= ft[i]) {
                            max += 2;
                        }else {
                            max += 1;
                        }
                    }
                    c[i][j] = max;
                }
            }
        }
        System.out.println(c[0][10]);
        System.out.println("----------");
        printResult(st, ft, 0, 10, ret);
        return c[0][length-1];
    }
    
    public static void printResult(int[] st, int[] ft, int i, int j, int[][] ret){
        int r = ret[i][j];
        if (r > 0) {
            System.out.println(r);
        }
        while (r > 0 && r < st.length && (r=ret[i][r]) > 0) {
            System.out.println(r);
        }
        if (st[j] >= ft[i]) {
            System.out.println(i);
            System.out.println(j);
        }else {
            System.out.println(i);
        }
    }
    
    /**
     * 贪心算法计算最大活动数
     */
    public static void greedSelect(){
        int[] st = {1,3,0,5,3,5,6,8,8,2,12}; 
        int[] ft = {4,5,6,7,8,9,10,11,12,13,14};
        
        int length = st.length;
        int current = 0;
        System.out.println("0 is selected");
        for (int i = 1; i < length; i++) {
            //因为活动结束时间已经按从小到大顺序排列，那么只要活动开始时间大于current结束时间
            //那么此活动一定会是最优的。有可能其它活动开始时间也满足要求，但他们的结束时间一定比i大。
            if (st[i] >= ft[current]) {
                current = i;
                System.out.println(current + " is selected");
            }
        }
    }
    
    public static void main(String[] args) {
        //recursion();
        greedSelect();
    }
}
