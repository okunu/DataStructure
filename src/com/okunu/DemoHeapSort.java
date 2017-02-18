package com.okunu;

public class DemoHeapSort {

    public static void maxHeapify(int[] array, int heapsize, int i){
        int l = 2*i + 1;
        int r = 2*i + 2;
        int large = i;
        if (l < heapsize && array[i] < array[l]) {
            large = l;
        }else {
            large = i;
        }
        if (r < heapsize && array[large] < array[r]) {
            large = r;
        }
        if (large != i) {
            int temp = array[i];
            array[i] = array[large];
            array[large] = temp;
            maxHeapify(array, heapsize, large);
        }
    }
    
    public static void buildMaxHeap(int[] array){
        int heapsize = array.length;
        for (int i = heapsize/2; i >= 0; i--) {
            maxHeapify(array,heapsize,i);
        }
    }
    
    public static int heapMaxNum(int[] array){
        return array[0];
    }
    
    public static int heapExtractMax(int[] array){
        if (array.length == 0) {
            System.out.println("heap is empty");
        }
        int i = array.length - 1;
        int max = array[0];
        array[0] = array[i];
        maxHeapify(array, array.length - 1, 0);
        return max;
    }
    
    public static int getParent(int i){
        if (i == 0) {
            return Integer.MIN_VALUE;
        }else {
            if (i%2 == 0) {
                return (i-1)/2;
            }else {
                return i/2;
            }
        }
    }
    
    public static void heapIncreaseKey(int[] array, int i, int key){
        if (key < array[i]) {
            System.out.println("the key is small than origin");
            return;
        }
        array[i] = key;
        int parent = getParent(i);
        while (parent != Integer.MIN_VALUE && array[i] > array[parent]) {
            int temp = array[i];
            array[i] = array[parent];
            array[parent] = temp;
            i = parent;
            parent = getParent(parent);
        }
    }
    
    public static void heapSort(int[] array){
        int heapsize = array.length;
        for (int i = heapsize - 1; i > 0; i--) {
            if (array[i] < array[0]) {
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                heapsize --;
                maxHeapify(array, heapsize, 0);
            }
        }
    }
    
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] array = {4,1,3,2,16,9,10,14,8,7,5};
        buildMaxHeap(array);
        print(array);
        
        System.out.println(heapMaxNum(array));
        
//        System.out.println(heapExtractMax(array));
        
        heapIncreaseKey(array, array.length - 1, 500);
        
        print(array);
//        heapSort(array);
//        print(array);
    }

}
