package com.okunu.sort;

public class DemoMergerSort {

    public static void main(String[] args) {
//        int[] array = {5,2,4,7,1,3,8,6};
        int[] array = {5,2,4,7,1,3,8,9,6,10};
        sort2(array, 0, array.length - 1);
        print(array);
    }
    
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
    
    public static void sort(int[] array, int p, int r){
        if (p < r) {
            int q = (r + p)/2;
            sort(array, p, q);
            sort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }
    
    public static void sort2(int[] array, int p, int r){
        if (p < r) {
            int q = (r + p)/2;
            sort2(array, p, q);
            sort2(array, q + 1, r);
            merge2(array, p, q, r);
        }
    }
    
    public static void merge(int[] array, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - (q + 1) + 1;
        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];
        int i = 0;
        int j = 0;
        for (i = 0; i < n1; i++) {
            left[i] = array[p + i];
        }
        left[n1] = Integer.MAX_VALUE;
        for (j = 0; j < n2; j++) {
            right[j] = array[q + 1 + j];
        }
        right[n2] = Integer.MAX_VALUE;
        i = 0;
        j = 0;
        for (int k = p; k <= r; k++) {
            if (left[i] < right[j]) {
                array[k] = left[i];
                i ++;
            }else {
                array[k] = right[j];
                j++;
            }
        }
    }
    
    public static void merge2(int[] array, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - (q + 1) + 1;
        int[] left = new int[n1];
        int[] right = new int[n2];
        int i = 0;
        int j = 0;
        for (i = 0; i < n1; i++) {
            left[i] = array[p + i];
        }
        for (j = 0; j < n2; j++) {
            right[j] = array[q + 1 + j];
        }
        i = 0;
        j = 0;
        int k = 0;
        for (k = p; k <= r; k++) {
            if (left[i] < right[j]) {
                array[k] = left[i];
                i ++;
            }else {
                array[k] = right[j];
                j++;
            }
            if (i == n1 || j== n2) {
                k++;
                break;
            }
        }
        if (i >= n1) {
            while (j < n2) {
                array[k] = right[j];
                j ++;
                k ++;
            }
        }else if (j >= n2) {
            while (i < n1) {
                array[k] = left[i];
                i ++;
                k ++;
            }
        }
    }
}
