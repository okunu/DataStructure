package com.okunu.dp;

/*
 * 求解最长公共子序列问题
 * 与钢条切割问题不一样，考虑以i为结尾的最大子序列，那么i+1最大子序列则是f(i)+a[i+1]
 * 如果f(i)小于0，那么结果则是a[i+1]
 */
public class MaxSubArray {

    public static int getMaxSubArray(int[] array){
        int[] b = new int[array.length];
        int max = 0;
        b[0] = array[0];
        max = b[0];
        for (int i = 1; i < array.length; i++) {
            if (b[i-1] > 0) {
                b[i] = b[i-1] + array[i];
            }else {
                b[i] = array[i];
            }
            if (max < b[i]) {
                max = b[i];
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
        testMaxSubArray();
    }
    
    public static void testMaxSubArray(){
        int[] array = {-2, 11, -4, 13, -5, -2};
        System.out.println(getMaxSubArray(array));
    }
}
