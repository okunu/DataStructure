package com.okunu;

public class QuickSort {

    /**
     * 将数组分成三段，小于k，大于k，以及未排序段。
     * i表示大于k段的开始，j表示小于k的开始，同时也是遍历索引
     * 如果j中值小于k，则和大于k段值互相交换
     * 算法最后，把k值插入到i+1位置上
     */
    public static int partition(int[] array, int p, int r){
        int k = array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (array[j] < k) {
                i ++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[r];
        array[r] = temp;
        return i + 1;
    }
    
    /**
     * 算法中产生一个多余数据，第一个冗余数据则是k值。本例中k是第一个元素
     * i和j分别从数组两端遍历，指示冗数据的位置，且将必要的数据赋值到冗余位置
     * 最后将k赋值到指定位置
     */
    public static int partition2(int[] array, int p, int r){
        int k = array[p];
        int i = p;
        int j = r;
        while (i < j) {
            while (array[j] > k) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];
                i ++;
            }
            
            while(array[i] < k){
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = k;
        return i;
    }
    
    public static void quickSort(int[] array,int p, int r){
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }
    
    public static void quickSort2(int[] array,int p, int r){
        if (p < r) {
            int q = partition2(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }
    
    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] array = {2,8,7,1,3,5,6,4};
//        int[] array = {2,8};
//        int q = partition(array, 0, array.length - 1);
//        print(array);
//        System.out.println(q);
//        quickSort(array, 0, array.length - 1);
        
        quickSort2(array, 0, array.length - 1);
        print(array);
    }
}
