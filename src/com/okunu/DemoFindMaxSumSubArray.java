package com.okunu;

public class DemoFindMaxSumSubArray {

    public static void main(String[] args) {
        int[] array = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
        
        //测试找跨中点最大值
//        int high = array.length - 1;
//        int middle= (high + 0)/2; 
//        getMaxSumSubArrayCorssMidel(array, 0, middle, high);
        
        //分治法找连续子数组的和的最大值，时间复杂度为N*lgN
        int[] result = getMaxSumSubArray(array, 0, array.length - 1);
        print(result);

        //暴力求解连续子数组的和的最大值，时间复杂度应该是n的平方
        getMaxSumSubArrayForce(array);
        
        //分治法求数组中的最大值，时间复杂度应该是lgN
//        int result = findMax(array, 0, array.length - 1);
//        System.out.println(result);
        
    }

    private static int[] getMaxSumSubArrayForce(int[] array){
        int sum = 0;
        int max_sum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            k= i;
            sum = 0;
            while (k < array.length) {
                sum = sum + array[k];
                if (sum > max_sum) {
                    max_sum = sum;
                    end = k;
                    start = i;
                }
                k++;
            }
        }
        System.out.println(start + "  " + end + "   " + max_sum);
        return new int[]{start, end, max_sum};
    }
    
    private static int findMax(int[] array, int p, int q){
        if (p == q) {
            return array[q];
        }else {
            int middle = (p + q)/2;
            int left = findMax(array, p, middle);
            int right = findMax(array, middle + 1, q);
            return (left > right) ? left : right;
        }
    }
    
    private static int[] getMaxSumSubArray(int[] array, int low, int high){
        if (low == high) {
            return new int[]{low, high ,array[low]};
        }else {
            int middle = (low + high)/2;
            int[] left = getMaxSumSubArray(array, low, middle);
            int[] right = getMaxSumSubArray(array, middle + 1, high);
            int[] corss = getMaxSumSubArrayCorssMidel(array, low, middle, high);
            if (left[2] >= right[2] && left[2] >= corss[2]) {
                return left;
            }else if (right[2] >= left[2] && right[2] >= corss[2]) {
                return right;
            }else{
                return corss;
            }
        }
    }
    
    private static int[] getMaxSumSubArrayCorssMidel(int[] array, int low, int middle, int high){
        int left_sum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeftIndex = 0;
        for (int i = middle; i >= low; i--) {
            sum = sum + array[i];
            if (sum > left_sum) {
                left_sum = sum;
                maxLeftIndex = i;
            }
        }
        sum = 0;
        int right_sum = Integer.MIN_VALUE;
        int maxRightIndex = 0;
        for (int i = middle + 1; i <= high; i++) {
            sum = sum + array[i];
            if (sum > right_sum) {
                right_sum = sum;
                maxRightIndex = i;
            }
        }
        return new int[]{maxLeftIndex, maxRightIndex, left_sum + right_sum};
    }
    
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
}
