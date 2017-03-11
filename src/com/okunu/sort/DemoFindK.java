package com.okunu.sort;

public class DemoFindK {

    public static int findK(int[] array, int left, int right, int k) {
        int i = partition(array, left, right);
        if (i == k - 1) {
            return array[k - 1];
        } else if (i > k - 1) {
            return findK(array, left, i - 1, k);
        } else if (i < k - 1) {
            return findK(array, i + 1, right, k);
        }
        return 0;
    }

    public static int partition(int[] array, int left, int right) {
        int k = array[left];
        int i = left;
        int j = right;
        while (j > i) {
            while (array[j] < k && j > i) {
                j--;
            }
            if (j > i) {
                array[i] = array[j];
                i++;
            }
            while (array[i] > k && j > i) {
                i++;
            }
            if (j > i) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = k;
        return i;
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = partition(array, left, right);
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
    }

    public static int findKBySort(int[] array, int k) {
        quickSort(array, 0, array.length - 1);
        return array[k - 1];
    }

    public static void maxHeapify(int[] array, int size, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int small = i;
        if (left < size) {
            if (array[small] > array[left]) {
                small = left;
            }
        }
        if (right < size) {
            if (array[small] > array[right]) {
                small = right;
            }
        }
        if (small != i) {
            int temp = array[small];
            array[small] = array[i];
            array[i] = temp;
            maxHeapify(array, size, small);
        }
    }

    public static void buildHeap(int[] array, int size) {
        for (int i = size - 1; i >= 0; i--) {
            maxHeapify(array, size, i);
        }
    }

    public static int findKByHeap(int[] array, int k) {
        buildHeap(array, k);
        for (int i = k + 1; i < array.length; i++) {
            if (array[i] > array[0]) {
                int temp = array[i];
                array[i] = array[0];
                array[0] = temp;
                maxHeapify(array, k, 0);
            }
        }
        return array[0];
    }

    public static void main(String[] args) {
        int[] array = { 4, 8, 7, 1, 3, 5, 6, 2 };
        // int k = partition(array, 0, array.length -1);
        // System.out.println(k);

        // int i = findK(array, 0, array.length - 1, 6);
        // System.out.println(i);

        // quickSort(array, 0, array.length - 1);
        // System.out.println(findKBySort(array, 3));

//        buildHeap(array, 4);
        System.out.println(findKByHeap(array, 4));
        
        print(array);
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
}
