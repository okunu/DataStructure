package com.okunu.dp;

/**
 * 钢条切割问题
 * @author oukun.ok
 */
public class SteelProblem {

    //使用递归求解，当钢条被分解为i和n-i两段时，解为i的收益再加上n-i的最大收益。
    //使用循环的意义在于，钢条有可能有n种分法，i段收益加上n-i的最佳收益，所以需要循环遍历，找出最大值
    public static int cutRod(int[] p, int n){
        int q = 0;
        if (n == 0) {
            return 0;
        }
        for (int i = 1; i <= n; i++) {
            //数据是从0开始的，所以i段不切割的直接收益是p[i-1]
            //而n-i的最佳收益是cutRod(p, n - i)，因为我们的i是从1开始的，这样更好理解
            q= Math.max(q, p[i - 1] + cutRod(p, n - i));
        }
        return q;
    }
    
    public static int[] generateArray(int n){
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.MIN_VALUE;
        }
        return array;
    }
    
    /*
     * 自顶向下法，保存结果在数组中，减少递归次数
     */
    public static int cutRod2(int[] p, int n, int[] r){
        int q = 0;
        if (n == 0) {
            return 0;
        }
        //r[n-1]代表着n长钢条的最佳收益
        if (r[n - 1] > 0) {
            return r[n - 1];
        }
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i - 1] + cutRod2(p, n - i, r));
        }
        r[n - 1] = q;
        return q;
    }
    
    /*
     * 自底向上法，算出0，1，2，i。。。一系列长度的切割收益，再计算长度为i+1的最佳收益就简单了
     */
    public static int[] cutRod3(int[] p, int n){
        //为了更好理解，把r数组长度增加一们，r[0]代表0长度的最佳收益，自然是0
        int[] r = new int[n + 1];
        r[0] = 0;
        int q = 0;
        for (int j = 1; j <= n; j++) {
            q = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                //为什么是p[i-1]，数据是从0开始的，所以i段不切割的直接收益是p[i-1]
                q = Math.max(q, p[i - 1] + r[j - i]);
            }
            r[j] = q;
        }
        return r;
    }
    
    public static void main(String[] args) {
//        testCutRoad();
//        testCutRoad2();
        testCutRoad3();
    }
    
    public static void testCutRoad(){
        int[] p = new int[]{1,5,8,9,10,17,17,20,24,30};
        for (int i = 1; i <= p.length; i++) {
            System.out.println("i = " + i + "  result = " + cutRod(p, i));
        }
    }
    
    public static void testCutRoad2(){
        int[] p = new int[]{1,5,8,9,10,17,17,20,24,30};
        int[] r = generateArray(10);
        for (int i = 1; i <= p.length; i++) {
            System.out.println("i = " + i + "  result = " + cutRod2(p, i, r));
        }
    }
    
    public static void testCutRoad3(){
        int[] p = new int[]{1,5,8,9,10,17,17,20,24,30};
        int[] r = cutRod3(p, 10);
        for (int i = 0; i < r.length; i++) {
            System.out.println(" i = " + i + "  r[i] = " + r[i]);
        }
    }
}
